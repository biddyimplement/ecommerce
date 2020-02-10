package com.deloitte.ecommerce.dkartproducts.exception;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deloitte.ecommerce.dkartproducts.entity.DKartEcommerceProductsGenericResponse;
import com.deloitte.ecommerce.dkartproducts.utility.DKartEcommerceProductsServiceErrors;

@ControllerAdvice
public class ExceptionHandlerController {

	
	@Autowired
	private DKartEcommerceProductsServiceErrors templateServiceErrors;
	
	private String errorCode;

	private String errorMessage;

	private static final Logger LOG = LoggerFactory
			.getLogger(ExceptionHandlerController.class);
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	/**
	 * Handler for for any un-handled exception.
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	final DKartEcommerceProductsGenericResponse handleUnhandledException(HttpServletRequest req,
			Exception ex) {
		LOG.error("Unhandled exception " + ex.getMessage() + ex.getClass());
		return constructErrorMessage(templateServiceErrors.getErrorCode1(), templateServiceErrors.getErrorMessage1());
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DKartEcommerceProductsDaoException.class)
	@ResponseBody
	public DKartEcommerceProductsGenericResponse handleDaoException(
			DKartEcommerceProductsDaoException e) {
		 	this.setErrorCode(e.getErrorCode());
		 	this.setErrorMessage(e.getErrorMessage());
		 	LOG.error("Skf Employee Career Service DAO Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DKartEcommerceProductsServiceException.class)
	@ResponseBody
	public DKartEcommerceProductsGenericResponse handleApplicationServiceException(
			DKartEcommerceProductsServiceException e) {
		 	this.setErrorCode(e.getErrorCode());
		 	this.setErrorMessage(e.getErrorMessage());
		 	LOG.error("Skf Employee Career Service Application Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InterruptedException.class)
	@ResponseBody
	public DKartEcommerceProductsGenericResponse handleInterruptedException(
			InterruptedException e) {
		 	this.setErrorCode(templateServiceErrors.getErrorCode1());
		 	this.setErrorMessage(e.getMessage());
		 	LOG.error("Skf Employee Career Service Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ExecutionException.class)
	@ResponseBody
	public DKartEcommerceProductsGenericResponse handleExecutionException(
			ExecutionException e) {
		 	this.setErrorCode(templateServiceErrors.getErrorCode1());
		 	this.setErrorMessage(e.getMessage());
		 	LOG.error("Skf Employee Career Services Application Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	public DKartEcommerceProductsGenericResponse constructErrorMessage(String errorCode,
			String errorMessage) {
		DKartEcommerceProductsGenericResponse responseJson = new DKartEcommerceProductsGenericResponse();
		responseJson.setErrorCode(errorCode);
		responseJson.setErrorMessage(errorMessage);
		return responseJson;
	}
}
