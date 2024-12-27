/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;

/**
 * @author Priya
 * 
 */
public interface UserService {
	public User addUser (User user);
	public boolean userAlreadyExists(User user);

	/**
	 * @param id
	 * @return
	 */
}

