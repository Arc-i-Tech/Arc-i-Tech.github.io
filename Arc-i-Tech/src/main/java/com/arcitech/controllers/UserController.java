/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.service.UserService;
/**
 * @author Priya
 * 
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        // Check if user already exists
        boolean exists = userService.userAlreadyExists(user);

        if (exists) {
            return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"Username\":\"" + user.getUsername() + "\",\"error\":\"User Already Exists..\"}");
        } else {
            UserAuth userAuth = userService.addUser(user);
            if (userAuth != null) {
                return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"Username\":\"" + user.getUsername() + "\",\"message\":\"User added successfully\"}");
            } else {
                return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"Username\":\"" + user.getUsername() + "\",\"error\":\"User could not be saved\"}");
            }
        }
    }
}
