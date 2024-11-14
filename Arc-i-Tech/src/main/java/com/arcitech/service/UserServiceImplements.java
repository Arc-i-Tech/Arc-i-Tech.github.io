package com.arcitech.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcitech.PasswordGenerator;
import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;

@Service
public class UserServiceImplements implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAuthRepository authRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String userAuthLogin(UserAuth userAuth) {
		System.out.println(userAuth.toString());

		UserAuth user = authRepository.findByUsername(userAuth.getUsername());

		if (user != null && passwordEncoder.matches(userAuth.getPassword(), user.getPassword())) {
			return "Login successful!";
		} else {
			return "Invalid username or password!";
		}
	}

	@Override
	@Transactional
	public String addUser(User user) {

		User existUser = userRepository.findByUsernameAndDob(user.getUsername(), user.getDob());

		if (existUser != null) {
			return "User Already exists..........";
		}
		try {
			String password = PasswordGenerator.getAlphaNumericString(10);

			userRepository.save(user);

			UserAuth auth = new UserAuth();
			auth.setUsername(user.getUsername());
			auth.setPassword(passwordEncoder.encode(password));
			auth.setUser(user);
			authRepository.save(auth);

			return "Data saved successfully " + password;
		} catch (DataIntegrityViolationException ex) {
			return "A user with this username already exists.";

		}
	}

	@Transactional
	public String deleteUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return "User deleted successfully!";
		} else {
			return "User not found!";
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String updateUser(User user) {

		if (user != null) {
			User updatedUser = userRepository.findById(user.getId()).get();
			updatedUser.setName(user.getName());
			updatedUser.setAddress(user.getAddress());
			updatedUser.setCity(user.getCity());
			updatedUser.setDob(user.getDob());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setPincode(user.getPincode());
			updatedUser.setUsername(user.getUsername());

			userRepository.save(updatedUser);
			return "User updated Successfully.........";
		}

		return "User not updated.........";
	}

}