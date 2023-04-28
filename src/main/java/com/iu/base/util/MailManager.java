package com.iu.base.util;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailManager {

	  @Autowired
	  private JavaMailSender javaMailSender;
	   
	   @Value("${spring.mail.username}")
	   private String sender;
	   
	   public void send(String to, String sub, String text) throws Exception {
	      
	      MimeMessage mailMessage = javaMailSender.createMimeMessage();
	      mailMessage.setFrom(sender);
	      mailMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
	      mailMessage.setSubject(sub);
	      mailMessage.setText(text);
	      javaMailSender.send(mailMessage);
	      

	   }
}
