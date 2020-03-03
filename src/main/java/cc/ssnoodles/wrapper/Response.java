package cc.ssnoodles.wrapper;

import cc.ssnoodles.config.Noodles;
import cc.ssnoodles.render.Render;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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

    public void render(String view) {
        render.render(view, null);
    }

    public void text(String text) {
        res.setContentType("text/plan;charset=UTF-8");
        this.print(text);
    }

    public void html(String html) {
        res.setContentType("text/html;charset=UTF-8");
        this.print(html);
    }

    private void print(String str){
        try {
            OutputStream outputStream = res.getOutputStream();
            outputStream.write(str.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cookie(String name, String value){
        Cookie cookie = new Cookie(name, value);
        res.addCookie(cookie);
    }

    public void redirect(String location) {
        try {
            res.sendRedirect(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
