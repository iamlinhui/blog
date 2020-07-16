package cn.promptness.blog.support.service.rpc;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.common.utils.HttpClientUtils;
import cn.promptness.blog.vo.HttpResult;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class BingPictureRpc {

    private static final Cache<String, String> CACHE_MANAGER = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(12, TimeUnit.HOURS).build();

    @Resource
    private HttpClientUtils httpClientUtil;

    @SneakyThrows
    public String getUrl() {
        String url = CACHE_MANAGER.getIfPresent(Constants.BING_URL_KEY);
        if (StringUtils.isEmpty(url)) {
            HttpResult httpResult = httpClientUtil.doGet("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=1", null, null);
            url = Constants.BING_HOST + httpResult.getContent(JSONObject.class).getJSONArray("images").getJSONObject(0).getString("url");
            CACHE_MANAGER.put(Constants.BING_URL_KEY, url);
        }
        return url;
    }
}
