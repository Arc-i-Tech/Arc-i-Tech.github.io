package com.arcitech.validations;

public class EmailValidation {

	public static boolean emailValidation(String email) {

		if (email.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}"))
			return true;
		else
			return false;
	}
}
