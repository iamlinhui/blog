package cn.promptness.blog.controller;

import cn.promptness.blog.support.service.OptionsService;
import cn.promptness.blog.support.utils.HttpUtils;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class OptionsController {

    @Resource
    private OptionsService optionsService;

    /**
     * 到站点设置页面
     */
    @GetMapping(value = "/website")
    public String toWebsitePage() {
        return "admin/website";
    }

    /**
     * 站点(信息保存)图标上传
     */
    @PostMapping(value = "/website")
    public String saveOptions() {

        Map<String, String[]> parameterMap = HttpUtils.getRequest().getParameterMap();
        Map<String, String> options = Maps.newHashMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            options.put(entry.getKey(), entry.getValue()[0]);
        }

        if (!CollectionUtils.isEmpty(options)) {
            optionsService.bathSaveOptions(options);
        }

        return "redirect:/admin/website";
    }

}
