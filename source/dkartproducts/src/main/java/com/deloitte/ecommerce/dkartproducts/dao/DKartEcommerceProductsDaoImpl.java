package com.deloitte.ecommerce.dkartproducts.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsDaoException;
import com.deloitte.ecommerce.dkartproducts.vo.Cart;
import com.deloitte.ecommerce.dkartproducts.vo.Cartdetails;
import com.deloitte.ecommerce.dkartproducts.vo.Product;

@Service
@Transactional
public class DKartEcommerceProductsDaoImpl implements DKartEcommerceProductsDao {

	private final Logger logger = LoggerFactory.getLogger(DKartEcommerceProductsDaoImpl.class);

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.openSession();
	}

	@Override
	public Set<Product> fetchProducts(String searchTerm) {
		Set<Product> products = null;
		Session session = getSession();
		try {
			String[] searchTerms = searchTerm.split(" ");
			Criteria crit = session.createCriteria(Product.class, "product");
			crit.createAlias("product.categories", "categories");
			Disjunction disjunction = this.getCriterions(searchTerms);
			crit.add(disjunction);
			List<Product> productList = crit.list();

			if (productList != null && !productList.isEmpty()) {
				products = new HashSet<>(productList);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}finally{
			session.close();
		}
		return products;
	}

	private Disjunction getCriterions(String[] searchTerms) {
		
		Disjunction disjunction = Restrictions.disjunction();
		for (String searchTerm : searchTerms) {
			searchTerm = searchTerm.trim();
			Criterion productNameCrit = Restrictions.ilike("product.productname", searchTerm, MatchMode.ANYWHERE);
			Criterion productNameExtendedCrit = Restrictions.ilike("product.productnameExtended", searchTerm,
					MatchMode.ANYWHERE);
			Criterion descriptionCrit = Restrictions.ilike("product.description", searchTerm, MatchMode.ANYWHERE);
			Criterion descriptionExtendedCrit = Restrictions.ilike("product.descriptionExtended", searchTerm,
					MatchMode.ANYWHERE);
			Criterion categoryCrit = Restrictions.ilike("categories.categoryname", searchTerm, MatchMode.ANYWHERE);
			disjunction.add(productNameCrit);
			disjunction.add(productNameExtendedCrit);
			disjunction.add(descriptionCrit);
			disjunction.add(descriptionExtendedCrit);
			disjunction.add(categoryCrit);
		}
		return disjunction;
	}

	@Override
	public Product getProduct(Integer productId) throws DKartEcommerceProductsDaoException {
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
	public List<Cartdetails> getCartDetails(Integer cartId,Integer productId) throws DKartEcommerceProductsDaoException {
		Session session = getSession();
		try {
			Criteria crit = session.createCriteria(Cartdetails.class,"cartdetails");
			crit.createAlias("cartdetails.cart","cart");
			crit.createAlias("cartdetails.product","product");
			crit.add(Restrictions.eq("cart.cartid", cartId));
			crit.add(Restrictions.eq("product.productid", productId));
			List<Cartdetails> cartDetails = crit.list();
			return cartDetails;
		} catch (Exception e) {
			logger.error("error while fetching carts" + e);
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> fetchProductsWithADeal(Double searchTerm) throws DKartEcommerceProductsDaoException {
		Session session = getSession();
		List<Product> products = null;
		try {
			Criteria crit = session.createCriteria(Product.class);
			crit.add(Restrictions.eq("discountperc", searchTerm));
			crit.add(Restrictions.eq("isDiscounted", "1"));
			products = crit.list();
		}catch(Exception e){
			logger.error("Error while fetching products" + e);
		} finally{
			session.close();
		}
		return products;
	}

	@Override
	public List<Product> fetchProductsForDeals() throws DKartEcommerceProductsDaoException {
		Session session = getSession();
		List<Product> products = null;
		try {
			Criteria crit = session.createCriteria(Product.class);
			crit.add(Restrictions.eq("isDiscounted", "1"));
			products = crit.list();
		}catch(Exception e){
			logger.error("Error while fetching products" + e);
		}finally{
			session.close();
		}
		return products;
	}
}
