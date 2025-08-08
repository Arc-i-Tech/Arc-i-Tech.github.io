/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import java.util.Optional;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;

/**
 * @author Ajay G
 * 
 */
public interface UserService {
	public Optional<User> getUser(String username);
	
	public Optional<User> resetPassword(UserAuth userAuth);
}
