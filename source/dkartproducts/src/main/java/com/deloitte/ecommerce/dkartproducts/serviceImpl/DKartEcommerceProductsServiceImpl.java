package com.deloitte.ecommerce.dkartproducts.serviceImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.ecommerce.dkartproducts.dao.DKartEcommerceProductsDao;
import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsDaoException;
import com.deloitte.ecommerce.dkartproducts.exception.DKartEcommerceProductsServiceException;
import com.deloitte.ecommerce.dkartproducts.json.output.ProductDetails;
import com.deloitte.ecommerce.dkartproducts.json.output.ProductInfo;
import com.deloitte.ecommerce.dkartproducts.json.output.Products;
import com.deloitte.ecommerce.dkartproducts.service.DKartEcommerceProductsService;
import com.deloitte.ecommerce.dkartproducts.utility.DKartEcommerceProductsServiceErrors;
import com.deloitte.ecommerce.dkartproducts.vo.Cartdetails;
import com.deloitte.ecommerce.dkartproducts.vo.Product;


@Service
public class DKartEcommerceProductsServiceImpl implements DKartEcommerceProductsService {
	final static Logger LOG = Logger.getLogger(DKartEcommerceProductsServiceImpl.class);

	@Autowired
	private DKartEcommerceProductsDao dKartEcommerceProductsDao;

	@Autowired
	private DKartEcommerceProductsServiceErrors dKartEcommerceProductsServiceErrors;

	@Override
	public Products getProducts(String searchTerm) throws DKartEcommerceProductsServiceException {

		Products products = new Products();
		try {
			
			if(searchTerm.toLowerCase().contains("-discount")){
				String[] searchTerms = searchTerm.split("-");
				if(!StringUtils.isNumeric(searchTerms[0])){
					throw new DKartEcommerceProductsServiceException("API_SYS_01","Discount is not a number", "500");
				}
				Double discount = Double.valueOf(searchTerms[0]);
				List<Product> productData = dKartEcommerceProductsDao.fetchProductsWithADeal(discount);
				if(productData!=null && !productData.isEmpty()){
					Set<Product> productSet = new HashSet<>(productData);
					products = this.alignProducts(productSet);
				}
			}else if(searchTerm.toLowerCase().contains("flashsale")){
				List<Product> productData = dKartEcommerceProductsDao.fetchProductsForDeals();
				Set<Product> productSet = new HashSet<>(productData);
				products = this.alignProducts(productSet);
			}else{
				Set<Product> productData = dKartEcommerceProductsDao.fetchProducts(searchTerm);
				if(productData!=null){
					products = this.alignProducts(productData);
				}
			}
		} catch (Exception e) {
			if(e instanceof DKartEcommerceProductsServiceException){
				throw (DKartEcommerceProductsServiceException)e;
			}else{
				throw new DKartEcommerceProductsServiceException(dKartEcommerceProductsServiceErrors.getErrorCode1(),
						dKartEcommerceProductsServiceErrors.getErrorMessage1(), "500");
			}
		}
		return products;
	}
	
	private Products alignProducts(Set<Product> productData){
		Products products = new Products();
		products.setData(new ArrayList<ProductInfo>());
		DecimalFormat df = new DecimalFormat("#.##");
		for (Product product : productData) {
			ProductInfo info = new ProductInfo();
			info.setTitle(product.getProductname());
			info.setDescription(product.getDescription());
			info.setOriginalPrice(Double.valueOf(df.format(product.getUnitprice())).toString());
			info.setProductId(product.getProductid().toString());
			info.setProductImage(product.getProductImages().getImagename());
			info.setCategory(product.getCategories().getCategoryname());
			if ("1".equalsIgnoreCase(product.getIsDiscounted())) {
				info.setIsDiscounted(true);
				Double discountPerc = product.getDiscountperc();
				Double finalPrice = product.getUnitprice() - ((discountPerc / 100) * product.getUnitprice());
				info.setFinalPrice(Double.valueOf(df.format(finalPrice)).toString());
			} else {
				info.setIsDiscounted(false);
				info.setFinalPrice(Double.valueOf(df.format(product.getUnitprice())).toString());
			}
			products.getData().add(info);
		}
		return products;
	}

	@Override
	public ProductDetails getProductDetails(Integer productId, Integer cartId)
			throws DKartEcommerceProductsServiceException {
		ProductDetails productDetails = new ProductDetails();
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			Product product = dKartEcommerceProductsDao.getProduct(productId);
			productDetails.setProductID(product.getProductid());
			productDetails.setProductTitle(product.getProductnameExtended());
			productDetails.setProductImage(product.getProductImages().getImagename());
			productDetails.setDescription(product.getDescriptionExtended());
			productDetails.setOriginalPrice(Double.valueOf(df.format(product.getUnitprice())).toString());
			if ("1".equalsIgnoreCase(product.getIsDiscounted())) {
				Double discountPerc = product.getDiscountperc();
				Double finalPrice = product.getUnitprice() - ((discountPerc / 100) * product.getUnitprice());
				productDetails.setFinalPrice(Double.valueOf(df.format(finalPrice)).toString());
				productDetails.setIsDiscounted(true);
			} else {
				productDetails.setFinalPrice(Double.valueOf(df.format(product.getUnitprice())).toString());
				productDetails.setIsDiscounted(false);
			}
			if (cartId != null) {
				List<Cartdetails> cartDetails = dKartEcommerceProductsDao.getCartDetails(cartId, productId);
				if (cartDetails != null && !cartDetails.isEmpty()) {
					productDetails.setIsInCart(true);
				} else {
					productDetails.setIsInCart(false);
				}
			}else{
				productDetails.setIsInCart(false);
			}
		} catch (DKartEcommerceProductsDaoException e) {
			LOG.error(e.getLocalizedMessage());
			throw new DKartEcommerceProductsServiceException(dKartEcommerceProductsServiceErrors.getErrorCode1(),
					dKartEcommerceProductsServiceErrors.getErrorMessage1(), "500");
		}
		return productDetails;
	}
}
