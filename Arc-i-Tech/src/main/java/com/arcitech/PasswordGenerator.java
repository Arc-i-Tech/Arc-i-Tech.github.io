/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech;

import java.security.SecureRandom;

/**
 * @author AJ
 * 
 */
public class PasswordGenerator {
	public static String getAlphaNumericString(int n) {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int randomIndex = random.nextInt(alphaNumericString.length());
            char randomChar = alphaNumericString.charAt(randomIndex);
            sb.append(randomChar);
        }
        
		return sb.toString();
	}
}
