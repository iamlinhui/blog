package cn.promptness.blog.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Lynn
 * @date : 2019-04-18 21:02
 */
@Data
@ConfigurationProperties(prefix = "spring.httpclient")
@Component
public class HttpClientProperties {

    private String agent = "agent";
    private Integer maxConnTotal = 200;
    private Integer maxConnPerRoute = 50;
    private Integer connectTimeOut = 100000;
    private Integer connectionRequestTimeout = 100000;
    private Integer socketTimeOut = 500000;
    private Integer validateAfterInactivity = 1000;
    private Boolean expectContinueEnabled = true;

}