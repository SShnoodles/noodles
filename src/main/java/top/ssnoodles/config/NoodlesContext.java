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

}
