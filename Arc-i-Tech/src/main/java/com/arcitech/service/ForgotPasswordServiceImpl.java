package com.arcitech.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.arcitech.exceptions.UserNotFoundException;
import com.arcitech.model.ForgotPasswordToken;
import com.arcitech.model.User;
import com.arcitech.model.UserAuth;
import com.arcitech.repository.ForgotPasswordRepository;
import com.arcitech.repository.UserAuthRepository;
import com.arcitech.repository.UserRepository;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ForgotPasswordRepository forgotPasswordRepository;

	@Autowired
	UserAuthRepository authRepository;

	public void updateRestPasswordToken(String token, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        User user = optionalUser.get();

        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
        forgotPasswordToken.setToken(token);
        forgotPasswordToken.setUser(user);

        forgotPasswordRepository.save(forgotPasswordToken);
    }


	public ForgotPasswordToken getUser(String resetPaswordToken) {
		return forgotPasswordRepository.findByToken(resetPaswordToken)
                .orElseThrow(() -> new UserNotFoundException("Token not found or expired."));
	}

	@Override
	public void updatePassword(ForgotPasswordToken forgotPasswordToken, String newPassword) {
	    User user = forgotPasswordToken.getUser();
	    UserAuth userAuth = user.getUserAuth();

	    if (userAuth == null) {
	        throw new UserNotFoundException("No UserAuth found for the provided user.");
	    }

	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    userAuth.setPassword(bCryptPasswordEncoder.encode(newPassword));

	    authRepository.save(userAuth);
	}

	 @Override
	    public ForgotPasswordToken validateToken(String token) {
	        return forgotPasswordRepository.findByToken(token)
	                .orElseThrow(() -> new UserNotFoundException("Invalid or expired token."));
	    }
}
