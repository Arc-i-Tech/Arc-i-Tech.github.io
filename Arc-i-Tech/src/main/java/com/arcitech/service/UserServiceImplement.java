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

	/**
	 * {@inheritDoc}
	 *
	 */
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthRepository userAuthRepository;
	
	@Override
	public String addUser(User user) {
		UserAuth msg = userAuthRepository.findByUsername(user.getUsername());
		if(msg!=null)
		{
			return "user already exist..";
		}
		
		
		if(user==null)
		{
			return "user not save successfully";
		}
		else
		{
			userRepository.save(user);
			String GeneratedPassword = RandomPasswordGenerater.getAlphaNumericString(7);
			UserAuth userAuth = new UserAuth();
			userAuth.setUsername(user.getUsername());
			userAuth.setPassword(GeneratedPassword);
			userAuthRepository.save(userAuth);
			return "user save successfully";
		}
		
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
