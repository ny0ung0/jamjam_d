package com.jamjam.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	//단순 문자 메일 보내기
	public void sendSimpleEmail(String email,String title,String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(title);
		message.setTo(email);
		message.setText(content);
		
		javaMailSender.send(message);
	}
	//HTML 메일 보내기
	public void sendHTMLEmail() {
		
	}
	
	
	
}
