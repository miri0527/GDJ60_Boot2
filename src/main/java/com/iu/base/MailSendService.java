package com.iu.base;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.iu.base.member.MemberVO;

public class MailSendService {
	
	@Autowired
	private JavaMailSenderImpl mailSenderImpl;
	private int authNumber;
	
	public String joinMail(String email, MemberVO memberVO) {
		String setForm = ".com";
		String toMail = email;
		String title = "생일축하합니다!";
		
		String contents =  memberVO.getName() + "의 생일을 축하드립니다!";
		
		mailSend(setForm, toMail, title, contents);
		
		return 
		
		
	}
	
	public void mailSend(String setForm, String toMail, String title, String contents) {
		MimeMessage message = mailSenderImpl.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		
		
	}
	
}
