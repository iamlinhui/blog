package cn.promptness.blog.config;

import cn.promptness.blog.exception.BusinessExceptionResolver;
import cn.promptness.blog.support.interceptor.AuthorityInterceptor;
import cn.promptness.blog.support.interceptor.LoginInterceptor;
import cn.promptness.blog.support.interceptor.OptionsInterceptor;
import cn.promptness.blog.support.interceptor.SessionBondingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

        AprLifecycleListener aprLifecycleListener = new AprLifecycleListener();
        aprLifecycleListener.setSSLEngine("off");

        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory() {

            @Override
            protected void customizeConnector(Connector connector) {
                connector.setAttribute("address", "127.0.0.1");
                super.customizeConnector(connector);
            }
        };
        webServerFactory.setProtocol("org.apache.coyote.http11.Http11AprProtocol");

        webServerFactory.addContextLifecycleListeners(aprLifecycleListener);

        return webServerFactory;
    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
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
