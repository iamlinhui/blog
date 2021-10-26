package cn.promptness.blog.support.service.rpc;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.config.properties.QiniuProperties;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : Lynn
 * @date : 2019-03-31 19:55
 */
@Component
public class QiNiuService {

    private static final Logger logger = LoggerFactory.getLogger(QiNiuService.class);

    private static final Cache<String, String> CACHE_MANAGER = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(3600, TimeUnit.SECONDS).build();

    private final QiniuProperties qiniuProperties;
    /**
     * 密钥配置
     */
    private final Auth auth;
    /**
     * 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。 创建上传对象
     */
    private final UploadManager uploadManager;

    public QiNiuService(QiniuProperties qiniuProperties) {
        this.qiniuProperties = qiniuProperties;
        this.auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        this.uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
    }

    public boolean upload(byte[] data, String filename) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(data, filename, getUpToken());
            logger.info(res.bodyString());
            return true;
        } catch (QiniuException e) {
            Response r = e.response;
            logger.error(r.toString());
        }
        return false;
    }

    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     */
    private String getUpToken() {
        String uploadToken = CACHE_MANAGER.getIfPresent(Constants.UPLOAD_TOKEN);
        if (uploadToken == null) {
            uploadToken = auth.uploadToken(qiniuProperties.getBucketName());
            CACHE_MANAGER.put(Constants.UPLOAD_TOKEN, uploadToken);
        }
        return uploadToken;
    }
}
