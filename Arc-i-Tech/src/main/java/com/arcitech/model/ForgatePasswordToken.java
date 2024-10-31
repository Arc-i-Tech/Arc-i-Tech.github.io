/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author AJ
 * 
 */
@Entity
public class ForgatePasswordToken extends CommonFields {


	@Column(nullable = false)
	private String token; 

	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private LocalDateTime expireTimel;

	@Column(nullable = false)
	private boolean isUsed;

	public ForgatePasswordToken(String token, User user, LocalDateTime expireTimel, boolean isUsed) {
		this.token = token;
		this.user = user;
		this.expireTimel = expireTimel;
		this.isUsed = isUsed;
	}

	public ForgatePasswordToken() {}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the expireTimel
	 */
	public LocalDateTime getExpireTimel() {
		return expireTimel;
	}

	/**
	 * @param expireTimel the expireTimel to set
	 */
	public void setExpireTimel(LocalDateTime expireTimel) {
		this.expireTimel = expireTimel;
	}

	/**
	 * @return the isUsed
	 */
	public boolean isUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public String toString() {
		return "ForgatePasswordToken [token=" + token + ", user=" + user + ", expireTimel=" + expireTimel + ", isUsed="
				+ isUsed + "]";
	}


}
