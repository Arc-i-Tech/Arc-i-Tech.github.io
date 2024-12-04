/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

/**
 * @author Dipika
 * 
 */
import com.arcitech.model.User;
import com.arcitech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private UserService userService;

    // Update User Profile
    @PutMapping("/update")
    public User updateUserProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }

    // Delete User Profile
    @DeleteMapping("/delete/{id}")
    public String deleteUserProfile(@PathVariable Long id) {
        userService.deleteUserProfile(id);
        return "User deleted successfully";
    }

}
