/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.repository;

/**
 * @author Dipika
 * 
 */
import com.arcitech.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}

