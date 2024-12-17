package com.arcitech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthRepository userAuthRepository;

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			initializeUsers();
		}
	}

	private void initializeUsers() {
		User user = new User();
		user.setUsername("ajayg2808");
		user.setName("Ajay Gaikwad");
		user.setMoNo("9156974513");
		user.setCity("Kaij");
		user.setPincode(431123);
		userRepository.save(user);

		UserAuth userAuth = new UserAuth();
		userAuth.setUsername(user.getUsername());
		userAuth.setPassword(new BCryptPasswordEncoder().encode("ajay1234"));
		userAuth.setUser(user);
		userAuthRepository.save(userAuth);
	}
}
