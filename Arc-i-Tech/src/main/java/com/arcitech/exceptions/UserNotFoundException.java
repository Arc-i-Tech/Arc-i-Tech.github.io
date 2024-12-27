package com.arcitech.exceptions;

import javax.naming.OperationNotSupportedException;

public class UserNotFoundException extends RuntimeException {

	
	private UserNotFoundException() throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}
	
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
