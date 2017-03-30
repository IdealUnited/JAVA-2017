/**
 *  File: FileParserMode.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-11-10      Jason_wang      Changes
 *  
 *
 */
package com.idealunited.file.parser.dto;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.idealunited.util.StringUtil;

/**
 * @author Jason_wang
 *
 */
public class GatewayReconciliationParserMode {

	private static final long serialVersionUID = -3136695356485959065L;

	// 网关渠道订单号
	private String channelOrderNo;

	// 参考号
	private String refeNumber;
	// 交易日期
	private String dealDate;

	// 清算日期
	private String clearDate;

	// 结账日期
	private String settleDate;
	// 结算币种
	private String settlementCurrency;
	// 交易金额
	private String dealAmount;
	// 结算金额
	private String settAmount;
	// 手续费
	private String transFee;
	// 状态，1-成功，0-失败
	private String status;
	// 结算汇率
	private String settlementRate;

	public String getChannelOrderNo() {
		if (!StringUtil.isEmpty(channelOrderNo)) {
			return channelOrderNo.trim();
		} else {
			return channelOrderNo;
		}
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public String getRefeNumber() {
		if (!StringUtil.isEmpty(refeNumber)) {
			return refeNumber.trim();
		} else {
			return refeNumber;
		}
	}

	public void setRefeNumber(String refeNumber) {
		this.refeNumber = refeNumber;
	}

	public String getClearDate() {
		if (!StringUtil.isEmpty(clearDate)) {
			return clearDate.trim();
		} else {
			return clearDate;
		}
	}

	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	public String getSettleDate() {
		if (!StringUtil.isEmpty(settleDate)) {
			return settleDate.trim();
		} else {
			return settleDate;
		}
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getSettlementCurrency() {
		return settlementCurrency;
	}

	public void setSettlementCurrency(String settlementCurrency) {
		this.settlementCurrency = settlementCurrency;
	}

	public String getSettAmount() {
		if (!StringUtil.isEmpty(settAmount)) {
			return settAmount.trim();
		} else {
			return settAmount;
		}
	}

	public void setSettAmount(String settAmount) {
		this.settAmount = settAmount;
	}

	public String getTransFee() {
		if (!StringUtil.isEmpty(transFee)) {
			return transFee.trim();
		} else {
			return transFee;
		}
	}

	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettlementRate() {
		if (!StringUtil.isEmpty(settlementRate)) {
			return settlementRate.trim();
		} else {
			return settlementRate;
		}
	}

	public void setSettlementRate(String settlementRate) {
		this.settlementRate = settlementRate;
	}

	public String getDealAmount() {
		if (!StringUtil.isEmpty(dealAmount)) {
			return dealAmount.trim();
		} else {
			return dealAmount;
		}
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealDate() {
		if (!StringUtil.isEmpty(dealDate)) {
			return dealDate.trim();
		} else {
			return dealDate;
		}
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		try {
			BeanWrapper bean = new BeanWrapperImpl(this);
			int i = 0;
			PropertyDescriptor[] properties = bean.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : properties) {
				i++;
				String key = propertyDescriptor.getDisplayName();
				Object value = bean.getPropertyValue(key);
				if (!"class".equals(key)) {

					sb.append(key);
					sb.append("=");
					if (null != value)
						sb.append(value);
					if (i < properties.length) {
						sb.append("&");
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
