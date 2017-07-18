package com.blog.Interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.blog.exception.AdminAccessForbiddenException;
import com.blog.pojo.Users;
import com.blog.service.OptionsService;
import com.blog.utils.GlobalString;


/**
 * 非公共资源的拦截器
 * 1).访问静态资源会经过拦截器:静态资源交给<mvc:default-servlet-handler default-servlet-name="default"/>来处理,也算上handler的请求
 * 2).<mvc:view-controller path="/guest/user/toLoginPage" view-name="guest/login"/>是handler请求 
 * 3).常规的handler方法
 * 
 * @author Lynn
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	OptionsService optionsService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1).解决静态资源被拦截器拦截的问题
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}

		// 3).通过request对象获取servletPath数据
		String servletPath = request.getServletPath();

		HttpSession session = request.getSession();

		// 访问管理员资源
		if (servletPath.startsWith("/admin")) {
			Users admin = (Users) session.getAttribute(GlobalString.USER);
			if (admin == null || admin.getUserStatus() != 0) {
				
				
				// 需要从数据库中查出来
				// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				String title = optionsService.getOption(GlobalString.TITLE);
				String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

				request.setAttribute(GlobalString.TITLE, title);
				request.setAttribute(GlobalString.LOGO_PATH, logoPath);
				// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

				// 抛出异常阻止异常访问
				throw new AdminAccessForbiddenException(GlobalString.ADMIN_ACCESS_FORBIDDEN);

			}

		}

		return true;
	}

}
