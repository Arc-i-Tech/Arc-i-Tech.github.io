package com.arcitech.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidation {

	public static boolean username(String username) {

		String regex = "^[A-Za-z]\\w{5,29}$";
		Pattern p = Pattern.compile(regex);

		if (username != null) {
			Matcher m = p.matcher(username);
			return m.matches();
		}

		return false;

	}

}
