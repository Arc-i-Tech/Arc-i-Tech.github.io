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
import com.arcitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
