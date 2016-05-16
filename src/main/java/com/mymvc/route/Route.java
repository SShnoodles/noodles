package com.mymvc.route;

import java.lang.reflect.Method;

/**
 * 路由
 * Created by Administrator on 2016/5/16.
 */
public class Route {
    /**
     * 路由的地址
     */
    private String path;
    /**
     * 执行路由的方法
     */
    private Method method;
    /**
     * 执行路由的控制器
     */
    private Object controller;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }


}
