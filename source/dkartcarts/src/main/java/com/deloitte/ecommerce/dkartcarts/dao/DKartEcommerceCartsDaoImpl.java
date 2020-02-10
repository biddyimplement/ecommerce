package com.deloitte.ecommerce.dkartcarts.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceDaoException;
import com.deloitte.ecommerce.dkartcarts.utility.DkartCartsServiceQueries;
import com.deloitte.ecommerce.dkartcarts.vo.Cart;
import com.deloitte.ecommerce.dkartcarts.vo.Cartdetails;
import com.deloitte.ecommerce.dkartcarts.vo.Product;

@Service
@Transactional
public class DKartEcommerceCartsDaoImpl implements DKartEcommerceCartsDao {

	private final Logger logger = LoggerFactory.getLogger(DKartEcommerceCartsDaoImpl.class);

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.openSession();
	}

	@Override
	public Integer saveOrUpdateCart(Cart cart) throws DkartCartsServiceDaoException {
		Session session = getSession();
		logger.info("persisting the Cart object");
		try {
			session.saveOrUpdate(cart);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally{
			session.close();
		}
		return cart.getCartid();
	}

	@Override
	public boolean saveOrUpdateCartDetails(Cartdetails cartDetails) throws DkartCartsServiceDaoException {
		Session session = getSession();
		logger.info("persisting the Cart object");
		try {
			session.saveOrUpdate(cartDetails);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return false;
		} finally{
			session.close();
		}
		return true;
	}

	@Override
	public Product getProduct(Integer productId) throws DkartCartsServiceDaoException {
		Session session = getSession();
		Product product = null;
		try {
			Criteria crit = session.createCriteria(Product.class);
			crit.add(Restrictions.eq("productid", productId));
			List<Product> products = crit.list();
			if (products != null && !products.isEmpty()) {
				product = products.get(0);
			}
		} catch (Exception e) {
			logger.error("error while fetching productsS" + e);
		} finally{
			session.close();
		}
		return product;
	}
	
	@Override
	@Transactional
	public Cart getCart(Integer cartId) throws DkartCartsServiceDaoException{
		Session session = getSession();
		Cart cart = null;
		try {
			Criteria crit = session.createCriteria(Cart.class);
			crit.add(Restrictions.eq("cartid", cartId));
			List<Cart> carts = crit.list();
			if (carts != null && !carts.isEmpty()) {
				cart = carts.get(0);
			}
		} catch (Exception e) {
			logger.error("error while fetching carts" + e);
		} finally{
			session.close();
		}
		return cart;
	}
	
	@Override
	public Cartdetails getCartItem(Cart cartId, Integer itemId) throws DkartCartsServiceDaoException {
		Session session = getSession();
		Cartdetails cartItem = null;
		try {
			Criteria crit = session.createCriteria(Cartdetails.class);
			crit.add(Restrictions.eq("cartdetailsid", itemId));
			crit.add(Restrictions.eq("cart", cartId));
			List<Cartdetails> carts = crit.list();
			if (carts != null && !carts.isEmpty()) {
				cartItem = carts.get(0);
			}
		} catch (Exception e) {
			logger.error("error while fetching carts" + e);
		} finally{
			session.close();
		}
		return cartItem;
	}

	@Override
	public Boolean removeCartItem(Cartdetails cartdetails) throws DkartCartsServiceDaoException {
		Session session = getSession();
		logger.info("Removing the Cart Item object");
		try {
			Query query = getSession().createSQLQuery(DkartCartsServiceQueries.deleteItemFromCart);
			query.setParameter("cartdetailsid", cartdetails.getCartdetailsid());
			int deleted = query.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return false;
		} finally{
			session.close();
		}
		return true;
	}
}
