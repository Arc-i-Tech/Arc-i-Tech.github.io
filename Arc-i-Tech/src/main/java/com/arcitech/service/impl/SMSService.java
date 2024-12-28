/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author Ajay G
 * 
 */
@Service
public class SMSService {
	public void sendSMS(String number, String message) {
		System.out.println("Sending SMS to " + number + ": " + message);
	}
}
