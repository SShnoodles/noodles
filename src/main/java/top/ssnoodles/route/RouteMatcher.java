package top.ssnoodles.route;

import top.ssnoodles.util.PathUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 路由匹配器
 * Created by ssnoodles on 2016/5/17.
 */
public class RouteMatcher {
    private List<Route> routes;

    public RouteMatcher(List<Route> routes) {
        this.routes = routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    //根据path 查找路由
    public Route findRoute(String path){
        String cleanPath = parsePath(path);
         List<Route> matchRoutes = new ArrayList<Route>();
        for (Route route : this.routes) {
            //匹配
            if(matchesPath(route.getPath(),cleanPath)){
                matchRoutes.add(route);
            }
        }
        //优先匹配原则
        giveMatch(path, matchRoutes);

        return matchRoutes.size() > 0 ? matchRoutes.get(0) : null;
    }

    private void giveMatch(final String uri, List<Route> routes) {
        Collections.sort(routes, new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {
                if(o2.getPath().equals(uri)){
                    return o2.getPath().indexOf(uri);
                }
                return -1;
            }
        });
    }

    private boolean matchesPath(String path, String cleanPath) {
        //:(\w+)  ([^#/?]+)
        path = path.replaceAll(PathUtil.VAR_REGEXP, PathUtil.VAR_REPLACE);
        return cleanPath.matches("(?i)" + path);
    }

    private String parsePath(String path) {
        String fixPath = PathUtil.fixPath(path);
        try {
            URI uri = new URI(fixPath);
            return uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
