package top.ssnoodles.route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 路由管理器，存放所有路由
 * Created by ssnoodles on 2016/5/16.
 */
public class Routes {
    private static final Logger LOGGER = Logger.getLogger(Routes.class.getName());

    private List<Route> routes = new ArrayList<Route>();

    public List<Route> getRoutes() {
        return routes;
    }
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Routes(List<Route> routes) {
        this.routes = routes;
    }

    public Routes() {
    }

    //加入
    public void addRoute(List<Route> routes){
        routes.addAll(routes);
    }
    public void addRoute(Route route){
        routes.add(route);
    }
    public void addRoute(String path, Method method, Object controller){
        Route route = new Route();
        route.setPath(path);
        route.setMethod(method);
        route.setController(controller);
        routes.add(route);
        LOGGER.info("add route: " + path);
    }
    //移除
    public void removeRoute(Route route){
        routes.remove(route);
    }

}
