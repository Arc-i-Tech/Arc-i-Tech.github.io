/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcitech.model.User;

/**
 * @author Priya
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
