package top.ssnoodles.config;

import top.ssnoodles.wrapper.Request;
import top.ssnoodles.wrapper.Response;

import javax.servlet.ServletContext;

/**
 * 当前线程上下文环境
 * Created by ssnoodles on 2016/5/17.
 */
public class NoodlesContext {
    private static final ThreadLocal<NoodlesContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<NoodlesContext>();

    private ServletContext servletContext;
    private Request request;
    private Response response;

    private NoodlesContext() {
    }
    public static void initContext(ServletContext context, Request request, Response response) {
        NoodlesContext marioContext = new NoodlesContext();
        marioContext.servletContext = context;
        marioContext.request = request;
        marioContext.response = response;
        CONTEXT_THREAD_LOCAL.set(marioContext);
    }
    public static NoodlesContext me(){
        return CONTEXT_THREAD_LOCAL.get();
    }
    public static void remove(){
        CONTEXT_THREAD_LOCAL.remove();
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
