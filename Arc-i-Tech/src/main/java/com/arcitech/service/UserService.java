/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import java.util.Optional;

import com.arcitech.model.User;

/**
 * @author Priya
 * 
 */
public interface UserService {
	public Optional<User> addUser(User user);

	public boolean isExists(String username);

}
