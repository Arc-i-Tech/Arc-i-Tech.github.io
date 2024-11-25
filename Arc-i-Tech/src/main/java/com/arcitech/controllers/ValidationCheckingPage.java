package com.arcitech.controllers;

import org.jboss.logging.Logger;

import com.arcitech.validations.ContactNumberValdiation;
import com.arcitech.validations.EmailValidation;
import com.arcitech.validations.NameValidation;
import com.arcitech.validations.PasswordAndConfirmPasswordValidation;
import com.arcitech.validations.PinCodeValidation;
import com.arcitech.validations.UsernameValidation;

public class ValidationCheckingPage {

	private static final Logger logger = Logger.getLogger(ValidationCheckingPage.class);

	public static void main(String[] args) {
		// Contact Number Validation
		String contactNumber = "9876541321";
		boolean ContactNumberValid = ContactNumberValdiation.validContactNumber(contactNumber);
		logger.info("Contact Number Validation: " + ContactNumberValid);

		// Email Validation
		String email = "ajdeshmukh241@gmail.com";
		boolean EmailValid = EmailValidation.emailValidation(email);
		logger.info("Email Validation: " + EmailValid);

		// FullName Validation
		String fullname = "Ajay Sambhaji Deshmukh";

		boolean name = NameValidation.validName(fullname);

		logger.info("Name is " + name);

		// Password And Confirm Password Validation
		String newPassword = "#ajstyle@Gmail.com";
		String confirmPassword = "#ajstyle@Gmail.com";

		boolean passwordAndConfirmPassword = PasswordAndConfirmPasswordValidation.PasswordValidations(newPassword,
				confirmPassword);

		logger.info("Password and Confirm Pssword " + passwordAndConfirmPassword);

		// Pin Code Validation

		boolean pincode = PinCodeValidation.validPinCode(123456);
		logger.info("Pin code is " + pincode);

		
		// Username Validation

		boolean username = UsernameValidation.username("ajstyle");

		logger.info("username is " + username);

	}

}
