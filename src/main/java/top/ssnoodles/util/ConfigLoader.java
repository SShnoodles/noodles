package top.ssnoodles.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 加载配置文件
 * Created by ssnoodles on 2016/5/17.
 */
public class ConfigLoader {
    private Map<String, Object> configMap;

    public ConfigLoader() {
        this.configMap = new HashMap<String, Object>();
    }

    public String getConf(String name) {
        Object value = configMap.get(name);
        if (value != null){
            return value.toString();
        }
        return null;
    }
    public Object getConfObject(String name){
        return configMap.get(name);
    }

    public void setConf(String name, String value) {
        configMap.put(name, value);
    }

    public void load(String conf) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(conf));
            toMap(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toMap(Properties properties) {
        if( null != properties){
            Set<Object> keySet = properties.keySet();
            for (Object key : keySet) {
                configMap.put(key.toString(),properties.get(key));
            }

        }

    }
}
