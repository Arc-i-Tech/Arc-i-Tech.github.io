package com.arcitech.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PinCodeValidation {

	public static boolean validPinCode(int pincode) {
		String regex = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
		Pattern p = Pattern.compile(regex);

		if (pincode == 0)
			return false;
		else {
			Matcher m = p.matcher(String.valueOf(pincode));
			return m.matches();
		}
	}

}
