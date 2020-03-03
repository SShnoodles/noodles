package cc.ssnoodles.render;

import java.io.Writer;

/**
 * 视图
 * Created by ssnoodles on 2016/5/17.
 */
public interface Render {
    /**
     * 渲染到视图
     * @param view 视图名称
     * @param writer 写入对象
     */
    public void render(String view, Writer writer);
}