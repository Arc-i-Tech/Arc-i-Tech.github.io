package com.arcitech.exceptions;

import javax.naming.OperationNotSupportedException;

public class InvalidTokenException extends RuntimeException {
	
	private InvalidTokenException() throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}
	
    public InvalidTokenException(String message) {
        super(message);
    }
}

