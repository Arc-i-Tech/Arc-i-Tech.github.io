/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved. visit www.arc-i-tech.in
 */
package com.arcitech.validations;


/**
 * @author GJI1KOR Implement this interface for validation.
 */
public interface IValidator {

  /**
   * Validate the input string based on the configured rules.
   * 
   * @param input the string to validate
   * @return true if the input satisfies all rules, false otherwise
   */
  boolean validate(String input);

}
