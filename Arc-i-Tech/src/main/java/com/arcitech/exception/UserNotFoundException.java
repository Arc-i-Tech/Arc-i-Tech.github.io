/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.exception;

/**
 * @author Dipika
 * 
 */
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L; // Add this line
	 public UserNotFoundException(String message) {
	        super(message);
	    }

}
