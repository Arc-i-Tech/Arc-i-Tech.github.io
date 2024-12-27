/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.util.Map;

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
	private static final String USERNAME_KEY  = "username"; 
	private static final String ERROR_KEY  = "error"; 
	private static final String MESSAGE_KEY  = "message"; 
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        // Check if user already exists
		if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(ERROR_KEY,"Invalid input: username is required."));
		}
		try {
        boolean exists = userService.userAlreadyExists(user);

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    		Map.of(
                                    USERNAME_KEY, user.getUsername(),
                                    ERROR_KEY, "User already exists."
                                )
                            );

        } else {
            User userAuth = userService.addUser(user);
            if (userAuth != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                		Map.of(
                                USERNAME_KEY, user.getUsername(),
                                MESSAGE_KEY, "User added successfully."
                            )
                        );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    		Map.of(
                                    USERNAME_KEY, user.getUsername(),
                                    ERROR_KEY, "Failed to add user due to invalid data."
                                )
                            );
            }
        }
        }catch(Exception e) {
        	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                     Map.of(
                         ERROR_KEY, "An unexpected error occurred.",
                         "details", e.getMessage()
                     )
                 );
        }
    
}
}
	
