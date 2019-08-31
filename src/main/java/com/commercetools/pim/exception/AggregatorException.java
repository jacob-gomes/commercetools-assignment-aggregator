package com.commercetools.pim.exception;
/**
 * Exception class
 * @author Jacob
 *
 */
public class AggregatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 216329091818034054L;
	
	private final String message;
	
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public AggregatorException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
