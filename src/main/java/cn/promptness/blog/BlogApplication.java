package cn.promptness.blog;

import cn.promptness.blog.config.properties.QiniuProperties;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lynn
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.promptness.blog.mapper"})
@EnableCaching
@EnableTransactionManagement
@EnableAsync
@EnableApolloConfig
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    /**
     * 密钥配置
     */
    @Bean
    public Auth auth(QiniuProperties qiniuProperties) {
        return Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
    }

    /**
     * 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。 创建上传对象
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(new Configuration(Zone.autoZone()));
    }


}
