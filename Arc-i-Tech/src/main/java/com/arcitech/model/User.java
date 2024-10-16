/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

/**
 * @author Ajay G
 * 
 */
@Entity
public class User extends PersonalDetails {

	private String username;
	private LocalDate createdAt;

	private boolean enabled;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDate.now();
	}

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
	 * @return the createdAt
	 */
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
