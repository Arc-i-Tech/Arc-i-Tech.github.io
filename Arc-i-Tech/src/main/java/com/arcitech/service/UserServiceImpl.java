/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AJ
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
  private  UserRepository userRepository;
private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

  @Override
  public String deleteUserProfile(long id) {
    LOGGER.info("Deleting user with ID: " + id);
    try {
      userRepository.deleteById(id);
      LOGGER.info("User ID " + id + " deleted successfully.");
      return "User ID " + id + " deleted successfully.";
    } catch (Exception e) {
      LOGGER.error("Error occurred while deleting user ID " + id, e);
      return "Error deleting user ID " + id + ". Please try again later.";
    }
  }
}
