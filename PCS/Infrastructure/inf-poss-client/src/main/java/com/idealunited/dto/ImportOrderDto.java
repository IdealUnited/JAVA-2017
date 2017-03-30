/**
 * 
 */
package com.idealunited.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chaoyue
 *
 */
public class ImportOrderDto {

	/**
	 * 商城订单号
	 */
	private String orderId;

	/**
	 * 平台
	 */
	private String platform;

	/**
	 * 订单金额
	 */
	private BigDecimal orderAmount;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 订单状态：0-创建,1-进行中，2-完成
	 */
	private Integer status;

	/**
	 * 创建订单的日期
	 */
	private Date purchaseDate;

	/**
	 * 卖家所指定的订单编码
	 */
	private String sellerOrderid;

	/**
	 * 订单的最后更新日期
	 */
	private Date lastUpdateDate;

	/**
	 * 订单配送方式
	 */
	private String fulfillmentChannel;

	/**
	 * 销售渠道
	 */
	private String salesChannel;

	/**
	 * 订单渠道
	 */
	private String orderChannel;

	/**
	 * 货件服务水平
	 */
	private String shipserviceLevel;

	/**
	 * 币种
	 */
	private String currencyCode;

	/**
	 * 已配送的商品数量
	 */
	private Integer numberOfItemsShipped;

	/**
	 * 未配送的商品数量
	 */
	private Integer numberOfItemsUnshipped;

	/**
	 * 订单付款方式
	 */
	private String paymentMethod;

	/**
	 * 收货姓名
	 */
	private String name;

	/**
	 * 街道地址
	 */
	private String addressline1;

	/**
	 * 其他街道地址信息
	 */
	private String addressline2;

	/**
	 * 其他街道地址信息
	 */
	private String addressline3;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 区县
	 */
	private String county;

	/**
	 * 区
	 */
	private String district;

	/**
	 * 省/自治区/直辖市或地区
	 */
	private String stateorregion;

	/**
	 * 邮政编码
	 */
	private String postalcode;

	/**
	 * 两位数国家/地区代码。格式为 ISO 3166-1-alpha 2
	 */
	private String countrycode;

	/**
	 * 电话号码
	 */
	private String phone;

	/**
	 * 订单生成所在商城的匿名编码
	 */
	private String marketplaceid;

	/**
	 * 买家的电子邮件地址
	 */
	private String buyeremail;

	/**
	 * 买家姓名
	 */
	private String buyername;

	/**
	 * 订单的配送服务级别分类
	 */
	private String shipmentservicelevelcategory;

	/**
	 * 指明订单配送方是否是亚马逊配送 (Amazon TFM) 服务
	 */
	private Integer shippedbyamazontfm;

	/**
	 * 亚马逊 TFM订单的状态
	 */
	private String tfmshipmentstatus;

	/**
	 * 卖家自定义的配送方式，属于Checkout by Amazon (CBA) 所支持的四种标准配送设置中的一种
	 */
	private String cbadisplayableshippinglabel;

	/**
	 * 订单类型
	 */
	private String ordertype;

	/**
	 * 承诺的订单发货时间范围的第一天
	 */
	private Date earliestshipdate;

	/**
	 * 承诺的订单发货时间范围的最后一天
	 */
	private Date latestshipdate;

	/**
	 * 承诺的订单送达时间范围的第一天
	 */
	private Date earliestdeliverydate;

	/**
	 * 承诺的订单送达时间范围的最后一天
	 */
	private Date latestdeliverydate;

	/**
     * 
     */
	private Integer isbusinessorder;

	/**
     * 
     */
	private String purchaseordernumber;

	/**
     * 
     */
	private Integer isprime;

	/**
     * 
     */
	private Integer ispremiumorder;

	private String platformStatus;

	/**
	 * 以下是订单明细
	 */

	/**
	 * 商品的亚马逊标准识别号 (ASIN)
	 */
	private String asin;

	/**
	 * 商品的卖家 SKU
	 */
	private String sellersku;

	/**
	 * 亚马逊定义的订单商品识别号
	 */
	private String orderItemid;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 下面两个子元素的父元素
	 */
	private String quantityordered;

	/**
	 * 已配送的商品数量
	 */
	private String quantityshipped;

