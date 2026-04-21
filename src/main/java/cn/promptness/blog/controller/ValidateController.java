package cn.promptness.blog.controller;

import cn.promptness.blog.common.captcha.GifCaptcha;
import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.config.properties.CaptchaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class ValidateController {

    @Resource
    private CaptchaProperties captchaProperties;

    @GetMapping("/code.jpg")
    public void codeTo(HttpServletResponse response, HttpSession session) throws Exception {
        GifCaptcha gifCaptcha = captchaProperties.getGifCaptcha();
        String text = gifCaptcha.text();
        session.setAttribute(Constants.LOGIN_CODE_KEY, text);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        gifCaptcha.out(response.getOutputStream());
    }

}
