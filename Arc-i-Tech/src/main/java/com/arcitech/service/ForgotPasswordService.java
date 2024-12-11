/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.arcitech.model.ForgatePasswordToken;

/**
 * @author AJ
 * 
 */
@Service
public class ForgotPasswordService {

	@Autowired
	JavaMailSender javaMailSender;

	  int minutes = 10;

	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	public LocalDateTime expireTimeRange() {
		return LocalDateTime.now().plusMinutes(0);
	}

	public void sendEmail(String to, String subject, String emailLink)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		String emailContent = "<p>hello</p>" + "Click the link below to reset password" + "<p><a href=\"" + emailLink
				+ "\">Change my Passowrd </a></p>" + "<br>" + "Ignore This Email if you did not made the request";
		helper.setText(emailContent, true);
		helper.setFrom(subject);
		helper.setTo(to);
		javaMailSender.send(message);
	}

	public boolean isExpired(ForgatePasswordToken forgatePasswordToken) {
		return LocalDateTime.now().isAfter(forgatePasswordToken.getExpireTimel());
	}

	public String checkValidity(ForgatePasswordToken forgatePasswordToken) {
		if (forgatePasswordToken == null) {
			return "error page";
		} else if (forgatePasswordToken.isUsed()) {
			return "the token already used";
		} else if (isExpired(forgatePasswordToken)) {
			return "the token is expired";
		} else {
			return "reset password";
		}
	}

}
