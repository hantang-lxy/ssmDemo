package cn.myjava.ssmDemo.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/24  21:58
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

    /**
     * 在调用controller之前的处理，一般可以做日志，登录权限认证等
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("======进入拦截器preHandle========");
        Object data = request.getSession().getAttribute("stuSession");
        //符合条件不做拦截，放行
        if (data != null) {
            return true;
        }
        //不符合条件，被拦截，进行处理
        response.sendRedirect("/ssmDemo");
        return false;
    }

    /**
     * 方法调用后，渲染视图之前的操作，可以对数据再次处理
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("======进入拦截器postHandle========");
    }

    /**
     * 调用完成之后可以执行，一般：作为资源关闭
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("======进入拦截器afterCompletion========");
    }
}
