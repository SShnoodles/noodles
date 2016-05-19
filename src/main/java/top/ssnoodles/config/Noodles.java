package top.ssnoodles.config;

import top.ssnoodles.render.JspRender;
import top.ssnoodles.render.Render;
import top.ssnoodles.util.ConfigLoader;
import top.ssnoodles.route.Routes;
import top.ssnoodles.wrapper.Request;
import top.ssnoodles.wrapper.Response;

import java.lang.reflect.Method;

/**
 * Created by ssnoodles on 2016/5/17.
 */
public final class Noodles {
    /**
     * 存放所有的路由
     */
    private Routes routes;
    /**
     * 配置加载器
     */
    private ConfigLoader configLoader;
    /**
     * 是否初始化框架
     */
    private boolean init = false;
    /**
     * 渲染器
     */
    private Render render;

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public void addRoute(Routes routes){
        this.routes.addRoute(routes.getRoutes());
    }

    /**
     * 添加路由
     * @param path 路径
     * @param methodName 方法名
     * @param controller 控制器
     * @return
     */
    public Noodles addRoute(String path, String methodName, Object controller){
        try {
            Method method = controller.getClass().getMethod(methodName, Request.class, Response.class);
            this.routes.addRoute(path, method, controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getConfig(String name) {
        return this.configLoader.getConf(name);
    }

    public Noodles setConfig(String name, String value) {
        this.configLoader.setConf(name , value);
        return this;
    }
    public Noodles loadConfig(String conf){
        configLoader.load(conf);
        return this;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }

    private Noodles() {
        routes = new Routes();
        configLoader = new ConfigLoader();
        render = new JspRender();
    }
    private static class NoodlesHolder{
        private static Noodles ME = new Noodles();
    }
    public static Noodles me(){
        return NoodlesHolder.ME;
    }
}
