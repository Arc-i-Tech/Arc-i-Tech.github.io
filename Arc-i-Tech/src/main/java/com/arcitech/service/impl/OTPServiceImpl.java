/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.service.impl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.arcitech.service.OTPService;
import com.arcitech.utils.OTP;

/**
 * @author Ajay G
 * 
 */
@Service
public class OTPServiceImpl implements OTPService {

	private static final SecureRandom RANDOM = new SecureRandom();

	public OTP generateOTP(int len) {
		StringBuilder otpStr = new StringBuilder();
		for (int i = 0; i < len; i++) {
			otpStr.append(RANDOM.nextInt(10));
		}
		return new OTP.Builder(otpStr.toString()).build();
	}
}
