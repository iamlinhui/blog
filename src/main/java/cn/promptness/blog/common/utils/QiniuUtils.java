package cn.promptness.blog.common.utils;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.config.properties.QiniuProperties;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : Lynn
 * @date : 2019-03-31 19:55
 */
@Component
public class QiniuUtils {

    private static final Logger logger = LoggerFactory.getLogger(QiniuUtils.class);

    private static final Cache<String, String> CACHE_MANAGER = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(3600, TimeUnit.SECONDS).build();

    @Autowired
    private QiniuProperties qiniuProperties;
    @Autowired
    private Auth auth;
    @Autowired
    private UploadManager uploadManager;

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
