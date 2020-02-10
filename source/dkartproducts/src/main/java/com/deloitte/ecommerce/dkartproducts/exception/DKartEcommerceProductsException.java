package com.deloitte.ecommerce.dkartproducts.exception;



public abstract class DKartEcommerceProductsException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String errorCode;

	protected String errorMessage;

	protected String httpErrorCode;

	public abstract String getErrorCode();

	public abstract String getErrorMessage();

	public abstract String getHttpErrorCode();

	
	public DKartEcommerceProductsException() {

	}

	public DKartEcommerceProductsException(String errorCode, String errorMessage, String httpErrorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.httpErrorCode = httpErrorCode;

	}

	/**
	 * @param message
	 */
	public DKartEcommerceProductsException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public DKartEcommerceProductsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DKartEcommerceProductsException(String message, Throwable cause) {
		super(message, cause);

	}

	
}
