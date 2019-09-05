package cn.promptness.blog.support.interceptor;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.pojo.Options;
import cn.promptness.blog.pojo.Terms;
import cn.promptness.blog.support.service.OptionsService;
import cn.promptness.blog.support.service.TermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : Lynn
 * @date : 2019-05-30 23:20
 */
public class OptionsInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private OptionsService optionsService;
    @Autowired
    private TermsService termsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 静态资源放行
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }

        List<Options> optionsList = optionsService.listOptions();
        for (Options options : optionsList) {
            request.setAttribute(options.getOptionName(), options.getOptionValue());
        }

        // 文章导航栏
        List<Terms> terms = termsService.getTerms();
        request.setAttribute(Constants.TERMS, terms);

        return super.preHandle(request, response, handler);
    }
}
