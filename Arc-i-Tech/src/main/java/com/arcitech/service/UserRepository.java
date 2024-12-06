/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcitech.model.User;

/**
 * @author AJ
 * 
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
