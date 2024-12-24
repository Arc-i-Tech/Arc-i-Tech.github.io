/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import java.util.Optional;

import com.arcitech.model.User;

/**
 * @author Ajay G
 * 
 */
public interface UserService {
	public Optional<User> getUser(String username);
}
