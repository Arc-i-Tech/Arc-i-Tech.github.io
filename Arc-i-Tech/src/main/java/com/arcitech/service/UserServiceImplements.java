/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;
import com.arcitech.model.User;
import com.arcitech.repository.UserRepository;

import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Priya
 * 
 */
@Server
public class UserServiceImplements implements UserService {
	private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public UserServiceImplements(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User userUpdate(User user) {
        if (user == null) 
        	return null;
        else {
        	User findUser = userRepository.findById(user.getId()).get();
   			findUser.setUsername(user.getUsername());
   			findUser.setDob(user.getDob());
   			findUser.setAddress(user.getAddress());
   			findUser.setMoNo(user.getMoNo());
   			findUser.setCity(user.getCity());
   			findUser.setPincode(user.getPincode());

   			return userRepository.save(findUser);
   		}

   	}

}

	

