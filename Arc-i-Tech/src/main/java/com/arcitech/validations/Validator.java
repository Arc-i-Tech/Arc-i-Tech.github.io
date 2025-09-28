/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved. visit www.arc-i-tech.in
 */
package com.arcitech.validations;

import java.util.regex.Pattern;

/**
 * A flexible Validator for validating string data using the Builder pattern. Supports rules like length checks,
 * alphanumeric constraints, regex validation, and password strength rules.
 * 
 * @author GJI1KOR
 */
public final class Validator implements IValidator {

  private final Integer minLength;
  private final Integer maxLength;
  private final boolean onlyNumeric;
  private final boolean onlyAlphabets;
  private final boolean onlyAlphanumeric;
  private final Pattern regexPattern;
  // Password-specific rules
  private final boolean requireUppercase;
  private final boolean requireLowercase;
  private final boolean requireDigit;
  private final boolean requireSpecialChar;
  // Null handling
  private final boolean allowNull;

  // Define allowed special characters (safe printable set, excludes path/formatting chars)
  private static final String SAFE_SPECIALS = "!@#$%^&*()_+=,.?~-";
  // Regex to check for at least one safe special char
  private static final Pattern SAFE_SPECIAL_PATTERN = Pattern.compile(".*[" + Pattern.quote(SAFE_SPECIALS) + "].*");

  /**
   * @param builder - String which is being validated
   */
  private Validator(Builder builder) {
    this.minLength = builder.minLength;
    this.maxLength = builder.maxLength;
    this.onlyNumeric = builder.onlyNumeric;
    this.onlyAlphabets = builder.onlyAlphabets;
    this.onlyAlphanumeric = builder.onlyAlphanumeric;
    this.regexPattern = builder.regexPattern;
    this.requireUppercase = builder.requireUppercase;
    this.requireLowercase = builder.requireLowercase;
    this.requireDigit = builder.requireDigit;
    this.requireSpecialChar = builder.requireSpecialChar;
    this.allowNull = builder.allowNull;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(String input) {
    if (input == null) {
      return allowNull;
    }
    return checkLength(input) && checkCharacterTypes(input) && checkRegex(input) && checkPasswordRules(input);
  }

  // --- Private helper methods ---
  private boolean checkLength(String input) {
    if (minLength != null && input.length() < minLength) {
      return false;
    }
    return !(maxLength != null && input.length() > maxLength);

  }

  private boolean checkCharacterTypes(String input) {
    if (onlyNumeric && !input.matches("\\d+")) {
      return false;
    }
    if (onlyAlphabets && !input.matches("[a-zA-Z]+")) {
      return false;
    }
    return !(onlyAlphanumeric && !input.matches("[a-zA-Z0-9]+"));

  }

  private boolean checkRegex(String input) {
    return regexPattern == null || regexPattern.matcher(input).matches();
  }

  private boolean checkPasswordRules(String input) {
    if (requireUppercase && !input.matches(".*[A-Z].*")) {
      return false;
    }
    if (requireLowercase && !input.matches(".*[a-z].*")) {
      return false;
    }
    if (requireDigit && !input.matches(".*\\d.*")) {
      return false;
    }
    return !(requireSpecialChar && !SAFE_SPECIAL_PATTERN.matcher(input).matches());
  }

  /**
   * Builder class for Validator. Allows step-by-step configuration of validation rules.
   * 
   * @author GJI1KOR
   */
  public static class Builder {

    private Integer minLength;
    private Integer maxLength;
    private boolean onlyNumeric;
    private boolean onlyAlphabets;
    private boolean onlyAlphanumeric;
    private Pattern regexPattern;
    // Password-specific rules
    private boolean requireUppercase;
    private boolean requireLowercase;
    private boolean requireDigit;
    private boolean requireSpecialChar;
    // Null handling
    private boolean allowNull = false; // default false

    /**
     * Set the minimum length of the input.
     * 
     * @param minLen minimum number of characters
     * @return this builder instance
     */
    public Builder minLength(int minLen) {
      this.minLength = minLen;
      return this;
    }

    /**
     * Set the maximum length of the input.
     * 
     * @param maxLen maximum number of characters
     * @return this builder instance
     */
    public Builder maxLength(int maxLen) {
      this.maxLength = maxLen;
      return this;
    }

    /**
     * Require the input to contain only digits (0-9).
     * 
     * @return this builder instance
     */
    public Builder onlyNumeric() {
      this.onlyNumeric = true;
      return this;
    }

    /**
     * Require the input to contain only alphabets (a-z, A-Z).
     * 
     * @return this builder instance
     */
    public Builder onlyAlphabets() {
      this.onlyAlphabets = true;
      return this;
    }

    /**
     * Require the input to contain only alphanumeric characters (a-z, A-Z, 0-9).
     * 
     * @return this builder instance
     */
    public Builder onlyAlphanumeric() {
      this.onlyAlphanumeric = true;
      return this;
    }

    /**
     * Require the input to match a custom regex pattern.
     * 
     * @param regex the regex string
     * @return this builder instance
     */
    public Builder regex(String regex) {
      this.regexPattern = Pattern.compile(regex);
      return this;
    }

    // Password rule builders
    /**
     * Require the input to contain at least one uppercase letter (A-Z).
     * 
     * @return this builder instance
     */
    public Builder requireUppercase() {
      this.requireUppercase = true;
      return this;
    }

    /**
     * Require the input to contain at least one lowercase letter (a-z).
     * 
     * @return this builder instance
     */
    public Builder requireLowercase() {
      this.requireLowercase = true;
      return this;
    }

    /**
     * Require the input to contain at least one digit (0-9).
     * 
     * @return this builder instance
     */
    public Builder requireDigit() {
      this.requireDigit = true;
      return this;
    }

    /**
     * Require the input to contain at least one special character from the safe set: {@code !@#$%^&*()_+=,.?~-}.
     * 
     * @return this builder instance
     */
    public Builder requireSpecialChar() {
      this.requireSpecialChar = true;
      return this;
    }

    // Null handling
    /**
     * Allow null values as valid input. By default, null is not allowed.
     * 
     * @return this builder instance
     */
    public Builder allowNull() {
      this.allowNull = true;
      return this;
    }

    /**
     * Build the Validator instance with the configured rules.
     * 
     * @return a new Validator instance
     */
    public Validator build() {
      return new Validator(this);
    }
  }


}
