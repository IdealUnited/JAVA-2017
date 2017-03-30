package com.idealunited.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TradeOrderItem {
    /**
     * 
     */
    private String tradeOrderItemNo;

    /**
     * 
     */
    private String tradeOrderNo;

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

    /**
     * 
     */
    private Date completedate;

    /**
     * 订单状态：0-创建,1-进行中，2-成功，3-滞留
     */
    private Integer status;

    /**
     * 滞留原因
     */
    private String retentionReason;
    
    private String expressChannelItem;

    /**
     *
     * @return the value of t_trade_order_item.TRADE_ORDER_ITEM_NO
     */
    public String getTradeOrderItemNo() {
        return tradeOrderItemNo;
    }

    /**
     *
     */
    public void setTradeOrderItemNo(String tradeOrderItemNo) {
        this.tradeOrderItemNo = tradeOrderItemNo;
    }

    /**
     *
     * @return the value of t_trade_order_item.TRADE_ORDER_NO
     */
    public String getTradeOrderNo() {
        return tradeOrderNo;
    }

    /**
     *
     */
    public void setTradeOrderNo(String tradeOrderNo) {
        this.tradeOrderNo = tradeOrderNo;
    }

    /**
     *
     * @return the value of t_trade_order_item.asin
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

    /**
     *
     * @return the value of t_trade_order_item.sellerSKU
     */
    public String getSellersku() {
        return sellersku;
    }

    /**
     *
     */
    public void setSellersku(String sellersku) {
        this.sellersku = sellersku;
    }

    /**
     *
     * @return the value of t_trade_order_item.order_ItemId
     */
    public String getOrderItemid() {
        return orderItemid;
    }

    /**
     *
     */
    public void setOrderItemid(String orderItemid) {
        this.orderItemid = orderItemid;
    }

    /**
     *
     * @return the value of t_trade_order_item.title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the value of t_trade_order_item.quantityOrdered
     */
    public String getQuantityordered() {
        return quantityordered;
    }

    /**
     *
     */
    public void setQuantityordered(String quantityordered) {
        this.quantityordered = quantityordered;
    }

    /**
     *
     * @return the value of t_trade_order_item.quantityShipped
     */
    public String getQuantityshipped() {
        return quantityshipped;
    }

    /**
     *
     */
    public void setQuantityshipped(String quantityshipped) {
        this.quantityshipped = quantityshipped;
    }

    /**
     *
     * @return the value of t_trade_order_item.pointsNumber
     */
    public Integer getPointsnumber() {
        return pointsnumber;
    }

    /**
     *
     */
    public void setPointsnumber(Integer pointsnumber) {
        this.pointsnumber = pointsnumber;
    }

    /**
     *
     * @return the value of t_trade_order_item.points_currencyCode
     */
    public String getPointsCurrencycode() {
        return pointsCurrencycode;
    }

    /**
     *
     */
    public void setPointsCurrencycode(String pointsCurrencycode) {
        this.pointsCurrencycode = pointsCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.points_amount
     */
    public BigDecimal getPointsAmount() {
        return pointsAmount;
    }

    /**
     *
     */
    public void setPointsAmount(BigDecimal pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.itemPrice_currencyCode
     */
    public String getItempriceCurrencycode() {
        return itempriceCurrencycode;
    }

    /**
     *
     */
    public void setItempriceCurrencycode(String itempriceCurrencycode) {
        this.itempriceCurrencycode = itempriceCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.itemPrice_amount
     */
    public BigDecimal getItempriceAmount() {
        return itempriceAmount;
    }

    /**
     *
     */
    public void setItempriceAmount(BigDecimal itempriceAmount) {
        this.itempriceAmount = itempriceAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.shippingPrice_currencyCode
     */
    public String getShippingpriceCurrencycode() {
        return shippingpriceCurrencycode;
    }

    /**
     *
     */
    public void setShippingpriceCurrencycode(String shippingpriceCurrencycode) {
        this.shippingpriceCurrencycode = shippingpriceCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.shippingPrice_amount
     */
    public BigDecimal getShippingpriceAmount() {
        return shippingpriceAmount;
    }

    /**
     *
     */
    public void setShippingpriceAmount(BigDecimal shippingpriceAmount) {
        this.shippingpriceAmount = shippingpriceAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.giftWrapPrice_currencyCode
     */
    public String getGiftwrappriceCurrencycode() {
        return giftwrappriceCurrencycode;
    }

    /**
     *
     */
    public void setGiftwrappriceCurrencycode(String giftwrappriceCurrencycode) {
        this.giftwrappriceCurrencycode = giftwrappriceCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.giftWrapPrice_amount
     */
    public BigDecimal getGiftwrappriceAmount() {
        return giftwrappriceAmount;
    }

    /**
     *
     */
    public void setGiftwrappriceAmount(BigDecimal giftwrappriceAmount) {
        this.giftwrappriceAmount = giftwrappriceAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.itemTax_currencyCode
     */
    public String getItemtaxCurrencycode() {
        return itemtaxCurrencycode;
    }

    /**
     *
     */
    public void setItemtaxCurrencycode(String itemtaxCurrencycode) {
        this.itemtaxCurrencycode = itemtaxCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.itemTax_amount
     */
    public BigDecimal getItemtaxAmount() {
        return itemtaxAmount;
    }

    /**
     *
     */
    public void setItemtaxAmount(BigDecimal itemtaxAmount) {
        this.itemtaxAmount = itemtaxAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.shippingTax_currencyCode
     */
    public String getShippingtaxCurrencycode() {
        return shippingtaxCurrencycode;
    }

    /**
     *
     */
    public void setShippingtaxCurrencycode(String shippingtaxCurrencycode) {
        this.shippingtaxCurrencycode = shippingtaxCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.shippingTax_amount
     */
    public BigDecimal getShippingtaxAmount() {
        return shippingtaxAmount;
    }

    /**
     *
     */
    public void setShippingtaxAmount(BigDecimal shippingtaxAmount) {
        this.shippingtaxAmount = shippingtaxAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.giftWrapTax_currencyCode
     */
    public String getGiftwraptaxCurrencycode() {
        return giftwraptaxCurrencycode;
    }

    /**
     *
     */
    public void setGiftwraptaxCurrencycode(String giftwraptaxCurrencycode) {
        this.giftwraptaxCurrencycode = giftwraptaxCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.giftWrapTax_amount
     */
    public BigDecimal getGiftwraptaxAmount() {
        return giftwraptaxAmount;
    }

    /**
     *
     */
    public void setGiftwraptaxAmount(BigDecimal giftwraptaxAmount) {
        this.giftwraptaxAmount = giftwraptaxAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.shippingDiscount_currencyCode
     */
    public String getShippingdiscountCurrencycode() {
        return shippingdiscountCurrencycode;
    }

    /**
     *
     */
    public void setShippingdiscountCurrencycode(String shippingdiscountCurrencycode) {
        this.shippingdiscountCurrencycode = shippingdiscountCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.shippingDiscount_amount
     */
    public BigDecimal getShippingdiscountAmount() {
        return shippingdiscountAmount;
    }

    /**
     *
     */
    public void setShippingdiscountAmount(BigDecimal shippingdiscountAmount) {
        this.shippingdiscountAmount = shippingdiscountAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.promotionDiscount_currencyCode
     */
    public String getPromotiondiscountCurrencycode() {
        return promotiondiscountCurrencycode;
    }

    /**
     *
     */
    public void setPromotiondiscountCurrencycode(String promotiondiscountCurrencycode) {
        this.promotiondiscountCurrencycode = promotiondiscountCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.promotionDiscount_amount
     */
    public BigDecimal getPromotiondiscountAmount() {
        return promotiondiscountAmount;
    }

    /**
     *
     */
    public void setPromotiondiscountAmount(BigDecimal promotiondiscountAmount) {
        this.promotiondiscountAmount = promotiondiscountAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.promotionIds
     */
    public String getPromotionids() {
        return promotionids;
    }

    /**
     *
     */
    public void setPromotionids(String promotionids) {
        this.promotionids = promotionids;
    }

    /**
     *
     * @return the value of t_trade_order_item.codFee_currencyCode
     */
    public String getCodfeeCurrencycode() {
        return codfeeCurrencycode;
    }

    /**
     *
     */
    public void setCodfeeCurrencycode(String codfeeCurrencycode) {
        this.codfeeCurrencycode = codfeeCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.codFee_amount
     */
    public BigDecimal getCodfeeAmount() {
        return codfeeAmount;
    }

    /**
     *
     */
    public void setCodfeeAmount(BigDecimal codfeeAmount) {
        this.codfeeAmount = codfeeAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.codFeeDiscount_currencyCode
     */
    public String getCodfeediscountCurrencycode() {
        return codfeediscountCurrencycode;
    }

    /**
     *
     */
    public void setCodfeediscountCurrencycode(String codfeediscountCurrencycode) {
        this.codfeediscountCurrencycode = codfeediscountCurrencycode;
    }

    /**
     *
     * @return the value of t_trade_order_item.codFeeDiscount_amount
     */
    public BigDecimal getCodfeediscountAmount() {
        return codfeediscountAmount;
    }

    /**
     *
     */
    public void setCodfeediscountAmount(BigDecimal codfeediscountAmount) {
        this.codfeediscountAmount = codfeediscountAmount;
    }

    /**
     *
     * @return the value of t_trade_order_item.giftMessageText
     */
    public String getGiftmessagetext() {
        return giftmessagetext;
    }

    /**
     *
     */
    public void setGiftmessagetext(String giftmessagetext) {
        this.giftmessagetext = giftmessagetext;
    }

    /**
     *
     * @return the value of t_trade_order_item.giftWrapLevel
     */
    public String getGiftwraplevel() {
        return giftwraplevel;
    }

    /**
     *
     */
    public void setGiftwraplevel(String giftwraplevel) {
        this.giftwraplevel = giftwraplevel;
    }

    /**
     *
     * @return the value of t_trade_order_item.invoiceRequirement
     */
    public String getInvoicerequirement() {
        return invoicerequirement;
    }

    /**
     *
     */
    public void setInvoicerequirement(String invoicerequirement) {
        this.invoicerequirement = invoicerequirement;
    }

    /**
     *
     * @return the value of t_trade_order_item.buyerSelectedInvoiceCategory
     */
    public String getBuyerselectedinvoicecategory() {
        return buyerselectedinvoicecategory;
    }

    /**
     *
     */
    public void setBuyerselectedinvoicecategory(String buyerselectedinvoicecategory) {
        this.buyerselectedinvoicecategory = buyerselectedinvoicecategory;
    }

    /**
     *
     * @return the value of t_trade_order_item.invoiceTitle
     */
    public String getInvoicetitle() {
        return invoicetitle;
    }

    /**
     *
     */
    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    /**
     *
     * @return the value of t_trade_order_item.invoiceInformation
     */
    public String getInvoiceinformation() {
        return invoiceinformation;
    }

    /**
     *
     */
    public void setInvoiceinformation(String invoiceinformation) {
        this.invoiceinformation = invoiceinformation;
    }

    /**
     *
     * @return the value of t_trade_order_item.conditionNote
     */
    public String getConditionnote() {
        return conditionnote;
    }

    /**
     *
     */
    public void setConditionnote(String conditionnote) {
        this.conditionnote = conditionnote;
    }

    /**
     *
     * @return the value of t_trade_order_item.conditionId
     */
    public String getConditionid() {
        return conditionid;
    }

    /**
     *
     */
    public void setConditionid(String conditionid) {
        this.conditionid = conditionid;
    }

    /**
     *
     * @return the value of t_trade_order_item.conditionSubtypeId
     */
    public String getConditionsubtypeid() {
        return conditionsubtypeid;
    }

    /**
     *
     */
    public void setConditionsubtypeid(String conditionsubtypeid) {
        this.conditionsubtypeid = conditionsubtypeid;
    }

    /**
     *
     * @return the value of t_trade_order_item.scheduledDeliveryStartDate
     */
    public String getScheduleddeliverystartdate() {
        return scheduleddeliverystartdate;
    }

    /**
     *
     */
    public void setScheduleddeliverystartdate(String scheduleddeliverystartdate) {
        this.scheduleddeliverystartdate = scheduleddeliverystartdate;
    }

    /**
     *
     * @return the value of t_trade_order_item.scheduledDeliveryEndDate
     */
    public String getScheduleddeliveryenddate() {
        return scheduleddeliveryenddate;
    }

    /**
     *
     */
    public void setScheduleddeliveryenddate(String scheduleddeliveryenddate) {
        this.scheduleddeliveryenddate = scheduleddeliveryenddate;
    }

    /**
     *
     * @return the value of t_trade_order_item.priceDesignation
     */
    public String getPricedesignation() {
        return pricedesignation;
    }

    /**
     *
     */
    public void setPricedesignation(String pricedesignation) {
        this.pricedesignation = pricedesignation;
    }

    /**
     *
     * @return the value of t_trade_order_item.customizedURL
     */
    public String getCustomizedurl() {
        return customizedurl;
    }

    /**
     *
     */
    public void setCustomizedurl(String customizedurl) {
        this.customizedurl = customizedurl;
    }

    /**
     *
     * @return the value of t_trade_order_item.create_date
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
     * @return the value of t_trade_order_item.cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     *
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     *
     * @return the value of t_trade_order_item.profit
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     *
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     *
     * @return the value of t_trade_order_item.logistics
     */
    public String getLogistics() {
        return logistics;
    }

    /**
     *
     */
    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    /**
     *
     * @return the value of t_trade_order_item.completeDate
     */
    public Date getCompletedate() {
        return completedate;
    }

    /**
     *
     */
    public void setCompletedate(Date completedate) {
        this.completedate = completedate;
    }

    /**
     *
     * @return the value of t_trade_order_item.status
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
     * @return the value of t_trade_order_item.retention_reason
     */
    public String getRetentionReason() {
        return retentionReason;
    }

    /**
     *
     */
    public void setRetentionReason(String retentionReason) {
        this.retentionReason = retentionReason;
    }

	public String getExpressChannelItem() {
		return expressChannelItem;
	}

	public void setExpressChannelItem(String expressChannelItem) {
		this.expressChannelItem = expressChannelItem;
	}
}