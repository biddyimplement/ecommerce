package com.deloitte.ecommerce.dkartcarts.exception;

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

import com.deloitte.ecommerce.dkartcarts.entity.DkartCartsServiceGenericResponse;
import com.deloitte.ecommerce.dkartcarts.utility.DkartCartsServiceErrors;

@ControllerAdvice
public class ExceptionHandlerController {

	@Autowired
	private DkartCartsServiceErrors templateServiceErrors;
	
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
	final DkartCartsServiceGenericResponse handleUnhandledException(HttpServletRequest req,
			Exception ex) {
		LOG.error("Unhandled exception " + ex.getMessage() + ex.getClass());
		return constructErrorMessage(templateServiceErrors.getErrorCode1(), templateServiceErrors.getErrorMessage1());
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DkartCartsServiceDaoException.class)
	@ResponseBody
	public DkartCartsServiceGenericResponse handleDaoException(
			DkartCartsServiceDaoException e) {
		 	this.setErrorCode(e.getErrorCode());
		 	this.setErrorMessage(e.getErrorMessage());
		 	LOG.error("Skf Employee Career Service DAO Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DkartCartsServiceException.class)
	@ResponseBody
	public DkartCartsServiceGenericResponse handleApplicationServiceException(
			DkartCartsServiceException e) {
		 	this.setErrorCode(e.getErrorCode());
		 	this.setErrorMessage(e.getErrorMessage());
		 	LOG.error("Skf Employee Career Service Application Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InterruptedException.class)
	@ResponseBody
	public DkartCartsServiceGenericResponse handleInterruptedException(
			InterruptedException e) {
		 	this.setErrorCode(templateServiceErrors.getErrorCode1());
		 	this.setErrorMessage(e.getMessage());
		 	LOG.error("Skf Employee Career Service Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ExecutionException.class)
	@ResponseBody
	public DkartCartsServiceGenericResponse handleExecutionException(
			ExecutionException e) {
		 	this.setErrorCode(templateServiceErrors.getErrorCode1());
		 	this.setErrorMessage(e.getMessage());
		 	LOG.error("Skf Employee Career Services Application Exception " + e.getMessage() + e.getClass());
			return constructErrorMessage(errorCode, errorMessage);
		
	}
	
	public DkartCartsServiceGenericResponse constructErrorMessage(String errorCode,
			String errorMessage) {
		DkartCartsServiceGenericResponse responseJson = new DkartCartsServiceGenericResponse();
		responseJson.setErrorCode(errorCode);
		responseJson.setErrorMessage(errorMessage);
		return responseJson;
	}
}
