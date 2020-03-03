package cc.ssnoodles.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具
 * Created by ssnoodles on 2016/5/17.
 */
public class ReflectUtil {

    public static Object invokeMehod(Object controller, Method method, Object...args) {
        Class<?>[] types = method.getParameterTypes();
        int length = types == null ? 0 : types.length;

        //转参数类型
        for (int i = 0 ; i < length ; i++){
            args[i] = cast(args[i],types[i]);
        }
        try {
            return method.invoke(controller, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //类型转换
    private static <T> T cast(Object arg, Class<T> type) {
        if (arg != null && !type.isAssignableFrom(type.getClass())){
            if (is(type, int.class, Integer.class)){
                arg = Integer.parseInt(String.valueOf(arg));
            } else if (is(type, long.class, Long.class)){
                arg = Long.parseLong(String.valueOf(arg));
            } else if (is(type, float.class, Float.class)) {
                arg = Float.parseFloat(String.valueOf(arg));
            } else if (is(type, double.class, Double.class)) {
                arg = Double.parseDouble(String.valueOf(arg));
            } else if (is(type, boolean.class, Boolean.class)) {
                arg = Boolean.parseBoolean(String.valueOf(arg));
            } else if (is(type, String.class)) {
                arg = String.valueOf(arg);
            }
        }
        return (T) arg;
    }

    private static boolean is(Object obj, Object...args) {
        if (obj != null && args != null){
            for (Object arg : args){
                if (arg.equals(obj)){
                    return true;
                }
            }
        }
        return false;
    }

}
