/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

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
import com.arcitech.service.UserService;
import com.arcitech.service.impl.AuthServiceImpl;

/**
 * AUthetication controller for providing authentication
 * 
 * @author Ajay G
 * 
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@Autowired
	private UserService userService;

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
}
