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
@JsonPropertyOrder({ "productId", "title", "description", "originalPrice", "finalPrice", "productImage" ,"category"})
public class ProductInfo {

	@JsonProperty("productId")
	private String productId;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("originalPrice")
	private String originalPrice;
	@JsonProperty("finalPrice")
	private String finalPrice;
	@JsonProperty("productImage")
	private String productImage;
	@JsonProperty("category")
	private String category;
	@JsonProperty("isDiscounted")
	private Boolean isDiscounted;
	

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

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
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

	@JsonProperty("productImage")
	public String getProductImage() {
		return productImage;
	}

	@JsonProperty("productImage")
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	@JsonProperty("category")
	public String getCategory() {
		return category;
	}
	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
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