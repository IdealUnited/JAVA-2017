package com.idealunited.dto;

import java.util.Date;

public class CurrencyExchangeRate {
	/**
	 * null
	 */
	private Long id;

	private String orgCode;

	/**
	 * 币种代码
	 */
	private String currency;

	/**
	 * 交易单位，例如：人民币：100元，日元：1000元等
	 */
	private Integer currencyUnit;

	/**
	 * 兑换的币种
	 */
	private String targetCurrency;

	/**
	 * null
	 */
	private String exchangeRate;

	private String reverseExchangeRate;

	/**
	 * null
	 */
	private Date effectDate;

	/**
	 * null
	 */
	private Date expireDate;

	/**
	 * null
	 */
	private Date createDate;

	/**
	 * null
	 */
	private Date updateDate;

	/**
	 * 0：已过期 1：正常 2：已作废 3：待审核 4：审核未通过
	 */
	private String status;

	/**
	 * null
	 */
	private String operator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(Integer currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getReverseExchangeRate() {
		return reverseExchangeRate;
	}

	public void setReverseExchangeRate(String reverseExchangeRate) {
		this.reverseExchangeRate = reverseExchangeRate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}