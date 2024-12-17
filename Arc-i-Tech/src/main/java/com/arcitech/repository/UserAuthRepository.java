/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.repository;

import com.arcitech.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Ajay G
 * 
 */
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
	Optional<UserAuth> findByUsername(String username);
}