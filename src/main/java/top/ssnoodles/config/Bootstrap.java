package top.ssnoodles.config;

/**
 * 启动类
 * Created by ssnoodles on 2016/5/17.
 */
public interface Bootstrap {
    /**
     * 初始化方法
     * @param noodles 全局对象
     */
    void init(Noodles noodles);
}
