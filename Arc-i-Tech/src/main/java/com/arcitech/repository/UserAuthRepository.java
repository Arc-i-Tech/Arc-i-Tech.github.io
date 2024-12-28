/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcitech.model.UserAuth;

/**
 * @author Ajay G
 * 
 */
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
	Optional<?> findByUsername(String username);
}