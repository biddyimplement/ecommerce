package com.deloitte.ecommerce.dkartcarts.service;

import com.deloitte.ecommerce.dkartcarts.entity.DkartCartsServiceGenericResponse;
import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceDaoException;
import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceException;
import com.deloitte.ecommerce.dkartcarts.json.input.DkartCartRemoveServiceRequest;
import com.deloitte.ecommerce.dkartcarts.json.input.DkartcartsServiceRequest;
import com.deloitte.ecommerce.dkartcarts.json.output.CartItems;
import com.deloitte.ecommerce.dkartcarts.json.output.DkartCartsServiceResponse;

public interface DkartCartsService {

	DkartCartsServiceResponse addCartItem(DkartcartsServiceRequest requestPayload) throws DkartCartsServiceException,DkartCartsServiceDaoException;

	CartItems getCartItems(String cartId)throws DkartCartsServiceException,DkartCartsServiceDaoException;

	DkartCartsServiceGenericResponse removeCartItem(DkartCartRemoveServiceRequest requestPayload)throws DkartCartsServiceException,DkartCartsServiceDaoException;

}
