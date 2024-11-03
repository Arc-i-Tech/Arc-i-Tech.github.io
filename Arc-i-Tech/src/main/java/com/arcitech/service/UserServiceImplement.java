/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

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
	
	@Override
	public String addUser(User user) {
	    if (user == null) {
	        return "User not saved successfully";
	    }

	    // Check if the user already exists
	    UserAuth existingAuth = userAuthRepository.findByUsername(user.getUsername());
	    if (existingAuth != null) {
	        return "User already exists.";
	    }
	    // Save the User entity first
	    userRepository.save(user);
	    String generatedPassword = RandomPasswordGenerater.getAlphaNumericString(7);
	    UserAuth userAuth = new UserAuth();
	    userAuth.setUsername(user.getUsername());
	    userAuth.setPassword(generatedPassword);
	    userAuth.setUser(user); // Associate the user with UserAuth
	    // Save UserAuth with the associated User
	    userAuthRepository.save(userAuth);

	    return "User saved successfully";
	}
	/**
	 * {@inheritDoc}
	 *
	 */
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
