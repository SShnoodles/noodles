package top.ssnoodles.filter;

import top.ssnoodles.config.Bootstrap;
import top.ssnoodles.config.Noodles;
import top.ssnoodles.config.NoodlesContext;
import top.ssnoodles.route.Route;
import top.ssnoodles.route.RouteMatcher;
import top.ssnoodles.route.Routes;
import top.ssnoodles.util.PathUtil;
import top.ssnoodles.util.ReflectUtil;
import top.ssnoodles.wrapper.Request;
import top.ssnoodles.wrapper.Response;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * 核心控制器
 * 1.接收用户请求
 * 2.查找路由
 * 3.找到并执行方法，找不到就404
 *
 * Created by ssnoodles on 2016/5/17.
 */
public class MyFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(MyFilter.class.getName());

    private RouteMatcher routeMatcher = new RouteMatcher(new ArrayList<Route>());

    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Noodles noodles = Noodles.me();
        if (!noodles.isInit()){
            String className = filterConfig.getInitParameter("bootstrap");
            Bootstrap bootstrap = this.getBootstrap(className);
            bootstrap.init(noodles);

            Routes routes = noodles.getRoutes();
            if (null != routes){
                routeMatcher.setRoutes(routes.getRoutes());
            }
            servletContext = filterConfig.getServletContext();
            noodles.setInit(true);
        }
    }

    private Bootstrap getBootstrap(String className) {
        if (className != null){
            try {
                Class<?> clazz = Class.forName(className);
                Bootstrap bootstrap = (Bootstrap)clazz.newInstance();
                return bootstrap;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("init bootstrap class error!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //请求的uri
        String uri = PathUtil.getRelativePath(request);

        LOGGER.info("Request URI：" + uri);
        Route route = routeMatcher.findRoute(uri);
        if (route == null){
            chain.doFilter(servletRequest,servletResponse);
        }else {
            //找到就执行对应的方法
            handle(request, response, route);
        }
    }

    private void handle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Route route) {
        //初始化上下文
        Request request = new Request(servletRequest);
        Response response = new Response(servletResponse);
        NoodlesContext.initContext(servletContext, request , response);

        Object controller = route.getController();
        Method method = route.getMethod();

        //执行方法
        int length = method.getParameterTypes().length;
        method.setAccessible(true); //访问私有方法
        if (length > 0){
            Object[] args = getArgs(request, response, method.getParameterTypes());
            ReflectUtil.invokeMehod(controller, method, args);
        } else {
            ReflectUtil.invokeMehod(controller, method);
        }

    }

    /**
     * 获取方法内的参数
     * @param request
     * @param response
     * @param parameterTypes
     * @return
     */
    private Object[] getArgs(Request request, Response response, Class<?>[] parameterTypes) {
        
    }

    @Override
    public void destroy() {

    }
}
