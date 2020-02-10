package com.deloitte.ecommerce.dkartcarts.json.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "quantity",
    "productId",
    "cartId",
    "size"
})
public class DkartcartsServiceRequest {
	
	@JsonProperty("quantity")
	private Integer quantity;
	
	@JsonProperty("productId")
	private Integer productId;
	
	@JsonProperty("cartId")
	private Integer cartId;
	
	@JsonProperty("size")
	private String size;
	
	@JsonProperty("quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@JsonProperty("productId")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@JsonProperty("cartId")
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
	@JsonProperty("size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
}
