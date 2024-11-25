package com.arcitech.validations;

import java.util.regex.Pattern;

public class EmailValidation {

	public static boolean emailValidation(String email) {
		if (email == null) {
			return false;
		} else {

			final Pattern emailVarification = Pattern.compile(
					"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
					Pattern.CASE_INSENSITIVE);
			return emailVarification.matcher(email).matches();

		}
	}
}
