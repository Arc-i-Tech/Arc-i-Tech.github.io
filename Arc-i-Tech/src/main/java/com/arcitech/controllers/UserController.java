/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.service.CustomDetailService;
import com.arcitech.service.UserService;

/**
 * @author AJ
 * 
 */

@Controller
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	CustomDetailService customDetailService;

	@SuppressWarnings("unused")
	@PostMapping("/addUsers")
	public ResponseEntity<String> addUser(@RequestBody User user, Principal principal) {

		System.err.println(user.getUsername());
		String msg = userService.addUser(user);
		System.err.println(msg);

		if (principal != null) {
			UserDetails userDetails = customDetailService.loadUserByUsername(principal.getName());
		} else {
			return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
		}

		try {
			String message = userService.addUser(user);
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		String response = userService.deleteUser(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		String response = userService.updateUser(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody UserAuth userAuth) {
		System.out.println("Login attempt for username: " + userAuth.getUsername());
		String msg = userService.userAuthLogin(userAuth);
		return new ResponseEntity<>(msg, msg != null && !msg.isEmpty() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/")
	public String welcome() {

		return "welcome message";
	}

}
