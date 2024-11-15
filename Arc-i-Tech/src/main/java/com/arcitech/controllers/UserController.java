/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcitech.model.User;
import com.arcitech.service.UserService;

/**
 * @author Priya
 * 
 */
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/updateUserProfile")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		if (user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		User updateUser = userService.userUpdate(user);

		return updateUser != null ? new ResponseEntity<>(updateUser, HttpStatus.ACCEPTED)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
