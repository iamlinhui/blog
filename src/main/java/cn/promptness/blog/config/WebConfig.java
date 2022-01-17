package cn.promptness.blog.config;

import cn.promptness.blog.exception.BusinessExceptionResolver;
import cn.promptness.blog.support.interceptor.AuthorityInterceptor;
import cn.promptness.blog.support.interceptor.LoginInterceptor;
import cn.promptness.blog.support.interceptor.OptionsInterceptor;
import cn.promptness.blog.support.interceptor.SessionBondingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;


/**
 * Web Config
 *
 * @author Lynn
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer, ErrorPageRegistrar {

    @ConditionalOnProperty(prefix = "server", name = "apr", havingValue = "true")
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.setProtocol(Http11AprProtocol.class.getName());
        tomcatServletWebServerFactory.addContextLifecycleListeners(new AprLifecycleListener());
        return tomcatServletWebServerFactory;
    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
    }


    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new BusinessExceptionResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(optionsInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
        registry.addInterceptor(new SessionBondingInterceptor()).addPathPatterns("/admin/**", "/user/**").excludePathPatterns("/static/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**", "/user/**").excludePathPatterns("/static/**");
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/static/**");
    }

    @Bean
    public OptionsInterceptor optionsInterceptor() {
        return new OptionsInterceptor();
    }


    /**
     * 解决https重定向问题
     */
    @Bean
    @Autowired
    public InternalResourceViewResolver defaultViewResolver(WebMvcProperties mvcProperties) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(mvcProperties.getView().getPrefix());
        resolver.setSuffix(mvcProperties.getView().getSuffix());
        resolver.setRedirectHttp10Compatible(false);
        return resolver;
    }
}
