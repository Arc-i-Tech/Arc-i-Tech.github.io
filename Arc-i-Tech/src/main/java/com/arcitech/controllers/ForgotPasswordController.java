/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcitech.model.ForgatePasswordToken;
import com.arcitech.model.User;
import com.arcitech.repository.ForgatePasswordTokenRepository;
import com.arcitech.service.ForgotPasswordService;
import com.arcitech.service.Userservice;

/**
 * @author AJ
 * 
 */
@Controller
public class ForgotPasswordController {

	@Autowired
	private Userservice userSerice;

	@Autowired
	ForgatePasswordTokenRepository forgatePasswordTokenRepository;

	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/passowrd-request")
	public String passwordRequest() {
		return "passowrd-request";
	}

	@PostMapping("/password-request")
	public String savePasswordRequest(@RequestParam("username") String username, Model model)
			throws UnsupportedEncodingException, MessagingException {

		User user = userSerice.findByUsername(username);

		if (user == null) {
			return "This is not Registerd";
		}

		ForgatePasswordToken forgatePasswordToken = new ForgatePasswordToken();
		forgatePasswordToken.setExpireTimel(forgotPasswordService.expireTimeRange());
		forgatePasswordToken.setToken(forgotPasswordService.generateToken());
		forgatePasswordToken.setUser(user);
		forgatePasswordToken.setUsed(false);
		String emailLink = "http://localhost:80000/reset-password?token=" + forgatePasswordToken.getToken();
		forgotPasswordService.sendEmail(user.getUsername(), "Password Rest Link", emailLink);

		return "password request successfully.!";
	}

	@GetMapping("/rest-password")
	public String resetPassoword(@Param(value = "token") String token,Model model) {

		ForgatePasswordToken forgatePasswordToken = forgatePasswordTokenRepository.findByToken(token);
		return forgotPasswordService.checkValidity(forgatePasswordToken, model);
	}
	
	@PostMapping("/rest-password")
	public String saveresetPassoword(HttpServletRequest httpServletRequest, HttpSession httpSession) {
		
		String password = httpServletRequest.getParameter("password");
		String token = (String ) httpSession.getAttribute("token");
	  ForgatePasswordToken forgatePasswordToken =forgatePasswordTokenRepository.findByToken(token);
	  User user = forgatePasswordToken.getUser();
	  user.setPassowrd(passwordEncoder.encode(password));
	  
	  forgatePasswordToken.setUsed(true);	
	  
	  //userService.save(user); implement the UserService Interface inside
	
	  return "you have sacussfully reset the password";
	}
	

}
