package com.deloitte.ecommerce.dkartcarts.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.ecommerce.dkartcarts.entity.DkartCartsServiceGenericResponse;
import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceDaoException;
import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceException;
import com.deloitte.ecommerce.dkartcarts.json.input.DkartCartRemoveServiceRequest;
import com.deloitte.ecommerce.dkartcarts.json.input.DkartcartsServiceRequest;
import com.deloitte.ecommerce.dkartcarts.json.output.CartItems;
import com.deloitte.ecommerce.dkartcarts.json.output.DkartCartsServiceResponse;
import com.deloitte.ecommerce.dkartcarts.service.DkartCartsService;
import com.deloitte.ecommerce.dkartcarts.wadl.MethodDescription;

@RestController
@RequestMapping("/dkart/api")
public class DkartCartsController {
	final static Logger LOG = Logger.getLogger(DkartCartsController.class);

	@Autowired	
	DkartCartsService dkartCartsService;
	
	@RequestMapping(value="/carts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DkartCartsServiceResponse getRolesBasedOnGradesList(@RequestHeader HttpHeaders reqHeaders,@RequestBody DkartcartsServiceRequest requestPayload) throws DkartCartsServiceDaoException,DkartCartsServiceException,Exception {
		LOG.info("Dkart Carts Service addCartItem Request Payload :" + requestPayload);
		DkartCartsServiceResponse response = dkartCartsService.addCartItem(requestPayload);
		return response;
	}
	
	@RequestMapping(value = "/carts/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@MethodDescription(value = "This API is to get cart Details from cart details table based on adaccountname", params = {"searchTerm - to get cart details"})  
	@ResponseBody
	public CartItems getCartDetailsById(@PathVariable String cartId) throws DkartCartsServiceDaoException,DkartCartsServiceException,Exception{
		LOG.info("The cart id is :" + cartId);
		CartItems response = null;
		if(cartId!= null && !cartId.isEmpty()) {
			response = dkartCartsService.getCartItems(cartId);
		} else {
			response = new CartItems();
		}
		return response;
	}
	
	@RequestMapping(value="/carts", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DkartCartsServiceGenericResponse removeCartItem(@RequestHeader HttpHeaders reqHeaders,@RequestBody DkartCartRemoveServiceRequest requestPayload) throws DkartCartsServiceDaoException,DkartCartsServiceException,Exception {
		LOG.info("Dkart Carts Service addCartItem Request Payload :" + requestPayload);
		DkartCartsServiceGenericResponse response = dkartCartsService.removeCartItem(requestPayload);
		return response;
	}
	
}
