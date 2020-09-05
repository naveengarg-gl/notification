package com.training.ms.notification.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.training.ms.notification.model.MailMessage;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender javaMailService;
	
	

	public void sendMail(MailMessage mailMessageModel) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(mailMessageModel.getTo());
			mailMessage.setSubject(mailMessageModel.getSubject());
			mailMessage.setFrom(mailMessageModel.getFrom());
			mailMessage.setText(mailMessageModel.getBody());
			javaMailService.send(mailMessage);

		} catch (MailException e) {
			e.printStackTrace();
		}

	}

}
