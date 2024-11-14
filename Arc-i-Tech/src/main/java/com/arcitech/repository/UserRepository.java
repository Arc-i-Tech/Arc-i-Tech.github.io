package com.arcitech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcitech.model.User;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	  public User findByUsername(String username);
	  public User findByUsernameAndDob(String username, LocalDate dob);

}
