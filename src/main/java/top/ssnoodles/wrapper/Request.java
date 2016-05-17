package top.ssnoodles.wrapper;

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
}
