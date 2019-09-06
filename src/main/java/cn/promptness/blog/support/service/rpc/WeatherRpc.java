package cn.promptness.blog.support.service.rpc;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.support.service.OptionsService;
import cn.promptness.blog.common.utils.HttpClientUtils;
import cn.promptness.blog.vo.HttpResult;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.http.message.BasicHeader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lionel
 * @date 2019/9/5 13:35
 * @since v1.0.0
 */
@Service
public class WeatherRpc {

    @Resource
    private HttpClientUtils httpClientUtil;
    @Resource
    private OptionsService optionsService;

    @Cacheable(value = "weatherCache", key = "'weather' + #ip")
    public Map getWeather(String ip) throws Exception {
        BasicHeader basicHeader = new BasicHeader("Authorization", "APPCODE " + optionsService.getOption(Constants.APPCODE));
        HttpResult httpResult = httpClientUtil.doGet("http://jisutqybmf.market.alicloudapi.com/weather/query", ImmutableMap.of("ip", ip), Lists.newArrayList(basicHeader));
        return httpResult.getContent(Map.class);
    }
}
