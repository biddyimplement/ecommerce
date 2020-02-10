package com.deloitte.ecommerce.dkartcarts.exception;



public abstract class DkartCartsException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String errorCode;

	protected String errorMessage;

	protected String httpErrorCode;

	public abstract String getErrorCode();

	public abstract String getErrorMessage();

	public abstract String getHttpErrorCode();

	
	public DkartCartsException() {

	}

	public DkartCartsException(String errorCode, String errorMessage, String httpErrorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.httpErrorCode = httpErrorCode;

	}

	/**
	 * @param message
	 */
	public DkartCartsException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public DkartCartsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DkartCartsException(String message, Throwable cause) {
		super(message, cause);

	}

	
}
