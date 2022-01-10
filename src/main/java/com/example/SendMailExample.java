package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.model.MailModal;
import com.example.repository.EmailService;

@Service
public class SendMailExample implements EmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(MailModal payload) throws MailSendException {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("nayeem.ahmedemba@gmail.com");
		msg.setTo(payload.getTo());
		msg.setSubject(payload.getSubject());
		msg.setText(payload.getMessage());
		mailSender.send(msg);
		System.out.println("=============Mail sent successfully===================");
	}

}
