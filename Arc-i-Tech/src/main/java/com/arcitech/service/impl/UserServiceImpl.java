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
	public User addUser(User user) {
		// Check if user is null or required fields are missing
		if (user == null && 
		        user.getUsername() == null && user.getUsername().isEmpty() && 
		        user.getName() == null && user.getName().isEmpty() &&
		        user.getCity() == null && user.getCity().isEmpty() && 
		        user.getPincode() <= 0 && 
		        user.getMoNo() == null && user.getMoNo().isEmpty()) {
		       	return null;
		    }
	    try {
	    	// Save the user to the user repository
	        userRepository.save(user);
	        // Generate a random password for the new user
	        String generatedPassword = RandomPasswordGenerator.getAlphaNumericString(7);
	        // Create a new UserAuth object for authentication details
	        UserAuth userAuth = new UserAuth();
	        userAuth.setUsername(user.getUsername());
	        userAuth.setPassword(generatedPassword);
	        userAuth.setUser(user); 
	        userAuthRepository.save(userAuth);
	        return user;
	    } catch (Exception e) {
	    	 return null;
	    }
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean userAlreadyExists(User user) {
		if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Invalid user data");
        }
		Optional<?> existingAuth = userAuthRepository.findByUsername(user.getUsername());
	    return existingAuth.isPresent(); 
	}

	
}

		