/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.dto;

import com.arcitech.model.interfaces.IAuth;

/**
 * @author Ajay G
 * 
 */
public class ResetPasswordRequest {
	private String username;
	private String newPassword;
	private String otpStr;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param password the password to set
	 */
	public void setNewPassword(String password) {
		this.newPassword = password;
	}

	/**
	 * @return the otpStr
	 */
	public String getOtpStr() {
		return otpStr;
	}

	/**
	 * @param otpStr the otpStr to set
	 */
	public void setOtpStr(String otpStr) {
		this.otpStr = otpStr;
	}

	/**
	 * @return
	 */
	public IAuth toAuth(IAuth auth) {
		auth.setUsername(this.getUsername());
		auth.setPassword(this.getNewPassword());
		return auth;
	}

}
