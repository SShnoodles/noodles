package top.ssnoodles.render;

import top.ssnoodles.config.Const;
import top.ssnoodles.config.Noodles;
import top.ssnoodles.config.NoodlesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by ssnoodles on 2016/5/19.
 */
public class JspRender implements Render{
    @Override
    public void render(String view, Writer writer) {
        String viewPath = this.getViewPath(view);

        HttpServletRequest request = NoodlesContext.me().getRequest().getReq();
        HttpServletResponse response = NoodlesContext.me().getResponse().getRes();

        try {
            request.getRequestDispatcher(viewPath).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getViewPath(String view) {
        Noodles noodles = Noodles.me();
        String viewPrefix = noodles.getConfig(Const.VIEW_PREFIX_FIELD);
        String viewSuffix = noodles.getConfig(Const.VIEW_SUFFIX_FIELD);

        if (null == viewSuffix || viewSuffix.equals("")) {
            viewSuffix = Const.VIEW_SUFFIX;
        }
        if (null == viewPrefix || viewPrefix.equals("")) {
            viewPrefix = Const.VIEW_PREFIX;
        }

        String viewPath = viewPrefix + "/" + view;
        if (!view.endsWith(viewSuffix)) {
            viewPath += viewSuffix;
        }
        return viewPath.replaceAll("[/]+","/");
    }
}
