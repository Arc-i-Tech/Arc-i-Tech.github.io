package com.arcitech.validations;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

public class PasswordAndConfirmPasswordValidation {

	Logger logger = LoggerFactory.logger(PasswordAndConfirmPasswordValidation.class);

	public static boolean PasswordValidations(String password, String confrirmPassword) {
		boolean firstPassword = validPasswordAndConfirmPasword(password);
		boolean secondPassword = validPasswordAndConfirmPasword(confrirmPassword);
		if (firstPassword && secondPassword)
			return true;
		else
			return false;
	}

	public static boolean validPasswordAndConfirmPasword(String password) {
		try {
			if (password != null) {
				String MIN_LENGTH = "8";
				String MAX_LENGTH = "20";
				boolean SPECIAL_CHAR_NEEDED = false;

				String ONE_DIGIT = "(?=.*[0-9])";
				String LOWER_CASE = "(?=.*[a-z])";
				String UPPER_CASE = "(?=.*[A-Z])";
				String SPECIAL_CHAR = SPECIAL_CHAR_NEEDED ? "(?=.*[@#$%^&+=])" : "";
				String NO_SPACE = "(?=\\S+$)";

				String MIN_MAX_CHAR = ".{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
				String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;

				if (password.matches(PATTERN))
					return true;
			}

		} catch (Exception ex) {
			Logger.getLogger(String.valueOf(ex));
		}
		return false;
	}

}
