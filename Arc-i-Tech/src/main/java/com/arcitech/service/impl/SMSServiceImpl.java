/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import org.springframework.stereotype.Service;

import com.arcitech.service.SMSService;

/**
 * @author Ajay G
 * 
 */
@Service
public class SMSServiceImpl implements SMSService {
	public void sendSMS(String number, String message) {
		// TODO Purchase SMS service and integrate here.
		System.out.println("Sending SMS to " + number + ": " + message);
	}
}
