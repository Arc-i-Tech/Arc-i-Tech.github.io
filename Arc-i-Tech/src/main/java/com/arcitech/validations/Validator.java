/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author ajdes
 * 
 */

public class Validator {
	private final String value;
	private final List<String> errors = new ArrayList<>();

	private static final Pattern ALPHABETS = Pattern.compile("^[A-Za-z]+$");
	private static final Pattern ALPHABETS_WITH_SPACES = Pattern.compile("^[A-Za-z\\s]+$");
	private static final Pattern NUMERIC = Pattern.compile("^\\d+$");
	private static final Pattern EMAIL = Pattern.compile(
		    "^[\\w._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$",
		    Pattern.CASE_INSENSITIVE
		);
	private static final Pattern PINCODE = Pattern.compile("^\\d{6}$");

	private Validator(String value) {
		this.value = value;
	}

	public static Validator of(String value) {
		return new Validator(value);
	}

	public Validator isValidEmail(String errorMessage) {
		return matchesRegex(EMAIL, errorMessage);
	}

	public Validator isValidPinCode(String errorMessage) {
		return matchesRegex(PINCODE, errorMessage);
	}

	public Validator isNotBlank(String errorMessage) {
		if (value == null || value.trim().isEmpty()) {
			errors.add(errorMessage);
		}
		return this;
	}

	public Validator onlyAlphabets(String errorMessage) {
		if (value != null && !ALPHABETS.matcher(value).matches()) {
			errors.add(errorMessage);
		}
		return this;
	}

	public Validator minLength(int length, String errorMessage) {
		if (value == null || value.length() < length) {
			errors.add(errorMessage);
		}
		return this;
	}

	public Validator maxLength(int length, String errorMessage) {
		if (value != null && value.length() > length) {
			errors.add(errorMessage);
		}
		return this;
	}

	public Validator containsNumber(String errorMessage) {
		if (value != null && !value.chars().anyMatch(Character::isDigit)) {
			errors.add(errorMessage);
		}
		return this;
	}

	public Validator containsAlphabet(String errorMessage) {
		if (value != null && !value.chars().anyMatch(Character::isLetter)) {
			errors.add(errorMessage);
		}
		return this;
	}

	public Validator matchesRegex(Pattern pattern, String errorMessage) {
		Objects.requireNonNull(pattern, "Regex pattern cannot be null");
		if (value != null && !pattern.matcher(value).matches()) {
			errors.add(errorMessage);
		}
		return this;
	}

}
