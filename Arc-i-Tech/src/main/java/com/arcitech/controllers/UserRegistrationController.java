/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.arcitech.model.User;
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
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		User user = userService.getUserById(id);
		if(user==null){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/adduser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
	    String response = userService.addUser(user);
	    if (response.contains("successfully")) {
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } else if (response.contains("already exists")) {
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    } else {
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}