package cn.promptness.blog.support.service.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author : Lynn
 * @date : 2019-05-04 01:33
 */
@Component
@Slf4j
public class SendMailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private MailProperties mailProperties;

    /**
     * @param toEmail 收件人
     * @param subject 主题
     * @param context 内容
     * @author linhuid
     * @date 2019-05-26 19:18
     * @since v1.0.0
     */
    @Async("asyncTaskExecutor")
    public void sendMail(String toEmail, String subject, String context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setTo(toEmail);
            // 启用HTML
            mimeMessageHelper.setText(context, true);
            // 发送邮件
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn("发送邮件{}至{}失败! {}", context, toEmail, e.getMessage());
        }

    }
}
