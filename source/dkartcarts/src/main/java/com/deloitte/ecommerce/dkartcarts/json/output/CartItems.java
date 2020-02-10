package com.deloitte.ecommerce.dkartcarts.json.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ 
	"cartItems", 
	"totalPrice", 
	"cartID" 
})
public class CartItems {

	@JsonProperty("cartItems")
	private List<CartItem> cartItems = null;
	@JsonProperty("totalPrice")
	private String totalPrice;
	@JsonProperty("cartID")
	private Integer cartID;
	@JsonProperty("totalOriginalPrice")
	private String totalOriginalPrice;
	@JsonProperty("savings")
	private String savings;
	@JsonProperty("isDiscounted")
	private Boolean isDiscounted;
	@JsonProperty("totalOriginalPrice")
	public String getTotalOriginalPrice() {
		return totalOriginalPrice;
	}
	@JsonProperty("totalOriginalPrice")
	public void setTotalOriginalPrice(String totalOriginalPrice) {
		this.totalOriginalPrice = totalOriginalPrice;
	}
	@JsonProperty("savings")
	public String getSavings() {
		return savings;
	}
	@JsonProperty("savings")
	public void setSavings(String savings) {
		this.savings = savings;
	}
	@JsonProperty("isDiscounted")
	public Boolean getIsDiscounted() {
		return isDiscounted;
	}
	@JsonProperty("isDiscounted")
	public void setIsDiscounted(Boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("cartItems")
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	@JsonProperty("cartItems")
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@JsonProperty("totalPrice")
	public String getTotalPrice() {
		return totalPrice;
	}

	@JsonProperty("totalPrice")
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@JsonProperty("cartID")
	public Integer getCartID() {
		return cartID;
	}

	@JsonProperty("cartID")
	public void setCartID(Integer cartID) {
		this.cartID = cartID;
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