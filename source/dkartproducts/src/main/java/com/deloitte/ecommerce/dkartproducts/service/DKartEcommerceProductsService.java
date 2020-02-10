package com.deloitte.ecommerce.dkartproducts.service;

import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsServiceException;
import com.deloitte.ecommerce.dkartproducts.json.output.ProductDetails;
import com.deloitte.ecommerce.dkartproducts.json.output.Products;

public interface DKartEcommerceProductsService {

	Products getProducts(String searchTerm) throws DKartEcommerceProductsServiceException;
	ProductDetails getProductDetails(Integer productId,Integer cartId) throws DKartEcommerceProductsServiceException;
}
