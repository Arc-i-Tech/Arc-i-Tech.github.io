/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.utils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Ajay G
 * 
 */
public class OTP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String otpString = null;
	private final LocalDateTime createDAt;
	private int expiryTime = 5;
	private Type type = Type.RESET_PASSWORD;

	public enum Type {
		RESET_PASSWORD(100), LOGIN(200);

		private int value;

		/**
		 * @param i
		 */
		Type(int i) {
			this.value = i;
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

	}

	public static class Builder {
		private String otpString = null;
		private int expiryTime = 5;
		private Type type;

		/**
		 * @param otpString
		 */
		public Builder(String otpString) {
			this.otpString = otpString;
		}

		public Builder withOTP(String otpStr) {
			this.otpString = otpStr;
			return this;
		}

		/**
		 * @param expiryTime - Expiry time in minutes
		 * @return
		 */
		public Builder withExpiry(int expiryTime) {
			this.expiryTime = expiryTime;
			return this;
		}

		public Builder withType(Type type) {
			this.type = type;
			return this;
		}

		public OTP build() {
			return new OTP(this);
		}
	}

	/**
	 * 
	 */
	private OTP() {
		super();
		this.createDAt = LocalDateTime.now();
		// Generate Unique ID
		// Create OTP map and put OTP there and remove once expired
	}

	/**
	 * @param builder
	 */
	public OTP(Builder builder) {
		this();
		this.otpString = builder.otpString;
		this.expiryTime = builder.expiryTime;
		this.type = builder.type;
	}

	/**
	 * @param otpString
	 * @param expiryTime
	 */
	public OTP(String otpString, int expiryTime) {
		this();
		this.otpString = otpString;
		this.expiryTime = expiryTime;
	}

	/**
	 * @param otpString
	 */
	public OTP(String otpString) {
		this();
		this.otpString = otpString;
	}

	/**
	 * @return the otpString
	 */
	public String getOtpString() {
		return otpString;
	}

	/**
	 * @param otpString the otpString to set
	 */
	public void setOtpString(String otpString) {
		this.otpString = otpString;
	}

	/**
	 * @return the expiryTime
	 */
	public int getExpiryTime() {
		return expiryTime;
	}

	/**
	 * @param expiryTime the expiryTime to set
	 */
	public void setExpiryTime(int expiryTime) {
		this.expiryTime = expiryTime;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the createDAt
	 */
	public LocalDateTime getCreateDAt() {
		return createDAt;
	}

}
