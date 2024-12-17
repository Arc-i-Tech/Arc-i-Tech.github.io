package com.arcitech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.arcitech.model.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
