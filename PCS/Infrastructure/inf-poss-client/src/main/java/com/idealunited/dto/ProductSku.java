package com.idealunited.dto;

import java.util.Date;

public class ProductSku {

	/**
     * 
     */
	private String skuCode;

	/**
     * 
     */
	private String platform;

	/**
	 * 产品名称，对应库存里产品名称
	 */
	private String productName;

	/**
     * 
     */
	private String zhName;

	/**
	 * 库位号
	 */
	private String stockCode;

	/**
     * 
     */
	private String enName;

	/**
	 * 1-创建,2-已复核，3-失效
	 */
	private Integer status;

	/**
	 * 操作员
	 */
	private String creator;

	/**
	 * 复核员
	 */
	private String auditor;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 优先物流
	 */
	private String priorityLogistics;

	/**
	 * 提成方式：1-%，2-Flat
	 */
	private Integer percentageType;

	/**
	 * 提成比例
	 */
	private String percentage;

	/**
	 * 仓库
	 */
	private String repository;

	/**
	 * Amazon平台必填
	 */
	private String asin;

	// 优先物流
	// UK DE FR ES IT US AU CA AT CH BE IE
	private String uk;
	private String de;
	private String fr;
	private String es;
	private String it;
	private String us;
	private String au;
	private String ca;
	private String at;
	private String ch;
	private String be;
	private String ie;

	/**
	 *
	 * @return the value of t_product_sku.SKU_CODE
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
     *
     */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 *
	 * @return the value of t_product_sku.PLATFORM
	 */
	public String getPlatform() {
		return platform;
	}

	/**
     *
     */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 *
	 * @return the value of t_product_sku.PRODUCT_NAME
	 */
	public String getProductName() {
		return productName;
	}

	/**
     *
     */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 *
	 * @return the value of t_product_sku.ZH_NAME
	 */
	public String getZhName() {
		return zhName;
	}

	/**
     *
     */
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	/**
	 *
	 * @return the value of t_product_sku.STOCK_CODE
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
     *
     */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	/**
	 *
	 * @return the value of t_product_sku.EN_NAME
	 */
	public String getEnName() {
		return enName;
	}

	/**
     *
     */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
	 *
	 * @return the value of t_product_sku.STATUS
	 */
	public Integer getStatus() {
		return status;
	}

	/**
     *
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 *
	 * @return the value of t_product_sku.CREATOR
	 */
	public String getCreator() {
		return creator;
	}

	/**
     *
     */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 *
	 * @return the value of t_product_sku.AUDITOR
	 */
	public String getAuditor() {
		return auditor;
	}

	/**
     *
     */
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	/**
	 *
	 * @return the value of t_product_sku.CREATE_DATE
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
     *
     */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 *
	 * @return the value of t_product_sku.percentage_type
	 */
	public Integer getPercentageType() {
		return percentageType;
	}

	/**
     *
     */
	public void setPercentageType(Integer percentageType) {
		this.percentageType = percentageType;
	}

	/**
	 *
	 * @return the value of t_product_sku.percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
     *
     */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	/**
	 *
	 * @return the value of t_product_sku.repository
	 */
	public String getRepository() {
		return repository;
	}

	/**
     *
     */
	public void setRepository(String repository) {
		this.repository = repository;
	}

	/**
	 *
	 * @return the value of t_product_sku.asin
	 */
	public String getAsin() {
		return asin;
	}

	/**
     *
     */
	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getPriorityLogistics() {
		return priorityLogistics;
	}

	public void setPriorityLogistics(String priorityLogistics) {
		this.priorityLogistics = priorityLogistics;
	}

	public String getUk() {
		return uk;
	}

	public void setUk(String uk) {
		this.uk = uk;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getEs() {
		return es;
	}

	public void setEs(String es) {
		this.es = es;
	}

	public String getIt() {
		return it;
	}

	public void setIt(String it) {
		this.it = it;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public String getAu() {
		return au;
	}

	public void setAu(String au) {
		this.au = au;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getBe() {
		return be;
	}

	public void setBe(String be) {
		this.be = be;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

}