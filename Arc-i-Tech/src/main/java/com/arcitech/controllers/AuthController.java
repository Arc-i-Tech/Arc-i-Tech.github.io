/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcitech.dto.LoginRequest;
import com.arcitech.dto.ResetPasswordRequest;
import com.arcitech.dto.SendOtpRequest;
import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.service.UserService;
import com.arcitech.service.impl.AuthServiceImpl;
import com.arcitech.service.impl.OTPService;
import com.arcitech.service.impl.SMSService;
import com.arcitech.utils.OTP;

/**
 * AUthetication controller for providing authentication
 * 
 * @author Ajay G
 * 
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final String ERROR_KEY = "error";
	private static final String MESSAGE_KEY = "success";
	private static final String USERNAME_KEY = "username";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@Autowired
	private UserService userService;

	@Autowired
	private OTPService otpService;

	@Autowired
	private SMSService smsService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

			// Retrieve the user details

			Optional<?> result = Optional.empty();

			UserDetails userDetails = authServiceImpl.loadUserByUsername(loginRequest.getUsername());
			if (userDetails.getAuthorities().stream()
					.anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
				result = userService.getUser(loginRequest.getUsername());
			}
			if (result.isPresent()) {
				return ResponseEntity.ok(result.get());
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
					.body("{\"error\":\"Content not found.\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON)
					.body("{\"Username\":\"" + loginRequest.getUsername()
							+ "\",\n\"error\":\"Invalid username or password\"}");
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("User logged out successfully");
	}

	@PostMapping("/send-otp")
	public ResponseEntity<?> sendOtp(@RequestBody SendOtpRequest sendOtpRequest, HttpSession httpSession) {
		Optional<User> user = this.userService.getUser(sendOtpRequest.getUsername());
		if (user.isPresent()) {
			// Generate token and store it in session with expiry and username
			OTP otp = otpService.generateOTP(6);
			httpSession.setAttribute(sendOtpRequest.getUsername(), otp);

			smsService.sendSMS(user.get().getMoNo(), "Your OTP to reset Password is: " + otp.getOtpString());
			String maskedMoNo = new StringBuilder(user.get().getMoNo()).replace(2, 8, "****").toString();
			return ResponseEntity.status(HttpStatus.OK)
					.body(Map.of(USERNAME_KEY, sendOtpRequest.getUsername(), MESSAGE_KEY,
							"OTP sent successfully to registered mobile number.", "Mobile No", maskedMoNo));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(Map.of(USERNAME_KEY, sendOtpRequest.getUsername(), ERROR_KEY, "User not found."));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest,
			HttpSession httpSession) {
		Optional<User> user = this.userService.getUser(resetPasswordRequest.getUsername());
		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(Map.of(USERNAME_KEY, resetPasswordRequest.getUsername(), ERROR_KEY, "User not found."));
		}
		OTP otp = (OTP) httpSession.getAttribute(resetPasswordRequest.getUsername());
		if (otp == null || !otp.getOtpString().equalsIgnoreCase(resetPasswordRequest.getOtpStr())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(USERNAME_KEY,
					resetPasswordRequest.getUsername(), ERROR_KEY, "OTP not found. or OTP mismatch."));
		}
		int isExpired = LocalDateTime.now().compareTo(otp.getCreateDAt().plusMinutes(otp.getExpiryTime()));
		if (isExpired > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of(USERNAME_KEY, resetPasswordRequest.getUsername(), ERROR_KEY, "OTP expired."));
		}
		UserAuth userAuth = (UserAuth) resetPasswordRequest.toAuth(new UserAuth());
		Optional<User> updatedUser = userService.resetPassword(userAuth);
		if (updatedUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(USERNAME_KEY, resetPasswordRequest.getUsername(), ERROR_KEY, "Password update failed."));
		}
		return ResponseEntity.accepted().body(updatedUser.get());
	}
}
