package cn.promptness.blog.support.interceptor;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.utils.BindUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Lynn
 * @date : 2019-05-03 23:54
 */
public class SessionBondingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Users user = (Users) request.getSession().getAttribute(Constants.USER);
        if (null == user) {
            return true;
        }
        BindUtils.bind(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        BindUtils.remove();
    }

}
