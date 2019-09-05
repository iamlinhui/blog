package cn.promptness.blog.support.interceptor;

import cn.promptness.blog.exception.BizExceptionEnum;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.utils.AssertUtils;
import cn.promptness.blog.support.utils.BindUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Lynn
 * @date : 2019-05-31 00:02
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 静态资源放行
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }

        Users user = BindUtils.getUser();
        AssertUtils.notNull(user, BizExceptionEnum.USER_NOT_LOGIN);

        return super.preHandle(request, response, handler);
    }
}