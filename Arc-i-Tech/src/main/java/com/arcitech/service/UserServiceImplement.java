/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcitech.helper.RandomPasswordGenerator;
import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;

/**
 * @author Priya
 * 
 */
@Service
public class UserServiceImplement implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthRepository userAuthRepository;
	
	private static final Logger logger = LoggerFactory.logger(UserServiceImplement.class); 

	@Override
	public UserAuth addUser(User user) {
		// Check if user is null or required fields are missing
		if (user == null && 
		        user.getUsername() == null && user.getUsername().isEmpty() && 
		        user.getName() == null && user.getName().isEmpty() &&
		        user.getCity() == null && user.getCity().isEmpty() && 
		        user.getPincode() <= 0 && 
		        user.getMoNo() <= 0) {
		        return null;  // Invalid user data
		    }
		// Check if the username already exists
		UserAuth existingAuth = userAuthRepository.findByUsername(user.getUsername());
	    if (existingAuth != null) {
	        return existingAuth;
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
	        // Save the authentication details to the userAuth repository
	        userAuthRepository.save(userAuth);
	        return userAuth;
	    } catch (Exception e) {
	    	// Log the error and return null in case of failure
	    	logger.error("Error saving user: " + e.getMessage());
	    	return null;
	    }
	}
	
	@Override
	public User getUserById(Long id) {
		User user = userRepository.findById(id).get();
		if(user==null)
		{
			return null;
		}
		return user;
	}

}

		