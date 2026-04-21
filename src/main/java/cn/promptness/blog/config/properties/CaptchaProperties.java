package cn.promptness.blog.config.properties;

import cn.promptness.blog.common.captcha.Captcha;
import cn.promptness.blog.common.captcha.GifCaptcha;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Data
@Configuration
@ConfigurationProperties(prefix = "captcha.gif")
public class CaptchaProperties {

    private int width = 130;
    private int height = 43;
    private int length = 5;
    private int charType = Captcha.TYPE_ONLY_LOWER;

    private String font = "Verdana";
    private int fontSize = 32;

    public GifCaptcha getGifCaptcha() {
        GifCaptcha gifCaptcha = new GifCaptcha(getWidth(), getHeight(), getLength());
        gifCaptcha.setFont(new Font(getFont(), Font.PLAIN, getFontSize()));
        gifCaptcha.setCharType(getCharType());
        return gifCaptcha;
    }
}
