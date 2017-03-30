package com.idealunited.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TradeOrder {
    /**
     * 交易流水号
     */
    private String tradeOrderNo;

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
     * 创建时间
     */
    private Date createDate;

    /**
     * 完成时间
     */
    private Date completeDate;

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

    /**
     * 平台订单状态
     */
    private String platformStatus;

    /**
     *
     * @return the value of t_trade_order.TRADE_ORDER_NO
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
     * @return the value of t_trade_order.ORDER_ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     *
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return the value of t_trade_order.PLATFORM
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
     * @return the value of t_trade_order.ORDER_AMOUNT
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     *
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     *
     * @return the value of t_trade_order.COUNTRY
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return the value of t_trade_order.CREATE_DATE
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
     * @return the value of t_trade_order.COMPLETE_DATE
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    /**
     *
     */
    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    /**
     *
     * @return the value of t_trade_order.STATUS
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
     * @return the value of t_trade_order.purchase_Date
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     *
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     *
     * @return the value of t_trade_order.seller_OrderId
     */
    public String getSellerOrderid() {
        return sellerOrderid;
    }

    /**
     *
     */
    public void setSellerOrderid(String sellerOrderid) {
        this.sellerOrderid = sellerOrderid;
    }

    /**
     *
     * @return the value of t_trade_order.last_Update_Date
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     *
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     *
     * @return the value of t_trade_order.fulfillment_Channel
     */
    public String getFulfillmentChannel() {
        return fulfillmentChannel;
    }

    /**
     *
     */
    public void setFulfillmentChannel(String fulfillmentChannel) {
        this.fulfillmentChannel = fulfillmentChannel;
    }

    /**
     *
     * @return the value of t_trade_order.sales_Channel
     */
    public String getSalesChannel() {
        return salesChannel;
    }

    /**
     *
     */
    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    /**
     *
     * @return the value of t_trade_order.order_Channel
     */
    public String getOrderChannel() {
        return orderChannel;
    }

    /**
     *
     */
    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     *
     * @return the value of t_trade_order.shipService_Level
     */
    public String getShipserviceLevel() {
        return shipserviceLevel;
    }

    /**
     *
     */
    public void setShipserviceLevel(String shipserviceLevel) {
        this.shipserviceLevel = shipserviceLevel;
    }

    /**
     *
     * @return the value of t_trade_order.currency_Code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     *
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     *
     * @return the value of t_trade_order.number_Of_Items_Shipped
     */
    public Integer getNumberOfItemsShipped() {
        return numberOfItemsShipped;
    }

    /**
     *
     */
    public void setNumberOfItemsShipped(Integer numberOfItemsShipped) {
        this.numberOfItemsShipped = numberOfItemsShipped;
    }

    /**
     *
     * @return the value of t_trade_order.number_Of_Items_Unshipped
     */
    public Integer getNumberOfItemsUnshipped() {
        return numberOfItemsUnshipped;
    }

    /**
     *
     */
    public void setNumberOfItemsUnshipped(Integer numberOfItemsUnshipped) {
        this.numberOfItemsUnshipped = numberOfItemsUnshipped;
    }

    /**
     *
     * @return the value of t_trade_order.payment_Method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     *
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     *
     * @return the value of t_trade_order.name
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the value of t_trade_order.addressLine1
     */
    public String getAddressline1() {
        return addressline1;
    }

    /**
     *
     */
    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    /**
     *
     * @return the value of t_trade_order.addressLine2
     */
    public String getAddressline2() {
        return addressline2;
    }

    /**
     *
     */
    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    /**
     *
     * @return the value of t_trade_order.addressLine3
     */
    public String getAddressline3() {
        return addressline3;
    }

    /**
     *
     */
    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    /**
     *
     * @return the value of t_trade_order.city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return the value of t_trade_order.County
     */
    public String getCounty() {
        return county;
    }

    /**
     *
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     *
     * @return the value of t_trade_order.district
     */
    public String getDistrict() {
        return district;
    }

    /**
     *
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     *
     * @return the value of t_trade_order.stateOrRegion
     */
    public String getStateorregion() {
        return stateorregion;
    }

    /**
     *
     */
    public void setStateorregion(String stateorregion) {
        this.stateorregion = stateorregion;
    }

    /**
     *
     * @return the value of t_trade_order.postalCode
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     *
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     *
     * @return the value of t_trade_order.countryCode
     */
    public String getCountrycode() {
        return countrycode;
    }

    /**
     *
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    /**
     *
     * @return the value of t_trade_order.phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return the value of t_trade_order.marketplaceId
     */
    public String getMarketplaceid() {
        return marketplaceid;
    }

    /**
     *
     */
    public void setMarketplaceid(String marketplaceid) {
        this.marketplaceid = marketplaceid;
    }

    /**
     *
     * @return the value of t_trade_order.buyerEmail
     */
    public String getBuyeremail() {
        return buyeremail;
    }

    /**
     *
     */
    public void setBuyeremail(String buyeremail) {
        this.buyeremail = buyeremail;
    }

    /**
     *
     * @return the value of t_trade_order.buyerName
     */
    public String getBuyername() {
        return buyername;
    }

    /**
     *
     */
    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    /**
     *
     * @return the value of t_trade_order.shipmentServiceLevelCategory
     */
    public String getShipmentservicelevelcategory() {
        return shipmentservicelevelcategory;
    }

    /**
     *
     */
    public void setShipmentservicelevelcategory(String shipmentservicelevelcategory) {
        this.shipmentservicelevelcategory = shipmentservicelevelcategory;
    }

    /**
     *
     * @return the value of t_trade_order.shippedByAmazonTFM
     */
    public Integer getShippedbyamazontfm() {
        return shippedbyamazontfm;
    }

    /**
     *
     */
    public void setShippedbyamazontfm(Integer shippedbyamazontfm) {
        this.shippedbyamazontfm = shippedbyamazontfm;
    }

    /**
     *
     * @return the value of t_trade_order.tfmShipmentStatus
     */
    public String getTfmshipmentstatus() {
        return tfmshipmentstatus;
    }

    /**
     *
     */
    public void setTfmshipmentstatus(String tfmshipmentstatus) {
        this.tfmshipmentstatus = tfmshipmentstatus;
    }

    /**
     *
     * @return the value of t_trade_order.cbaDisplayableShippingLabel
     */
    public String getCbadisplayableshippinglabel() {
        return cbadisplayableshippinglabel;
    }

    /**
     *
     */
    public void setCbadisplayableshippinglabel(String cbadisplayableshippinglabel) {
        this.cbadisplayableshippinglabel = cbadisplayableshippinglabel;
    }

    /**
     *
     * @return the value of t_trade_order.orderType
     */
    public String getOrdertype() {
        return ordertype;
    }

    /**
     *
     */
    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    /**
     *
     * @return the value of t_trade_order.earliestShipDate
     */
    public Date getEarliestshipdate() {
        return earliestshipdate;
    }

    /**
     *
     */
    public void setEarliestshipdate(Date earliestshipdate) {
        this.earliestshipdate = earliestshipdate;
    }

    /**
     *
     * @return the value of t_trade_order.latestShipDate
     */
    public Date getLatestshipdate() {
        return latestshipdate;
    }

    /**
     *
     */
    public void setLatestshipdate(Date latestshipdate) {
        this.latestshipdate = latestshipdate;
    }

    /**
     *
     * @return the value of t_trade_order.earliestDeliveryDate
     */
    public Date getEarliestdeliverydate() {
        return earliestdeliverydate;
    }

    /**
     *
     */
    public void setEarliestdeliverydate(Date earliestdeliverydate) {
        this.earliestdeliverydate = earliestdeliverydate;
    }

    /**
     *
     * @return the value of t_trade_order.latestDeliveryDate
     */
    public Date getLatestdeliverydate() {
        return latestdeliverydate;
    }

    /**
     *
     */
    public void setLatestdeliverydate(Date latestdeliverydate) {
        this.latestdeliverydate = latestdeliverydate;
    }

    /**
     *
     * @return the value of t_trade_order.isBusinessOrder
     */
    public Integer getIsbusinessorder() {
        return isbusinessorder;
    }

    /**
     *
     */
    public void setIsbusinessorder(Integer isbusinessorder) {
        this.isbusinessorder = isbusinessorder;
    }

    /**
     *
     * @return the value of t_trade_order.purchaseOrderNumber
     */
    public String getPurchaseordernumber() {
        return purchaseordernumber;
    }

    /**
     *
     */
    public void setPurchaseordernumber(String purchaseordernumber) {
        this.purchaseordernumber = purchaseordernumber;
    }

    /**
     *
     * @return the value of t_trade_order.isPrime
     */
    public Integer getIsprime() {
        return isprime;
    }

    /**
     *
     */
    public void setIsprime(Integer isprime) {
        this.isprime = isprime;
    }

    /**
     *
     * @return the value of t_trade_order.isPremiumOrder
     */
    public Integer getIspremiumorder() {
        return ispremiumorder;
    }

    /**
     *
     */
    public void setIspremiumorder(Integer ispremiumorder) {
        this.ispremiumorder = ispremiumorder;
    }

    /**
     *
     * @return the value of t_trade_order.PLATFORM_STATUS
     */
    public String getPlatformStatus() {
        return platformStatus;
    }

    /**
     *
     */
    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus;
    }
}