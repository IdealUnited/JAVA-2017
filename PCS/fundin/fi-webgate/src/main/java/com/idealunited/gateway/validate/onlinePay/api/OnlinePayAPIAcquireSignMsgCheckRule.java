/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.api;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.fi.service.TradeDataSingnatureService;
import com.idealunited.gateway.client.TxncoreClientService;
import com.idealunited.gateway.dto.OnlinePayAPIRequestDTO;
import com.idealunited.gateway.dto.OnlinePayAPIResponseDTO;
import com.idealunited.inf.rule.MessageRule;

/**
 * 验证网关签名
 */
public class OnlinePayAPIAcquireSignMsgCheckRule extends MessageRule {

	private final Log logger = LogFactory.getLog(getClass());
	private TxncoreClientService txncoreClientService;
	private TradeDataSingnatureService tradeDataSingnatureService;

	public void setTxncoreClientService(
			TxncoreClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}

	public void setTradeDataSingnatureService(
			TradeDataSingnatureService tradeDataSingnatureService) {
		this.tradeDataSingnatureService = tradeDataSingnatureService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ttf.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected boolean makeDecision(Object validateBean) throws Exception {

		OnlinePayAPIRequestDTO OnlinePayAPIRequestDTO = (OnlinePayAPIRequestDTO) validateBean;
		OnlinePayAPIResponseDTO OnlinePayAPIResponseDTO = OnlinePayAPIRequestDTO
				.getOnlinePayAPIResponseDTO();

		String orderId = OnlinePayAPIRequestDTO.getOrderId();
		String partnerId =StringUtils.isNotBlank(OnlinePayAPIRequestDTO.getPlatformId())?OnlinePayAPIRequestDTO.getPlatformId() :OnlinePayAPIRequestDTO.getPartnerId();
		String signType = OnlinePayAPIRequestDTO.getSignType();
		String charsetType = OnlinePayAPIRequestDTO.getCharset();
		String signMsg = OnlinePayAPIRequestDTO.getSignMsg();

		String src = OnlinePayAPIRequestDTO.generateSign();

		if (logger.isInfoEnabled()) {
			logger.info("partnerId:" + partnerId + ",orderId:" + orderId
					+ "signMsg:" + signMsg);
			logger.info("system signData:" + src);
		}

		try {

			Map<String, String> resultMap = txncoreClientService
					.partnerConfigQuery(partnerId, "code1");

			String merchantKey = resultMap.get("value");

			boolean isvalid = tradeDataSingnatureService.verifyBySignType(src,
					signMsg, signType, merchantKey, charsetType);

			if (!isvalid) {
				OnlinePayAPIResponseDTO.setResultCode(getMessageId());
				OnlinePayAPIResponseDTO.setResultMsg(getMessage());
			}
			return isvalid;
		} catch (Exception e) {
			logger.error("sign check error:", e);
			OnlinePayAPIResponseDTO.setResultCode(getMessageId());
			OnlinePayAPIResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}

}
