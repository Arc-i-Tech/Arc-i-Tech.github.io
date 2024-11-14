package com.arcitech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcitech.model.UserAuth;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

	public UserAuth findByUsername(String username);

	public UserAuth findByUsernameAndPassword(String username, String password);
}
