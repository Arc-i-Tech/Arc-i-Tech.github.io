/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

/**
 * @author Ajay G
 * 
 */
@MappedSuperclass
public abstract class PersonalDetails extends CommonFields {

	private String name;
	private LocalDate dob;
	private String address;
	private int moNo;
	private int city;
	private int pincode;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the moNo
	 */
	public int getMoNo() {
		return moNo;
	}

	/**
	 * @param moNo the moNo to set
	 */
	public void setMoNo(int moNo) {
		this.moNo = moNo;
	}

	/**
	 * @return the city
	 */
	public int getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/**
	 * @return the pincode
	 */
	public int getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
