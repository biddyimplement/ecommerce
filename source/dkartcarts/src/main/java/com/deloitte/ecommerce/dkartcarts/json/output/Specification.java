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
	"noOfItems", 
	"itemPrice", 
	"itemFormattedPrice", 
	"totalPrice", 
	"totalFormattedPrice" 
})
public class Specification {

	@JsonProperty("noOfItems")
	private Integer noOfItems;
	@JsonProperty("itemPrice")
	private Double itemPrice;
	@JsonProperty("itemFormattedPrice")
	private String itemFormattedPrice;
	@JsonProperty("totalPrice")
	private double totalPrice;
	@JsonProperty("totalFormattedPrice")
	private String totalFormattedPrice;
	
	@JsonProperty("itemOriginalPrice")
	private String itemOriginalPrice;
	
	@JsonProperty("itemOriginalFormattedPrice")
	private String itemOriginalFormattedPrice;
	
	@JsonProperty("totalOriginalPrice")
	private String totalOriginalPrice;
	
	@JsonProperty("totalOriginalFormattedPrice")
	private String totalOriginalFormattedPrice;
	
	@JsonProperty("isDiscounted")
	private Boolean isDiscounted;
	
	@JsonProperty("itemOriginalPrice")
	public String getItemOriginalPrice() {
		return itemOriginalPrice;
	}
	@JsonProperty("itemOriginalPrice")
	public void setItemOriginalPrice(String itemOriginalPrice) {
		this.itemOriginalPrice = itemOriginalPrice;
	}
	@JsonProperty("itemOriginalFormattedPrice")
	public String getItemOriginalFormattedPrice() {
		return itemOriginalFormattedPrice;
	}
	@JsonProperty("itemOriginalFormattedPrice")
	public void setItemOriginalFormattedPrice(String itemOriginalFormattedPrice) {
		this.itemOriginalFormattedPrice = itemOriginalFormattedPrice;
	}
	@JsonProperty("totalOriginalPrice")
	public String getTotalOriginalPrice() {
		return totalOriginalPrice;
	}
	@JsonProperty("totalOriginalPrice")
	public void setTotalOriginalPrice(String totalOriginalPrice) {
		this.totalOriginalPrice = totalOriginalPrice;
	}
	@JsonProperty("totalOriginalFormattedPrice")
	public String getTotalOriginalFormattedPrice() {
		return totalOriginalFormattedPrice;
	}
	@JsonProperty("totalOriginalFormattedPrice")
	public void setTotalOriginalFormattedPrice(String totalOriginalFormattedPrice) {
		this.totalOriginalFormattedPrice = totalOriginalFormattedPrice;
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

	@JsonProperty("noOfItems")
	public Integer getNoOfItems() {
		return noOfItems;
	}

	@JsonProperty("noOfItems")
	public void setNoOfItems(Integer noOfItems) {
		this.noOfItems = noOfItems;
	}

	@JsonProperty("itemPrice")
	public Double getItemPrice() {
		return itemPrice;
	}

	@JsonProperty("itemPrice")
	public void setItemPrice(Double double1) {
		this.itemPrice = double1;
	}

	@JsonProperty("itemFormattedPrice")
	public String getItemFormattedPrice() {
		return itemFormattedPrice;
	}

	@JsonProperty("itemFormattedPrice")
	public void setItemFormattedPrice(String itemFormattedPrice) {
		this.itemFormattedPrice = itemFormattedPrice;
	}

	@JsonProperty("totalPrice")
	public double getTotalPrice() {
		return totalPrice;
	}

	@JsonProperty("totalPrice")
	public void setTotalPrice(double d) {
		this.totalPrice = d;
	}

	@JsonProperty("totalFormattedPrice")
	public String getTotalFormattedPrice() {
		return totalFormattedPrice;
	}

	@JsonProperty("totalFormattedPrice")
	public void setTotalFormattedPrice(String totalFormattedPrice) {
		this.totalFormattedPrice = totalFormattedPrice;
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