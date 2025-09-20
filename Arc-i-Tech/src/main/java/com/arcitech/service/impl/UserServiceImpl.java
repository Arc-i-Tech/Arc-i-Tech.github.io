/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcitech.helper.RandomPasswordGenerator;
import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;
import com.arcitech.service.UserService;

/**
 * @author Priya
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAuthRepository userAuthRepository;

	@Override
	public Optional<User> addUser(User user) {
		// Save the user to the user repository
		User savedUser = this.userRepository.save(user);

		// Generate a random password for the new user
		String generatedPassword = RandomPasswordGenerator.getPassword(8);

		// Create and Save UserAuth for authentication details
		UserAuth userAuth = new UserAuth();
		userAuth.setUsername(savedUser.getUsername());
		userAuth.setPassword(generatedPassword);
		userAuth.setUser(user);

		this.userAuthRepository.save(userAuth);

		return Optional.of(savedUser);
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean isExists(String username) {
		return userAuthRepository.findByUsername(username).isPresent();
	}

}
