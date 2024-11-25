package com.arcitech.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation {

	public static boolean validName(String name) {
		String expression = "^[a-zA-Z]+([ '-][a-zA-Z]+)*$"; 

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			return true;
		}

		return false;

	}

}
