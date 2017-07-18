package com.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtil {
	
	 @Autowired
     private MailSender mailSender;
	  
	  @Autowired
	  private SimpleMailMessage simpleMailMessage;
	  
	  public void sendEmail(String to,String text) {
		try {
			simpleMailMessage.setSubject("Blog小组欢迎您使用博客");
			simpleMailMessage.setTo(to);
			simpleMailMessage.setText("您的新密码为:"+text+";请您及时登录博客修改原始密码!");
			mailSender.send(simpleMailMessage);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

}
