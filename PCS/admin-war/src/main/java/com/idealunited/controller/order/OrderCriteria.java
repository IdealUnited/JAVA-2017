package com.idealunited.controller.order;

public class OrderCriteria {

	private String orderId;

	private String skuCode;

	private String countryCode;

	private String platform;

	private String orderAmountOperator;

	private String orderAmount;

	private String costOperator;

	private String cost;

	private String profitOperator;

	private String profit;

	private String weightOperator;

	private String weight;

	private String itemStatus;
	
	private String beginTime;
	
	private String endTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOrderAmountOperator() {
		return orderAmountOperator;
	}

	public void setOrderAmountOperator(String orderAmountOperator) {
		this.orderAmountOperator = orderAmountOperator;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getCostOperator() {
		return costOperator;
	}

	public void setCostOperator(String costOperator) {
		this.costOperator = costOperator;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getProfitOperator() {
		return profitOperator;
	}

	public void setProfitOperator(String profitOperator) {
		this.profitOperator = profitOperator;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getWeightOperator() {
		return weightOperator;
	}

	public void setWeightOperator(String weightOperator) {
		this.weightOperator = weightOperator;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
