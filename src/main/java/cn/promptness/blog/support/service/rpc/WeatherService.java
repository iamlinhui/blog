package cn.promptness.blog.support.service.rpc;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.support.service.OptionsService;
import cn.promptness.httpclient.core.DefaultHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lionel
 * @date 2019/9/5 13:35
 * @since v1.0.0
 */
@Service
@Slf4j
public class WeatherService {

    @Resource
    private DefaultHttpClient httpClientUtil;
    @Resource
    private OptionsService optionsService;

    @Cacheable(value = "weatherCache", key = "'weather' + #ip")
    public String getWeather(String ip) {
        String appCode = optionsService.getOption(Constants.APPCODE);
        return httpClientUtil.get("https://jisutqybmf.market.alicloudapi.com/weather/query")
                .addHeader("Authorization", "APPCODE " + appCode)
                .addParam("ip", ip)
                .execute().getMessage();
    }
}
