/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcitech.model.User;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.service.UserService;

/**
 * @author Priya
 * 
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {
	private static final String USERNAME_KEY = "username";
	private static final String ERROR_KEY = "error";

	@Autowired
	private UserService userService;

	@Autowired
	private UserAuthRepository userAuthRepository;

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		// Check if user is null or required fields are missing
		if (user == null || user.getUsername() == null || user.getUsername().isEmpty() || user.getName() == null
				|| user.getName().isEmpty() || user.getCity() == null || user.getCity().isEmpty()
				|| user.getPincode() <= 0 || user.getMoNo() == null || user.getMoNo().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(ERROR_KEY,
					"Missing user details!, Check {Username}, {Name}, {City}, {Mobile No}, {Pincode}."));
		}
		boolean exists = this.userService.isExists(user.getUsername());
		if (exists) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(Map.of(USERNAME_KEY, user.getUsername(), ERROR_KEY, "Username already exists."));
		}

		Optional<User> savedUser = this.userService.addUser(user);
		if (savedUser.isPresent()) {
			Optional<?> userAuth = userAuthRepository.findByUsername(savedUser.get().getUsername());
			if (userAuth.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(userAuth.get());
			}
			// TODO Delete user if UserAuth no created.
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(Map.of(USERNAME_KEY, user.getUsername(), ERROR_KEY, "Failed to create new authorized user."));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Map.of(USERNAME_KEY, user.getUsername(), ERROR_KEY, "Failed to add user."));
	}
}
