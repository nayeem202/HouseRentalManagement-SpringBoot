package com.example.repository;

import com.example.model.MailModal;

public interface EmailService {
	void sendMail(MailModal payload);
}
