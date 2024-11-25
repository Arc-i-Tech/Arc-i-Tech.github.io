package com.arcitech.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactNumberValdiation {

	public static boolean validContactNumber(String contactNumber) {
		Pattern p = Pattern.compile("^\\d{10}$");

		Matcher m = p.matcher(contactNumber);

		if (m.matches())
			return true;
		else
			return false;

	}
}
