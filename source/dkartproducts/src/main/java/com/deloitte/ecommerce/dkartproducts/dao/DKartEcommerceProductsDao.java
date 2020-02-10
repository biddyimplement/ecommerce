package com.deloitte.ecommerce.dkartproducts.dao;

import java.util.List;
import java.util.Set;

import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsDaoException;
import com.deloitte.ecommerce.dkartproducts.vo.Cartdetails;
import com.deloitte.ecommerce.dkartproducts.vo.Product;

public interface DKartEcommerceProductsDao {

	public Set<Product> fetchProducts(String searchTerm);
	public Product getProduct(Integer productId) throws DKartEcommerceProductsDaoException;
	public List<Cartdetails> getCartDetails(Integer cartId,Integer productId) throws DKartEcommerceProductsDaoException; 
	public List<Product> fetchProductsWithADeal(Double searchTerm) throws DKartEcommerceProductsDaoException;
	public List<Product> fetchProductsForDeals() throws DKartEcommerceProductsDaoException;
}