	/**
     * 
     */
	private Integer pointsnumber;

	/**
     * 
     */
	private String pointsCurrencycode;

	/**
     * 
     */
	private BigDecimal pointsAmount;

	/**
	 * 订单商品的售价币种
	 */
	private String itempriceCurrencycode;

	/**
	 * 订单商品的售价金额
	 */
	private BigDecimal itempriceAmount;

	/**
	 * 运费币种
	 */
	private String shippingpriceCurrencycode;

	/**
	 * 运费金额
	 */
	private BigDecimal shippingpriceAmount;

	/**
	 * 商品的礼品包装币种
	 */
	private String giftwrappriceCurrencycode;

	/**
	 * 商品的礼品包装金额
	 */
	private BigDecimal giftwrappriceAmount;

	/**
	 * 商品价格的税费币种
	 */
	private String itemtaxCurrencycode;

	/**
	 * 商品价格的税费
	 */
	private BigDecimal itemtaxAmount;

	/**
	 * 运费的税费币种
	 */
	private String shippingtaxCurrencycode;

	/**
	 * 运费的税费
	 */
	private BigDecimal shippingtaxAmount;

	/**
	 * 礼品包装金额的税费币种
	 */
	private String giftwraptaxCurrencycode;

	/**
	 * 礼品包装金额的税费
	 */
	private BigDecimal giftwraptaxAmount;

	/**
	 * 运费的折扣币种
	 */
	private String shippingdiscountCurrencycode;

	/**
	 * 运费的折扣
	 */
	private BigDecimal shippingdiscountAmount;

	/**
	 * 报价中的全部促销折扣总计
	 */
	private String promotiondiscountCurrencycode;

	/**
	 * 报价中的全部促销折扣总计
	 */
	private BigDecimal promotiondiscountAmount;

	/**
	 * PromotionId 元素列表
	 */
	private String promotionids;

	/**
	 * COD 服务费用，注： CODFee 是仅在日本 (JP) 使用的响应元素
	 */
	private String codfeeCurrencycode;

	/**
	 * COD 服务费用，注： CODFee 是仅在日本 (JP) 使用的响应元素
	 */
	private BigDecimal codfeeAmount;

	/**
	 * 货到付款费用的折扣
	 */
	private String codfeediscountCurrencycode;

	/**
	 * 货到付款费用的折扣
	 */
	private BigDecimal codfeediscountAmount;

	/**
	 * 买家提供的礼品消息
	 */
	private String giftmessagetext;

	/**
	 * 买家指定的礼品包装等级
	 */
	private String giftwraplevel;

	/**
	 * 发票要求信息
	 */
	private String invoicerequirement;

	/**
	 * 买家在下订单时选择的发票类目信息
	 */
	private String buyerselectedinvoicecategory;

	/**
	 * 买家指定的发票抬头
	 */
	private String invoicetitle;

	/**
	 * 发票信息
	 */
	private String invoiceinformation;

	/**
	 * 卖家描述的商品状况
	 */
	private String conditionnote;

	/**
	 * 商品的状况
	 */
	private String conditionid;

	/**
	 * 商品的子状况
	 */
	private String conditionsubtypeid;

	/**
	 * 订单预约送货上门的开始日期（目的地时区）
	 */
	private String scheduleddeliverystartdate;

	/**
	 * 订单预约送货上门的终止日期（目的地时区）
	 */
	private String scheduleddeliveryenddate;

	/**
     * 
     */
	private String pricedesignation;

	/**
     * 
     */
	private String customizedurl;

	/**
     * 
     */
	private Date createDate;

	/**
	 * 预计物流成本
	 */
	private BigDecimal cost;

	/**
	 * 预计毛利
	 */
	private BigDecimal profit;

	/**
	 * 物流方式
	 */
	private String logistics;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getSellerOrderid() {
		return sellerOrderid;
	}

