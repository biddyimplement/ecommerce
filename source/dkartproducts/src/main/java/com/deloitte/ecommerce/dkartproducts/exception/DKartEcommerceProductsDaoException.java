package com.deloitte.ecommerce.dkartproducts.exception;


public class DKartEcommerceProductsDaoException extends DKartEcommerceProductsException {

	
	private static final long serialVersionUID = 1L;

	public DKartEcommerceProductsDaoException() {
	}

	public DKartEcommerceProductsDaoException(String errorCode, String errorMessage, String httpErrorCode) {
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
