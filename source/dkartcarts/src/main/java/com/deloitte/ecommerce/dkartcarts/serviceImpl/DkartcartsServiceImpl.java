package com.deloitte.ecommerce.dkartcarts.serviceImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.ecommerce.dkartcarts.dao.DKartEcommerceCartsDao;
import com.deloitte.ecommerce.dkartcarts.entity.DkartCartsServiceGenericResponse;
import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceDaoException;
import com.deloitte.ecommerce.dkartcarts.exception.DkartCartsServiceException;
import com.deloitte.ecommerce.dkartcarts.json.input.DkartCartRemoveServiceRequest;
import com.deloitte.ecommerce.dkartcarts.json.input.DkartcartsServiceRequest;
import com.deloitte.ecommerce.dkartcarts.json.output.CartItem;
import com.deloitte.ecommerce.dkartcarts.json.output.CartItems;
import com.deloitte.ecommerce.dkartcarts.json.output.Data;
import com.deloitte.ecommerce.dkartcarts.json.output.DkartCartsServiceResponse;
import com.deloitte.ecommerce.dkartcarts.json.output.Specification;
import com.deloitte.ecommerce.dkartcarts.service.DkartCartsService;
import com.deloitte.ecommerce.dkartcarts.utility.DkartCartsServiceConstants;
import com.deloitte.ecommerce.dkartcarts.utility.DkartCartsServiceErrors;
import com.deloitte.ecommerce.dkartcarts.vo.Cart;
import com.deloitte.ecommerce.dkartcarts.vo.Cartdetails;
import com.deloitte.ecommerce.dkartcarts.vo.Product;

@Service
public class DkartcartsServiceImpl implements DkartCartsService	{

	private final Logger LOG = LoggerFactory.getLogger(DkartcartsServiceImpl.class);
	
	@Autowired
	private DkartCartsServiceErrors dkartCartsServiceErrors;

	@Autowired
	public DKartEcommerceCartsDao dKartEcommerceCartsDao;
	
	@Override
	public DkartCartsServiceResponse addCartItem(DkartcartsServiceRequest requestPayload)throws DkartCartsServiceException, DkartCartsServiceDaoException {
		DkartCartsServiceResponse response = new DkartCartsServiceResponse();
		DecimalFormat df2 = new DecimalFormat(".##");
		if(requestPayload == null || requestPayload.getProductId() ==null){
			throw new DkartCartsServiceException("API_SYS_001","Product ID can't be null","500");
		}
		try {
			Product product = dKartEcommerceCartsDao.getProduct(requestPayload.getProductId());
			if(product==null){
				throw new DkartCartsServiceException("API_SYS_002","Product ID is not available","500");
			}
			Cart cart = null;
			if(requestPayload != null && requestPayload.getCartId() !=null){
				cart = dKartEcommerceCartsDao.getCart(requestPayload.getCartId());
				cart.setModificationdate(new Date());
				dKartEcommerceCartsDao.saveOrUpdateCart(cart);
			} else {
				cart = new Cart();
				cart.setCreationdate(new Date());
				dKartEcommerceCartsDao.saveOrUpdateCart(cart);
			}
			Cart cartWithDetails = dKartEcommerceCartsDao.getCart(cart.getCartid());
			Cartdetails cartDetailsToSave = new Cartdetails();
			Set<Cartdetails> cartDetails = cartWithDetails.getCartdetailses();
			Set<Cartdetails> cartDetailsForProduct = new HashSet<>();
			for(Cartdetails cartDetail : cartDetails){
				if(cartDetail.getProduct().getProductid().equals(product.getProductid())){
					cartDetailsForProduct.add(cartDetail);
				}
			}
			boolean updateCartDetails = false;
			for(Cartdetails cartDetailForProduct : cartDetailsForProduct){
				if(requestPayload.getSize() == null){
					if(cartDetailForProduct.getSize() == requestPayload.getSize()){
						cartDetailForProduct.setQuantity(cartDetailForProduct.getQuantity()+requestPayload.getQuantity());
						cartDetailsToSave = cartDetailForProduct;
						updateCartDetails = true;
						break;
					}
				}else if(requestPayload.getSize().equalsIgnoreCase(cartDetailForProduct.getSize())){
					cartDetailForProduct.setQuantity(cartDetailForProduct.getQuantity()+requestPayload.getQuantity());
					cartDetailsToSave = cartDetailForProduct;
					updateCartDetails = true;
					break;
				}
			}
			
			if(updateCartDetails){
				if(dKartEcommerceCartsDao.saveOrUpdateCartDetails(cartDetailsToSave)){
					response.setMessage(DkartCartsServiceConstants.SUCCESS_MESSAGE_CART_DETAILS);
					response.setSuccess(true);
					Data data = new Data();
					data.setCartId(cart.getCartid());
					response.setData(data);
				} else {
					throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
				}
			}else{
				Double finalPrice;
				if("1".equalsIgnoreCase(product.getIsDiscounted())){
					Double discountPerc = product.getDiscountperc();
					finalPrice = product.getUnitprice() - ((discountPerc/100)*product.getUnitprice());
				} else {
					finalPrice = product.getUnitprice();
				}
				cartDetailsToSave.setCart(cart);
				cartDetailsToSave.setDiscountprice(Double.valueOf(df2.format(finalPrice)));
				cartDetailsToSave.setOriginalprice(Double.valueOf(df2.format(product.getUnitprice())));
				cartDetailsToSave.setProduct(product);
				cartDetailsToSave.setQuantity(requestPayload.getQuantity());
				cartDetailsToSave.setSize(requestPayload.getSize());
				if(dKartEcommerceCartsDao.saveOrUpdateCartDetails(cartDetailsToSave)){
					response.setMessage(DkartCartsServiceConstants.SUCCESS_MESSAGE_CART_DETAILS);
					response.setSuccess(true);
					Data data = new Data();
					data.setCartId(cart.getCartid());
					response.setData(data);
				} else {
					throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
				}
			}
		} catch(DkartCartsServiceDaoException e) {
			LOG.error("addCartItem" + e);
			throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
		}
		return response;
	}

