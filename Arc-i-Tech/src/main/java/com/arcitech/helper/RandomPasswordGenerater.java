/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.helper;

/**
 * @author Priya
 * 
 */
public class RandomPasswordGenerater {
	
	public static String getAlphaNumericString(int n) 
	{ 

	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "0123456789"
			+ "abcdefghijklmnopqrstuvxyz"; 

	// create StringBuffer size of AlphaNumericString 
	StringBuilder sb = new StringBuilder(n); 

	for (int i = 0; i < n; i++) { 

	// generate a random number between 
	// 0 to AlphaNumericString variable length 
	int index 
		= (int)(AlphaNumericString.length() 
		* Math.random()); 

	// add Character one by one in end of sb 
	sb.append(AlphaNumericString 
		.charAt(index)); 
	} 

	return sb.toString(); 
	} 


}
