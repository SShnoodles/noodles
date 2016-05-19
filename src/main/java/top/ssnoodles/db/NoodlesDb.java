package top.ssnoodles.db;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ssnoodles on 2016/5/19.
 */
public final class NoodlesDb {
    private static Sql2o sql2o = null;

    private NoodlesDb() {
    }
    /**
     * 初始化数据库配置
     * @param url
     * @param user
     * @param pass
     */
    public static void init(String url, String user, String pass){
        sql2o = new Sql2o(url, user, pass);
    }
    /**
     * 初始化数据库配置
     * @param dataSource
     */
    public static void init(DataSource dataSource){
        sql2o = new Sql2o(dataSource);
    }

    /**
     * 查询单个
     * @param sql
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String sql, Class<T> clazz){
        return get(sql, clazz , null);
    }

    public static <T> T get(String sql, Class<T> clazz, Map<String, Object> params){
        Connection connection = sql2o.open();
        Query query = connection.createQuery(sql);
        executeQuery(query, params);
        T t = query.executeAndFetchFirst(clazz);
        connection.close();
        return t;
    }

    /**
     * 查询一个列表
     * @param sql
     * @param clazz
     * @return
     */
    public static <T> List<T> getList(String sql, Class<T> clazz){
        return getList(sql, clazz, null);
    }

    /**
     * 查询一个列表
     * @param sql
     * @param clazz
     * @return
     */
    public static <T> List<T> getList(String sql, Class<T> clazz, Map<String, Object> params){
        Connection con = sql2o.open();
        Query query = con.createQuery(sql);
        executeQuery(query, params);
        List<T> list = query.executeAndFetch(clazz);
        con.close();
        return list;
    }

    private static void executeQuery(Query query, Map<String, Object> params) {
        if (null != params && params.size() >0){
            Set<String> keySet = params.keySet();
            for (String key : keySet){
                query.addParameter(key, params.get(key));
            }
        }
    }
}
