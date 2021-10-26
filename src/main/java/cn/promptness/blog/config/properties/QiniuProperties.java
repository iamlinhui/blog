package cn.promptness.blog.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Lynn
 * @date : 2019-03-30 04:55
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
@Data
public class QiniuProperties {

    /**
     * 设置好账号的ACCESS_KEY和SECRET_KEY
     */
    private String accessKey;

    private String secretKey;
    /**
     * 要上传的空间
     */
    private String bucketName;

    private String imageHost;

}
