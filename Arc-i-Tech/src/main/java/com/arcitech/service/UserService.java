package com.arcitech.service;

import com.arcitech.model.User;

public interface UserService {
	public User updateUserProfile(User user);

	public void deleteUserProfile(Long id);
}
