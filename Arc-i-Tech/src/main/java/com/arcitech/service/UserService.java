package com.arcitech.service;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;

public interface UserService {
	public String userAuthLogin(UserAuth userAuth);
	public String addUser(User user);
	public String deleteUser(long id);
	/**
	 * @param user
	 * @return
	 */
	public String updateUser(User user);
}
