package top.ssnoodles.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 路径工具
 * Created by ssnoodles on 2016/5/17.
 */
public class PathUtil {
    public static final String VAR_REGEXP = ":(\\w+)";
    public static final String VAR_REPLACE = "([^#/?]+)";

    private static final String SLASH = "/";

    //对路径简单处理
    public static String fixPath(String path){
        if (path == null){
            return SLASH;
        }
        if (!path.startsWith(SLASH)){
            path = SLASH + path;
        }
        if (path.length() >1 && path.endsWith(SLASH)){
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public static String getRelativePath(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath(); //工程

        path = path.substring(contextPath.length());

        if (path.length() > 0 ){
            path = path.substring(1);
        }
        if (!path.startsWith(SLASH)){
            path = SLASH + path;
        }
        //UTF-8编码
        try {
            URLDecoder.decode(path,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }
}
