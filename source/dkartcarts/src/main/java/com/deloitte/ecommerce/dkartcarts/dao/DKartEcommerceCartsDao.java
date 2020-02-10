package com.deloitte.ecommerce.dkartcarts.dao;

import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceDaoException;
import com.deloitte.ecommerce.dkartcarts.vo.Cart;
import com.deloitte.ecommerce.dkartcarts.vo.Cartdetails;
import com.deloitte.ecommerce.dkartcarts.vo.Product;

public interface DKartEcommerceCartsDao {

	public  Integer saveOrUpdateCart(Cart cart)throws DkartCartsServiceDaoException;
	boolean saveOrUpdateCartDetails(Cartdetails cartDetails) throws DkartCartsServiceDaoException;
	public Product getProduct(Integer productId) throws DkartCartsServiceDaoException;
	public Cart getCart(Integer cartId) throws DkartCartsServiceDaoException;
	public Boolean removeCartItem(Cartdetails cartdetails) throws DkartCartsServiceDaoException;
	public Cartdetails getCartItem(Cart cartId, Integer itemId) throws DkartCartsServiceDaoException;
}
