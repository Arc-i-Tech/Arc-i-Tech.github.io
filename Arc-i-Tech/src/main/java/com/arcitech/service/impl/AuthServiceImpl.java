/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arcitech.model.interfaces.IAuth;
import com.arcitech.repository.UserAuthRepository;

/**
 * @author Ajay G
 * 
 */
@Service
public class AuthServiceImpl implements UserDetailsService {

	@Autowired
	UserAuthRepository userAuthRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<?> auth = userAuthRepository.findByUsername(username);
		if (!auth.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(((IAuth) auth.get()).getUsername(),
				((IAuth) auth.get()).getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}
}