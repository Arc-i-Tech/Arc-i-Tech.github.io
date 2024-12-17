/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.helper;

import java.security.SecureRandom;

/**
 * @author Priya
 * 
 */
public class RandomPasswordGenerator {
	
	private RandomPasswordGenerator() {
		throw new UnsupportedOperationException("Password Generation");
	}
	public static String getAlphaNumericString(int n) 
	{ 
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 
		SecureRandom random = new SecureRandom();
		
		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 
			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int randomIndex = random.nextInt(alphaNumericString.length());
	           char randomChar = alphaNumericString.charAt(randomIndex);
	           sb.append(randomChar);
		} 
		return sb.toString(); 
	} 
}


