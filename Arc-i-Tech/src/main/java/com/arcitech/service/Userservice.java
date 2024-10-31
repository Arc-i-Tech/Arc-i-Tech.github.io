/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import com.arcitech.model.User;

/**
 * @author AJ
 * 
 */
public interface Userservice {

	/**
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

}
