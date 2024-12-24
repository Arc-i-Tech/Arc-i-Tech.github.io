/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.service.UserService;

/**
 * @author Priya
 * 
 */
@Controller
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("findByUserId/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		// Retrieve the user by ID using the service method
		User user = userService.getUserById(id);
		// If the user is found, return 200 OK, else return 404 Not Found
		if(user==null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
			}
	}
	// Create a new user
	@PostMapping("/adduser")
	public ResponseEntity<UserAuth> createUser(@RequestBody User user) {
		// Call the service method to add the user
		UserAuth userAuth = userService.addUser(user);
		// If user creation was successful, return 201 Created, else return 500 Internal Server Error
	    if (userAuth!=null) {
	        return new ResponseEntity<>(userAuth, HttpStatus.CREATED);
	    }
	    else {
	        return new ResponseEntity<>(userAuth, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
