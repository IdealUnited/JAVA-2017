/**
 * 
 */
package com.idealunited.gateway.dto;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.idealunited.util.StringUtil;

/**
 * @author Steven Lee
 *
 */
public class OnlinePayAPIRequestDTO {

	// 请求版本
	private String version;
	// 请求序列号
	private String orderId;
	// 商户显示名称
	private String displayName;
	//商品类型
	private String goodsType;
	// 商品名称
	private String goodsName;
	// 商品描述
	private String goodsCount;
	// 订单提交时间
	private String submitTime;
	// 订单失效时间
	private String failureTime;
	// 下单域名及IP
	private String customerIp;

	private String siteId;
	// 订单金额
	private String orderAmount;
	
	//订单币种
	private String orderCurrencyCode;
	// 交易类型
	private String tradeType;//01-进口跨境人民币支付 02-出口跨境人民币支付 03-进口跨境外汇支付 04-出口跨境外汇支付 05-国际汇款（跨境人民币） 06-国际汇款（跨境外汇）


	//交易附件
	private String attachment;
	//TODO：贸易类型 （2015－10－16）
	
	private String shareFlag;
	
	private String subMerchantOrderDetails;
	
	// 付款账号
	private String payerAccount;
	// 支付方式
	private String payType;
	// 资金机构代码
	private String orgCode;
	// 交易币种
	private String currencyCode;
	//结算币种
	private String settlementCurrencyCode;
	// 是否直连
	private String directFlag;
	// 借贷标识
	private String borrowingMarked;
	// 优惠券标识
	private String couponFlag;
	// 平台商Id
	private String platformId;
	// 前台回调地址
	private String returnUrl;
	// 后台回调地址
	private String noticeUrl;
	// 商户ＩＤ
	private String partnerId;

	private String tradeDetail;

	/**
	 * 收款人信息
	 */
	private String payeeName;
	private String regionEnCode;
	private String regionNumCode;
	private String payeeSwift;
	private String payeeAccount;
	private String payeeAddress;
	private String fee;
	private String freightOrg;
	private String freightWebsite;
	private String waybillNo;
	private String customerId;

	/**
	 * 保留字段
	 */
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;
	
	/****快捷支付信息****/
	//持卡人
	private String cardHolder;
	
	//手机号
	private String mobileNo;
	
	//卡号
	private String cardNo;
	
	//缩略卡号
	private String bankName;
	
	//卡类型
	private String cardType;
	
	//有效期
	private String availableDate;
	
	//CVV2
	private String cvv2;
	
	//证件类型
	private String idType;
	
	//身份证
	private String idNo;
	
	//支付类型
	private String quickPayType;
	
	// 绑卡协议号
	private String cardBindNo;
	


	// 备注
	private String remark;

	private String charset;

	private String signType;

	private String signMsg;
	
	private String userAgent;

	// 返回对象
	private OnlinePayAPIResponseDTO onlinePayAPIResponseDTO;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(String failureTime) {
		this.failureTime = failureTime;
	}



	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public String getOrderCurrencyCode() {
		return orderCurrencyCode;
	}

	public void setOrderCurrencyCode(String orderCurrencyCode) {
		this.orderCurrencyCode = orderCurrencyCode;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDirectFlag() {
		return directFlag;
	}

	public void setDirectFlag(String directFlag) {
		this.directFlag = directFlag;
	}

	public String getBorrowingMarked() {
		return borrowingMarked;
	}

	public void setBorrowingMarked(String borrowingMarked) {
		this.borrowingMarked = borrowingMarked;
	}

	public String getCouponFlag() {
		return couponFlag;
	}

	public void setCouponFlag(String couponFlag) {
		this.couponFlag = couponFlag;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSettlementCurrencyCode() {
		return settlementCurrencyCode;
	}

	public void setSettlementCurrencyCode(String settlementCurrencyCode) {
		this.settlementCurrencyCode = settlementCurrencyCode;
	}


	public String getTradeDetail() {
		return tradeDetail;
	}

	public void setTradeDetail(String tradeDetail) {
		this.tradeDetail = tradeDetail;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getRegionEnCode() {
		return regionEnCode;
	}

	public void setRegionEnCode(String regionEnCode) {
		this.regionEnCode = regionEnCode;
	}

	public String getRegionNumCode() {
		return regionNumCode;
	}

	public void setRegionNumCode(String regionNumCode) {
		this.regionNumCode = regionNumCode;
	}


	public String getPayeeSwift() {
		return payeeSwift;
	}

	public void setPayeeSwift(String payeeSwift) {
		this.payeeSwift = payeeSwift;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayeeAddress() {
		return payeeAddress;
	}

	public void setPayeeAddress(String payeeAddress) {
		this.payeeAddress = payeeAddress;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFreightOrg() {
		return freightOrg;
	}

	public void setFreightOrg(String freightOrg) {
		this.freightOrg = freightOrg;
	}
	
	public String getFreightWebsite() {
		return freightWebsite;
	}

	public void setFreightWebsite(String freightWebsite) {
		this.freightWebsite = freightWebsite;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}
	
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	


	public OnlinePayAPIResponseDTO getOnlinePayAPIResponseDTO() {
		return onlinePayAPIResponseDTO;
	}

	public void setOnlinePayAPIResponseDTO(OnlinePayAPIResponseDTO onlinePayAPIResponseDTO) {
		this.onlinePayAPIResponseDTO = onlinePayAPIResponseDTO;
	}

	public String getShareFlag() {
		return shareFlag;
	}

	public void setShareFlag(String shareFlag) {
		this.shareFlag = shareFlag;
	}

	public String getSubMerchantOrderDetails() {
		return subMerchantOrderDetails;
	}

	public void setSubMerchantOrderDetails(String subMerchantOrderDetails) {
		this.subMerchantOrderDetails = subMerchantOrderDetails;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	
	public String getCardHolder() {
		return cardHolder;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public String getCardType() {
		return cardType;
	}

	public String getAvailableDate() {
		return availableDate;
	}

	public String getCvv2() {
		return cvv2;
	}

	public String getIdType() {
		return idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public String getQuickPayType() {
		return quickPayType;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public void setQuickPayType(String quickPayType) {
		this.quickPayType = quickPayType;
	}

	public String getCardBindNo() {
		return cardBindNo;
	}

	public void setCardBindNo(String cardBindNo) {
		this.cardBindNo = cardBindNo;
	}

	public String generateSign() {
		StringBuilder sb = new StringBuilder();
		try {
			BeanWrapper bean = new BeanWrapperImpl(this);
			PropertyDescriptor[] properties = bean.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : properties) {
				String key = propertyDescriptor.getDisplayName();
				Object value = bean.getPropertyValue(key);
				if (!"class".equals(key) && !"signMsg".equals(key)
						&& !"userAgent".equals(key)
						&& !"gatewayResponseDTO".equals(key)) {
					if (!StringUtil.isEmpty(value+"")) {
						if(sb.length()<1){
							sb.append(key);
							sb.append("=");
							sb.append(value);
						}else{
							sb.append("&");
							sb.append(key);
							sb.append("=");
							sb.append(value);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		BeanWrapper beanWrapper = new BeanWrapperImpl(this);
		PropertyDescriptor[] propertyDescriptors = beanWrapper
				.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String propertyName = property.getDisplayName();
			Object value = beanWrapper.getPropertyValue(propertyName);
			if ("class".equals(propertyName)) {
				continue;
			}
			sb.append("&");
			sb.append(propertyName);
			sb.append("=");
			sb.append(value);
		}
		return sb.toString();
	}
}
