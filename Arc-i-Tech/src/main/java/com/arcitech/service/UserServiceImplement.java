/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service;

/**
 * @author Dipika
 * 
 */
import com.arcitech.model.User;
import com.arcitech.repository.UserRepository;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserServiceImplement implements UserService {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Transactional
    public User updateUserProfile(User user) {
        User existingUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setDob(user.getDob());
        existingUser.setAddress(user.getAddress());
        existingUser.setMoNo(user.getMoNo());
        existingUser.setCity(user.getCity());
        existingUser.setPincode(user.getPincode());

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.isEnabled() != existingUser.isEnabled()) {
            existingUser.setEnabled(user.isEnabled());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUserProfile(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        userAuthRepository.deleteById(id); 
        userRepository.delete(user);       
    }

}
