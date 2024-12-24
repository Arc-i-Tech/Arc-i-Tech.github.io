/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.arcitech.interfaces.Copyable;

/**
 * @author Ajay G
 * 
 */
@MappedSuperclass
public abstract class CommonFields implements Copyable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public Object copy(Object obj) {
		if (obj instanceof CommonFields) {
			CommonFields commonFields = (CommonFields) obj;
			commonFields.setId(this.getId());
			return commonFields;
		}
		throw new UnsupportedOperationException("Object type mismatch.");
	}
}
