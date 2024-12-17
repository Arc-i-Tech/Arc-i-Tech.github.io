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

import com.arcitech.helper.RandomPasswordGenerater;
import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;

/**
 * @author Priya
 * 
 */
@Service
public class UserServiceImplement implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthRepository userAuthRepository;
	
	private static final Logger logger = LoggerFactory.logger(UserServiceImplement.class); 
	
	@Override
	public String addUser(User user) {
	    if (user == null && 
	        user.getUsername() == null && user.getUsername().isEmpty() && 
	        user.getName() == null && user.getName().isEmpty() &&
	        user.getEmail() == null && user.getEmail().isEmpty() && 
	        user.getCity() == null && user.getCity().isEmpty() && 
	        user.getPincode() <= 0 && 
	        user.getMoNo() <= 0) {
	        return "User not saved successfully. Please ensure all required fields are filled.";
	    }

	    UserAuth existingAuth = userAuthRepository.findByUsername(user.getUsername());
	    if (existingAuth != null) {
	        return "User not saved successfully. Username already exists.";
	    }

	    try {
	        userRepository.save(user);
	        String generatedPassword = RandomPasswordGenerater.getAlphaNumericString(7);
	        
	        UserAuth userAuth = new UserAuth();
	        userAuth.setUsername(user.getUsername());
	        userAuth.setPassword(generatedPassword);
	        userAuth.setUser(user); 
	        
	        userAuthRepository.save(userAuth);
	        return "User saved successfully. Generated password: " + generatedPassword;
	    } catch (Exception e) {
	    	logger.error("Error saving user: " + e.getMessage());
	    	return "User not saved successfully. Please check your input and try again.";
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