	@Override
	public CartItems getCartItems(String cartId) throws DkartCartsServiceException,DkartCartsServiceDaoException{
		CartItems cartItems = new CartItems();
		cartItems.setCartItems(new ArrayList<CartItem>());
		try{
			Boolean isCartDiscounted = false;
			Double totalPrice = 0d;
			Double totalCartOriginalPrice = 0d;
			Cart cartWithDetails = dKartEcommerceCartsDao.getCart(Integer.valueOf(cartId));
			Set<Cartdetails> cartDetails = cartWithDetails.getCartdetailses();
			for(Cartdetails cartDetail : cartDetails){
				CartItem cartItem = new CartItem();
				cartItem.setProductId(cartDetail.getProduct().getProductid().toString());
				cartItem.setTitle(cartDetail.getProduct().getProductnameExtended());
				cartItem.setProductImage(cartDetail.getProduct().getProductImages().getImagename());
				cartItem.setItemID(cartDetail.getCartdetailsid());
				Specification specification = new Specification();
				specification.setNoOfItems(cartDetail.getQuantity());
				specification.setItemPrice(cartDetail.getDiscountprice());
				Double totalpricepercart = (cartDetail.getDiscountprice()*cartDetail.getQuantity());
				DecimalFormat df = new DecimalFormat("#.##");    
				if("1".equalsIgnoreCase(cartDetail.getProduct().getIsDiscounted())){
					isCartDiscounted = true;
					Double itemUnitPrice = Double.valueOf(df.format(cartDetail.getProduct().getUnitprice()));
					specification.setIsDiscounted(true);
					specification.setItemOriginalPrice(df.format(itemUnitPrice));
					specification.setItemOriginalFormattedPrice(String.format("%,.2f",itemUnitPrice));
					Double totalOriginalPrice = itemUnitPrice * cartDetail.getQuantity();
					totalCartOriginalPrice = totalCartOriginalPrice + totalOriginalPrice;
					specification.setTotalOriginalPrice(df.format(totalOriginalPrice));
					specification.setTotalOriginalFormattedPrice(String.format("%,.2f",totalOriginalPrice));
				}else{
					specification.setIsDiscounted(false);
					totalCartOriginalPrice = totalCartOriginalPrice + totalpricepercart;
				}
				specification.setItemPrice(Double.valueOf(df.format(specification.getItemPrice())));
				specification.setItemFormattedPrice(String.format("%,.2f",cartDetail.getDiscountprice()));
				totalpricepercart = Double.valueOf(df.format(totalpricepercart));
				specification.setTotalPrice(totalpricepercart);
				specification.setTotalFormattedPrice(String.format("%,.2f",totalpricepercart));
				cartItem.setSpecification(specification);
				totalPrice = totalPrice + totalpricepercart;
				cartItems.getCartItems().add(cartItem);
			}
			cartItems.setCartID(cartWithDetails.getCartid());
			cartItems.setTotalPrice(String.format("%,.2f",totalPrice));
			if(isCartDiscounted){
				Double savings = totalCartOriginalPrice - totalPrice;
				cartItems.setTotalOriginalPrice(String.format("%,.2f",totalCartOriginalPrice));
				cartItems.setIsDiscounted(true);
				cartItems.setSavings(String.format("%,.2f",savings));
			}else{
				cartItems.setIsDiscounted(false);
			}
		}catch(Exception e){
			throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
		}
		return cartItems;
	}
	
	@Override
	public DkartCartsServiceGenericResponse removeCartItem(DkartCartRemoveServiceRequest requestPayload)
			throws DkartCartsServiceException, DkartCartsServiceDaoException {
		DkartCartsServiceGenericResponse response = new DkartCartsServiceGenericResponse();
		try {
			Cart cartWithDetails = dKartEcommerceCartsDao.getCart(requestPayload.getCartID());
			boolean cartItemremoved = false;
			if(cartWithDetails != null ){
				Cartdetails cartdetailsObj = dKartEcommerceCartsDao.getCartItem(cartWithDetails,requestPayload.getItemID());
				if(cartdetailsObj != null){
					cartItemremoved = dKartEcommerceCartsDao.removeCartItem(cartdetailsObj);
					if (cartItemremoved) {
						response.setStatus(DkartCartsServiceConstants.KEY_SUCCESS);	
					} else {
						response.setStatus(DkartCartsServiceConstants.KEY_FAILURE);
					}
				} else {
					throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
				}
				
			} else {
				throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
			}
			
		} catch (Exception e) {
			throw new DkartCartsServiceDaoException(dkartCartsServiceErrors.getErrorCode1(),dkartCartsServiceErrors.getErrorMessage1(),"500");
		}
		return response;
	}

}
