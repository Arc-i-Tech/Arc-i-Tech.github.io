/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcitech.model.User;
import com.arcitech.service.UserService;

/**
 * @author Priya
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/{username}/update")
	public ResponseEntity<?> addUser(@RequestBody User user, @PathVariable String username) {
		if (user == null) {
			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON)
					.body("{\"Username\":\"" + username + "\",\n\"error\":\"Null or Empty user details.\"}");
		}

		String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		if (!username.equalsIgnoreCase(loggedInUsername)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON)
					.body("{\"Username\":\"" + username + "\",\n\"error\":\"You can only update your own details.\"}");
		}

		if (!(userService.isPresent(username) && userService.isPresent(user.getId()))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
					.body("{\"Username\":\"" + username + "\",\n\"error\":\"Username not found.\"}");
		}

		Optional<User> updatedUser = userService.updateUser(user);

		if (updatedUser.isPresent()) {
			return ResponseEntity.ok(updatedUser);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body("{\"Username\":\"" + username + "\",\n\"error\":\"Update failed.\"}");
		}
	}

}
