package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MailModal;
import com.example.repository.EmailService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class MailSendController {
	@Autowired
	private EmailService emailService;
	@PostMapping("/send_amail")
	public ResponseEntity<?> sendMail(@RequestBody MailModal payload){
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			emailService.sendMail(payload);
			data.put("status", "success");
			data.put("messsage", "Mail sent successfully");
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} catch (Exception e) {
			data.put("status", "Failed");
			data.put("messsage", e.getLocalizedMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
		}
		
	}

}
