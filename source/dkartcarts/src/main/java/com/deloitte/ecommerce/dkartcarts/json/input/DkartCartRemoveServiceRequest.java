package com.deloitte.ecommerce.dkartcarts.json.input;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cartId", "itemId" })
public class DkartCartRemoveServiceRequest {

	@JsonProperty("cartId")
	private Integer cartID;
	@JsonProperty("itemId")
	private Integer itemID;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("cartId")
	public Integer getCartID() {
		return cartID;
	}

	@JsonProperty("cartId")
	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	@JsonProperty("itemId")
	public Integer getItemID() {
		return itemID;
	}

	@JsonProperty("itemId")
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
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
