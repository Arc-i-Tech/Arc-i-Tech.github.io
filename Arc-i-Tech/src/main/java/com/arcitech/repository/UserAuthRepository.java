/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcitech.model.UserAuth;

/**
 * @author Priya
 * 
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
	Optional<?> findByUsername(String username);
}
