package cn.promptness.blog.config;

import cn.promptness.httpclient.HttpClientProperties;
import cn.promptness.httpclient.core.DefaultHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Lynn
 * @date : 2019-04-18 21:01
 */
@Configuration
public class HttpClientConfig {

    @ConfigurationProperties(prefix = "spring.httpclient")
    @Bean
    public HttpClientProperties httpClientProperties() {
        return new HttpClientProperties();
    }

    @Bean
    public DefaultHttpClient httpClientUtil(HttpClientProperties httpClientProperties) {
        return new DefaultHttpClient(httpClientProperties);
    }

}
