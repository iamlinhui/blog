package cn.promptness.blog.config;

import cn.promptness.blog.config.properties.HttpClientProperties;
import cn.promptness.blog.common.utils.HttpClientUtils;
import com.google.common.collect.ImmutableList;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author : Lynn
 * @date : 2019-04-18 21:01
 */
@Configuration
public class HttpClientConfig {

    private final HttpClientProperties httpClientProperties;

    public HttpClientConfig(HttpClientProperties properties) {
        this.httpClientProperties = properties;
    }

    /**
     * httpclient bean 的定义
     *
     * @return CloseableHttpClient
     */
    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager httpClientConnectionManager) {
        return HttpClientBuilder.create()
                .setUserAgent(httpClientProperties.getAgent())
                .setConnectionManager(httpClientConnectionManager)
                .setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
    }

    @Bean
    public RequestConfig requestConfig() {
        //构建requestConfig
        return RequestConfig.custom()
                .setConnectTimeout(httpClientProperties.getConnectTimeOut())
                .setSocketTimeout(httpClientProperties.getSocketTimeOut())
                .setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout())
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setExpectContinueEnabled(httpClientProperties.getExpectContinueEnabled())
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(ImmutableList.of(AuthSchemes.BASIC))
                .build();
    }

    /**
     * 定义连接管理器
     *
     * @return PoolingHttpClientConnectionManager
     */
    @Bean(destroyMethod = "close")
    public PoolingHttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(httpClientProperties.getMaxConnTotal());
        connectionManager.setDefaultMaxPerRoute(httpClientProperties.getMaxConnPerRoute());
        connectionManager.setValidateAfterInactivity(httpClientProperties.getValidateAfterInactivity());
        return connectionManager;
    }

    @Bean
    public HttpClientUtils httpClientUtil(CloseableHttpClient httpClient, RequestConfig requestConfig) {
        return new HttpClientUtils(httpClient, requestConfig);
    }

}
