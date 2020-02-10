package com.deloitte.ecommerce.dkartcarts.json.output;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ 
	"productId", 
	"title", 
	"productImage", 
	"itemId", 
	"specification" 
})
public class CartItem {

	@JsonProperty("productId")
	private String productId;
	@JsonProperty("title")
	private String title;
	@JsonProperty("productImage")
	private String productImage;
	@JsonProperty("itemId")
	private Integer itemID;
	@JsonProperty("specification")
	private Specification specification;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("productId")
	public String getProductId() {
		return productId;
	}

	@JsonProperty("productId")
	public void setProductId(String productId) {
		this.productId = productId;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("productImage")
	public String getProductImage() {
		return productImage;
	}

	@JsonProperty("productImage")
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@JsonProperty("itemId")
	public Integer getItemID() {
		return itemID;
	}

	@JsonProperty("itemId")
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	@JsonProperty("specification")
	public Specification getSpecification() {
		return specification;
	}

	@JsonProperty("specification")
	public void setSpecification(Specification specification) {
		this.specification = specification;
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