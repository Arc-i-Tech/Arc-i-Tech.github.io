/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;
import com.arcitech.service.UserService;

/**
 * @author Ajay G
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	private UserAuthRepository userAuthRepository;

	String otp;

	// Constructor injection for UserRepository
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public Optional<User> getUser(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public Optional<User> resetPassword(UserAuth userAuth) {
		Optional<UserAuth> userAuthToUpdate = this.userAuthRepository.findByUsername(userAuth.getUsername());
		if (userAuthToUpdate.isPresent()) {
			userAuthToUpdate.get().setPassword(new BCryptPasswordEncoder().encode(userAuth.getPassword()));
			UserAuth savedUserAuth = this.userAuthRepository.save(userAuthToUpdate.get());
			return Optional.of(savedUserAuth.getUser());
		}
		return Optional.empty();
	}

}