	public void setSellerOrderid(String sellerOrderid) {
		this.sellerOrderid = sellerOrderid;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getFulfillmentChannel() {
		return fulfillmentChannel;
	}

	public void setFulfillmentChannel(String fulfillmentChannel) {
		this.fulfillmentChannel = fulfillmentChannel;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getShipserviceLevel() {
		return shipserviceLevel;
	}

	public void setShipserviceLevel(String shipserviceLevel) {
		this.shipserviceLevel = shipserviceLevel;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Integer getNumberOfItemsShipped() {
		return numberOfItemsShipped;
	}

	public void setNumberOfItemsShipped(Integer numberOfItemsShipped) {
		this.numberOfItemsShipped = numberOfItemsShipped;
	}

	public Integer getNumberOfItemsUnshipped() {
		return numberOfItemsUnshipped;
	}

	public void setNumberOfItemsUnshipped(Integer numberOfItemsUnshipped) {
		this.numberOfItemsUnshipped = numberOfItemsUnshipped;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline3() {
		return addressline3;
	}

	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStateorregion() {
		return stateorregion;
	}

	public void setStateorregion(String stateorregion) {
		this.stateorregion = stateorregion;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMarketplaceid() {
		return marketplaceid;
	}

	public void setMarketplaceid(String marketplaceid) {
		this.marketplaceid = marketplaceid;
	}

	public String getBuyeremail() {
		return buyeremail;
	}

	public void setBuyeremail(String buyeremail) {
		this.buyeremail = buyeremail;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getShipmentservicelevelcategory() {
		return shipmentservicelevelcategory;
	}

	public void setShipmentservicelevelcategory(
			String shipmentservicelevelcategory) {
		this.shipmentservicelevelcategory = shipmentservicelevelcategory;
	}

	public Integer getShippedbyamazontfm() {
		return shippedbyamazontfm;
	}

	public void setShippedbyamazontfm(Integer shippedbyamazontfm) {
		this.shippedbyamazontfm = shippedbyamazontfm;
	}

	public String getTfmshipmentstatus() {
		return tfmshipmentstatus;
	}

	public void setTfmshipmentstatus(String tfmshipmentstatus) {
		this.tfmshipmentstatus = tfmshipmentstatus;
	}

	public String getCbadisplayableshippinglabel() {
		return cbadisplayableshippinglabel;
	}

	public void setCbadisplayableshippinglabel(
			String cbadisplayableshippinglabel) {
		this.cbadisplayableshippinglabel = cbadisplayableshippinglabel;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public Date getEarliestshipdate() {
		return earliestshipdate;
	}

	public void setEarliestshipdate(Date earliestshipdate) {
		this.earliestshipdate = earliestshipdate;
	}

	public Date getLatestshipdate() {
		return latestshipdate;
	}

	public void setLatestshipdate(Date latestshipdate) {
		this.latestshipdate = latestshipdate;
	}

	public Date getEarliestdeliverydate() {
		return earliestdeliverydate;
	}

	public void setEarliestdeliverydate(Date earliestdeliverydate) {
		this.earliestdeliverydate = earliestdeliverydate;
	}

	public Date getLatestdeliverydate() {
		return latestdeliverydate;
	}

	public void setLatestdeliverydate(Date latestdeliverydate) {
		this.latestdeliverydate = latestdeliverydate;
	}

	public Integer getIsbusinessorder() {
		return isbusinessorder;
	}

	public void setIsbusinessorder(Integer isbusinessorder) {
		this.isbusinessorder = isbusinessorder;
	}

	public String getPurchaseordernumber() {
		return purchaseordernumber;
	}

	public void setPurchaseordernumber(String purchaseordernumber) {
		this.purchaseordernumber = purchaseordernumber;
	}

	public Integer getIsprime() {
		return isprime;
	}

	public void setIsprime(Integer isprime) {
		this.isprime = isprime;
	}

	public Integer getIspremiumorder() {
		return ispremiumorder;
	}

	public void setIspremiumorder(Integer ispremiumorder) {
		this.ispremiumorder = ispremiumorder;
	}

	public String getPlatformStatus() {
		return platformStatus;
	}

	public void setPlatformStatus(String platformStatus) {
		this.platformStatus = platformStatus;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getSellersku() {
		return sellersku;
	}

	public void setSellersku(String sellersku) {
		this.sellersku = sellersku;
	}

	public String getOrderItemid() {
		return orderItemid;
	}

	public void setOrderItemid(String orderItemid) {
		this.orderItemid = orderItemid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuantityordered() {
		return quantityordered;
	}

	public void setQuantityordered(String quantityordered) {
		this.quantityordered = quantityordered;
	}

	public String getQuantityshipped() {
		return quantityshipped;
	}

	public void setQuantityshipped(String quantityshipped) {
		this.quantityshipped = quantityshipped;
	}

	public Integer getPointsnumber() {
		return pointsnumber;
	}

	public void setPointsnumber(Integer pointsnumber) {
		this.pointsnumber = pointsnumber;
	}

	public String getPointsCurrencycode() {
		return pointsCurrencycode;
	}

	public void setPointsCurrencycode(String pointsCurrencycode) {
		this.pointsCurrencycode = pointsCurrencycode;
	}

	public BigDecimal getPointsAmount() {
		return pointsAmount;
	}

	public void setPointsAmount(BigDecimal pointsAmount) {
		this.pointsAmount = pointsAmount;
	}

	public String getItempriceCurrencycode() {
		return itempriceCurrencycode;
	}

	public void setItempriceCurrencycode(String itempriceCurrencycode) {
		this.itempriceCurrencycode = itempriceCurrencycode;
	}

	public BigDecimal getItempriceAmount() {
		return itempriceAmount;
	}

	public void setItempriceAmount(BigDecimal itempriceAmount) {
		this.itempriceAmount = itempriceAmount;
	}

	public String getShippingpriceCurrencycode() {
		return shippingpriceCurrencycode;
	}

	public void setShippingpriceCurrencycode(String shippingpriceCurrencycode) {
		this.shippingpriceCurrencycode = shippingpriceCurrencycode;
	}

	public BigDecimal getShippingpriceAmount() {
		return shippingpriceAmount;
	}

	public void setShippingpriceAmount(BigDecimal shippingpriceAmount) {
		this.shippingpriceAmount = shippingpriceAmount;
	}

	public String getGiftwrappriceCurrencycode() {
		return giftwrappriceCurrencycode;
	}

	public void setGiftwrappriceCurrencycode(String giftwrappriceCurrencycode) {
		this.giftwrappriceCurrencycode = giftwrappriceCurrencycode;
	}

	public BigDecimal getGiftwrappriceAmount() {
		return giftwrappriceAmount;
	}

	public void setGiftwrappriceAmount(BigDecimal giftwrappriceAmount) {
		this.giftwrappriceAmount = giftwrappriceAmount;
	}

	public String getItemtaxCurrencycode() {
		return itemtaxCurrencycode;
	}

	public void setItemtaxCurrencycode(String itemtaxCurrencycode) {
		this.itemtaxCurrencycode = itemtaxCurrencycode;
	}

	public BigDecimal getItemtaxAmount() {
		return itemtaxAmount;
	}

	public void setItemtaxAmount(BigDecimal itemtaxAmount) {
		this.itemtaxAmount = itemtaxAmount;
	}

	public String getShippingtaxCurrencycode() {
		return shippingtaxCurrencycode;
	}

	public void setShippingtaxCurrencycode(String shippingtaxCurrencycode) {
		this.shippingtaxCurrencycode = shippingtaxCurrencycode;
	}

	public BigDecimal getShippingtaxAmount() {
		return shippingtaxAmount;
	}

	public void setShippingtaxAmount(BigDecimal shippingtaxAmount) {
		this.shippingtaxAmount = shippingtaxAmount;
	}

	public String getGiftwraptaxCurrencycode() {
		return giftwraptaxCurrencycode;
	}

	public void setGiftwraptaxCurrencycode(String giftwraptaxCurrencycode) {
		this.giftwraptaxCurrencycode = giftwraptaxCurrencycode;
	}

	public BigDecimal getGiftwraptaxAmount() {
		return giftwraptaxAmount;
	}

	public void setGiftwraptaxAmount(BigDecimal giftwraptaxAmount) {
		this.giftwraptaxAmount = giftwraptaxAmount;
	}

	public String getShippingdiscountCurrencycode() {
		return shippingdiscountCurrencycode;
	}

	public void setShippingdiscountCurrencycode(
			String shippingdiscountCurrencycode) {
		this.shippingdiscountCurrencycode = shippingdiscountCurrencycode;
	}

	public BigDecimal getShippingdiscountAmount() {
		return shippingdiscountAmount;
	}

	public void setShippingdiscountAmount(BigDecimal shippingdiscountAmount) {
		this.shippingdiscountAmount = shippingdiscountAmount;
	}

	public String getPromotiondiscountCurrencycode() {
		return promotiondiscountCurrencycode;
	}

	public void setPromotiondiscountCurrencycode(
			String promotiondiscountCurrencycode) {
		this.promotiondiscountCurrencycode = promotiondiscountCurrencycode;
	}

	public BigDecimal getPromotiondiscountAmount() {
		return promotiondiscountAmount;
	}

	public void setPromotiondiscountAmount(BigDecimal promotiondiscountAmount) {
		this.promotiondiscountAmount = promotiondiscountAmount;
	}

	public String getPromotionids() {
		return promotionids;
	}

	public void setPromotionids(String promotionids) {
		this.promotionids = promotionids;
	}

	public String getCodfeeCurrencycode() {
		return codfeeCurrencycode;
	}

	public void setCodfeeCurrencycode(String codfeeCurrencycode) {
		this.codfeeCurrencycode = codfeeCurrencycode;
	}

	public BigDecimal getCodfeeAmount() {
		return codfeeAmount;
	}

	public void setCodfeeAmount(BigDecimal codfeeAmount) {
		this.codfeeAmount = codfeeAmount;
	}

	public String getCodfeediscountCurrencycode() {
		return codfeediscountCurrencycode;
	}

	public void setCodfeediscountCurrencycode(String codfeediscountCurrencycode) {
		this.codfeediscountCurrencycode = codfeediscountCurrencycode;
	}

	public BigDecimal getCodfeediscountAmount() {
		return codfeediscountAmount;
	}

	public void setCodfeediscountAmount(BigDecimal codfeediscountAmount) {
		this.codfeediscountAmount = codfeediscountAmount;
	}

	public String getGiftmessagetext() {
		return giftmessagetext;
	}

	public void setGiftmessagetext(String giftmessagetext) {
		this.giftmessagetext = giftmessagetext;
	}

	public String getGiftwraplevel() {
		return giftwraplevel;
	}

	public void setGiftwraplevel(String giftwraplevel) {
		this.giftwraplevel = giftwraplevel;
	}

	public String getInvoicerequirement() {
		return invoicerequirement;
	}

	public void setInvoicerequirement(String invoicerequirement) {
		this.invoicerequirement = invoicerequirement;
	}

	public String getBuyerselectedinvoicecategory() {
		return buyerselectedinvoicecategory;
	}

	public void setBuyerselectedinvoicecategory(
			String buyerselectedinvoicecategory) {
		this.buyerselectedinvoicecategory = buyerselectedinvoicecategory;
	}

	public String getInvoicetitle() {
		return invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}

	public String getInvoiceinformation() {
		return invoiceinformation;
	}

	public void setInvoiceinformation(String invoiceinformation) {
		this.invoiceinformation = invoiceinformation;
	}

	public String getConditionnote() {
		return conditionnote;
	}

	public void setConditionnote(String conditionnote) {
		this.conditionnote = conditionnote;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public String getConditionsubtypeid() {
		return conditionsubtypeid;
	}

	public void setConditionsubtypeid(String conditionsubtypeid) {
		this.conditionsubtypeid = conditionsubtypeid;
	}

	public String getScheduleddeliverystartdate() {
		return scheduleddeliverystartdate;
	}

	public void setScheduleddeliverystartdate(String scheduleddeliverystartdate) {
		this.scheduleddeliverystartdate = scheduleddeliverystartdate;
	}

	public String getScheduleddeliveryenddate() {
		return scheduleddeliveryenddate;
	}

	public void setScheduleddeliveryenddate(String scheduleddeliveryenddate) {
		this.scheduleddeliveryenddate = scheduleddeliveryenddate;
	}

	public String getPricedesignation() {
		return pricedesignation;
	}

	public void setPricedesignation(String pricedesignation) {
		this.pricedesignation = pricedesignation;
	}

	public String getCustomizedurl() {
		return customizedurl;
	}

	public void setCustomizedurl(String customizedurl) {
		this.customizedurl = customizedurl;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

}
