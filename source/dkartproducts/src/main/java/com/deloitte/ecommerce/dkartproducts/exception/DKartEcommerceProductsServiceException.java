package com.deloitte.ecommerce.dkartproducts.exception;

public class DKartEcommerceProductsServiceException extends DKartEcommerceProductsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DKartEcommerceProductsServiceException() {
	}

	public DKartEcommerceProductsServiceException(String errorCode, String errorMessage, String httpErrorCode) {
		super(errorCode, errorMessage, httpErrorCode);

	}

	
	@Override
	public String getErrorCode() {

		return errorCode;
	}

	@Override
	public String getErrorMessage() {

		return errorMessage;
	}
	
	@Override
	public String getHttpErrorCode() {

		return httpErrorCode;
	}

}
