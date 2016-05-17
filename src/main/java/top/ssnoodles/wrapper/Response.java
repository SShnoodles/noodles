package top.ssnoodles.wrapper;

import top.ssnoodles.config.Noodles;
import top.ssnoodles.render.Render;

import javax.servlet.http.HttpServletResponse;

/**
 * HttpServletResponse增强
 * Created by ssnoodles on 2016/5/17.
 */
public class Response {

    private HttpServletResponse res;

    private Render render = null;

    public Response(HttpServletResponse httpServletResponse) {
        this.res = httpServletResponse;
        this.res.setHeader("Framework", "Noodles");
        this.render = Noodles.me().getRender();
    }
    public HttpServletResponse getRes() {
        return res;
    }

}
