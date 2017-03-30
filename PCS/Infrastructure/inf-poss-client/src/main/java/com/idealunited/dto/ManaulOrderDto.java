/**
 * 
 */
package com.idealunited.dto;

/**
 * @author chaoyue
 *
 */
public class ManaulOrderDto {

	/**
	 * 发货方式
	 */
	private String expressChannelId;
	
	private String expressChannelItemId;

	/**
	 * 订单号
	 */
	private String orderId;
	
	private String platform;

	/**
	 * 发货日期
	 */
	private String earliestshipdate;

	private String name;

	private String phone;

	private String country;

	private String postalCode;

	private String stateOrRegion;

	private String city;

	private String addressLine1;

	private String zhType;

	private String enType;

	private String numberOfItemsUnshipped;

	private String weight;

	private String currencyCode;

	private String declargPrices;

	private String productName;

	private String remark;
	
	private String orderAmount;

	public String getExpressChannelId() {
		return expressChannelId;
	}

	public void setExpressChannelId(String expressChannelId) {
		this.expressChannelId = expressChannelId;
	}

	public String getExpressChannelItemId() {
		return expressChannelItemId;
	}

	public void setExpressChannelItemId(String expressChannelItemId) {
		this.expressChannelItemId = expressChannelItemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getEarliestshipdate() {
		return earliestshipdate;
	}

	public void setEarliestshipdate(String earliestshipdate) {
		this.earliestshipdate = earliestshipdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateOrRegion() {
		return stateOrRegion;
	}

	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getZhType() {
		return zhType;
	}

	public void setZhType(String zhType) {
		this.zhType = zhType;
	}

	public String getEnType() {
		return enType;
	}

	public void setEnType(String enType) {
		this.enType = enType;
	}

	public String getNumberOfItemsUnshipped() {
		return numberOfItemsUnshipped;
	}

	public void setNumberOfItemsUnshipped(String numberOfItemsUnshipped) {
		this.numberOfItemsUnshipped = numberOfItemsUnshipped;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDeclargPrices() {
		return declargPrices;
	}

	public void setDeclargPrices(String declargPrices) {
		this.declargPrices = declargPrices;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

}
