/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arcitech.model.User;
import com.arcitech.repository.UserRepository;
import com.arcitech.service.UserService;

/**
 * @author Ajay G
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	// Constructor injection for UserRepository
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> updateUser(User user) {
		User foundUser = userRepository.findById(user.getId()).get();
		if (!foundUser.getUsername().equals(user.getUsername())) {
			// Username update not supported yet
			user.setUsername(foundUser.getUsername());
		}
		user.copy(foundUser);
		return Optional.of(userRepository.save(foundUser));
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean isPresent(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean isPresent(Long id) {
		return userRepository.findById(id).isPresent();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public Optional<User> getUser(String username) {
		return userRepository.findByUsername(username);
	}
}
