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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arcitech.service.UserService;

/**
 * @author AJ
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUserProfile(@PathVariable("id") long id) {
		String msg = userService.deleteUserProfile(id);

		if (msg == null)
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
}
