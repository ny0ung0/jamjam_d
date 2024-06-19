package com.jamjam.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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
	public void sendHTMLEmail(String email, String title, String htmlContent) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(htmlContent, true); // true indicates the message is HTML
            
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	
	
}
