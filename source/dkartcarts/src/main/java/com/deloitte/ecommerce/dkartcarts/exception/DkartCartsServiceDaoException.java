package com.deloitte.ecommerce.dkartcarts.exception;


public class DkartCartsServiceDaoException extends DkartCartsException {

	
	private static final long serialVersionUID = 1L;

	public DkartCartsServiceDaoException() {
	}

	public DkartCartsServiceDaoException(String errorCode, String errorMessage, String httpErrorCode) {
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
