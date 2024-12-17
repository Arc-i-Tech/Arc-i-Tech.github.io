/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.arcitech.model.User;

/**
 * @author Ajay G
 * 
 */
@Service
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
