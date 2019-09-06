package cn.promptness.blog.support.interceptor;

import cn.promptness.blog.common.constant.enums.UserStateEnum;
import cn.promptness.blog.exception.BizExceptionEnum;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.common.utils.AssertUtils;
import cn.promptness.blog.common.utils.BindUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author : Lynn
 * @date : 2019-05-04 12:48
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 静态资源放行
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }

        Users user = BindUtils.getUser();
        //用户状态(0管理,1会员)
        UserStateEnum userStateEnum = UserStateEnum.getInstance(user.getUserStatus());
        boolean isAdmin = Objects.equals(userStateEnum, UserStateEnum.ADMIN);
        AssertUtils.isTrue(isAdmin, BizExceptionEnum.ACCESS_LIMIT);

        return super.preHandle(request, response, handler);
    }
}
