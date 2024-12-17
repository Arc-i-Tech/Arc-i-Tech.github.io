/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Dipika
 * 
 */

import org.springframework.stereotype.Service;

import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;

/**
 * @author Ajay G
 * 
 */
@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserAuthRepository userAuthRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserAuth> userAuth = userAuthRepository.findByUsername(username);
		if (!userAuth.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(userAuth.get().getUsername(),
				userAuth.get().getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}
}