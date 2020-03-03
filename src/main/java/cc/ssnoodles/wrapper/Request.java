package cc.ssnoodles.wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ssnoodles on 2016/5/17.
 */
public class Request {
    private HttpServletRequest req;

    public Request(HttpServletRequest httpServletRequest) {
        this.req = httpServletRequest;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void attr(String name, Object value){
        req.setAttribute(name, value);
    }

    public Integer queryAsInt(String name) {
        String val = query(name);
        if (val != null && !val.equals("")){
            return Integer.valueOf(val);
        }
        return null;
    }

    public String query(String name){
        return req.getParameter(name);
    }
}
