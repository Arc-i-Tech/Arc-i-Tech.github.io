/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import com.arcitech.model.User;

/**
 * @author Priya
 * 
 */
public interface UserService {
	
	public String addUser (User user);

	/**
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
}
