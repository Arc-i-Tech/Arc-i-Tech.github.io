/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.arcitech.service.EmailService;
import com.arcitech.utils.Email;

/**
 * @author Ajay G
 * 
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public void sendEmail(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(email.getSender());
		mailMessage.setTo(String.join(",", email.getRecipients()));
		mailMessage.setText(email.getBody());
		mailMessage.setSubject(email.getSubject());
		this.javaMailSender.send(mailMessage);
	}

}
