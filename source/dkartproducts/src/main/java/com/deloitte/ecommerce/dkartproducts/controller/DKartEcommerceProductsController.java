package com.deloitte.ecommerce.dkartproducts.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsDaoException;
import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsServiceException;
import com.deloitte.ecommerce.dkartproducts.json.output.ProductDetails;
import com.deloitte.ecommerce.dkartproducts.json.output.Products;
import com.deloitte.ecommerce.dkartproducts.service.DKartEcommerceProductsService;

@RestController
@RequestMapping("/dkart/api")
public class DKartEcommerceProductsController {
	final static Logger LOG = Logger.getLogger(DKartEcommerceProductsController.class);

	@Autowired
	DKartEcommerceProductsService dKartEcommerceProductsService;

	@RequestMapping(value="/products", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Products getProducts(@RequestParam(value="searchTerm", required=true) String searchTerm) throws DKartEcommerceProductsDaoException,DKartEcommerceProductsServiceException,Exception {
		LOG.debug("Get Products for " + searchTerm);
		Products products = dKartEcommerceProductsService.getProducts(searchTerm);
		return products;
	}
	
	@RequestMapping(value="/products/{productId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProductDetails getProductDetails(@PathVariable Integer productId,@RequestHeader(value="Cart-ID", required = false) Integer cartId) throws DKartEcommerceProductsDaoException,DKartEcommerceProductsServiceException,Exception {
		LOG.debug("Get Product Details for " + productId);
		ProductDetails productDetails = dKartEcommerceProductsService.getProductDetails(productId,cartId);
		return productDetails;
	}
	
}