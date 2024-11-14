package com.arcitech.service;


import java.util.Arrays;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arcitech.CustomDetails;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;



@Service
public class CustomDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserAuthRepository userAuthRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		UserAuth userAuth = userAuthRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new CustomDetails(username, userAuth.getPassword(), getAuthorities(), username);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}