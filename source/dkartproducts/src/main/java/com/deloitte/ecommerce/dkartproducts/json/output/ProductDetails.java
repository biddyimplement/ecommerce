package com.deloitte.ecommerce.dkartproducts.json.output;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "productTitle", "productImage", "description", "isInCart" })
public class ProductDetails {

	@JsonProperty("productId")
	private Integer productID;
	@JsonProperty("productTitle")
	private String productTitle;
	@JsonProperty("productImage")
	private String productImage;
	@JsonProperty("description")
	private String description;
	@JsonProperty("originalPrice")
	private String originalPrice;
	@JsonProperty("finalPrice")
	private String finalPrice;
	@JsonProperty("isInCart")
	private Boolean isInCart;
	@JsonProperty("isDiscounted")
	private Boolean isDiscounted;
	
	

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("productId")
	public Integer getProductID() {
		return productID;
	}

	@JsonProperty("productId")
	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	@JsonProperty("productTitle")
	public String getProductTitle() {
		return productTitle;
	}

	@JsonProperty("productTitle")
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	@JsonProperty("productImage")
	public String getProductImage() {
		return productImage;
	}

	@JsonProperty("productImage")
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("isInCart")
	public Boolean getIsInCart() {
		return isInCart;
	}

	@JsonProperty("isInCart")
	public void setIsInCart(Boolean isInCart) {
		this.isInCart = isInCart;
	}

	@JsonProperty("originalPrice")
	public String getOriginalPrice() {
		return originalPrice;
	}

	@JsonProperty("originalPrice")
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	@JsonProperty("finalPrice")
	public String getFinalPrice() {
		return finalPrice;
	}

	@JsonProperty("finalPrice")
	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	@JsonProperty("isDiscounted")
	public Boolean getIsDiscounted() {
		return isDiscounted;
	}
	
	@JsonProperty("isDiscounted")
	public void setIsDiscounted(Boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
