/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.cashier;

import java.util.Map;

import com.idealunited.gateway.client.TxncoreClientService;
import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.dto.OnlinePayCashierResponseDTO;
import com.idealunited.inf.rule.MessageRule;
import com.idealunited.util.StringUtil;

/**
 * 验证网关商户
 */
public class MerchantConfigureCheckRule extends MessageRule {

	private TxncoreClientService txncoreClientService;

	public void setTxncoreClientService(
			TxncoreClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ttf.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected boolean makeDecision(Object validateBean) throws Exception {

		OnlinePayCashierRequestDTO onlineRequestDTO = (OnlinePayCashierRequestDTO) validateBean;
		OnlinePayCashierResponseDTO onlineResponseDTO = onlineRequestDTO.getOnlinePayCashierResponseDTO();

		try {
			String partnerId = onlineRequestDTO.getPartnerId();
			Map<String, String> resultMap = txncoreClientService
					.partnerConfigQuery(partnerId, "code1");

			String merchantKey = resultMap.get("value");

			if (!StringUtil.isEmpty(merchantKey)) {
				return true;
			} else {
				onlineResponseDTO.setResultCode(getMessageId());
				onlineResponseDTO.setResultMsg(getMessage());
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			onlineResponseDTO.setResultCode(getMessageId());
			onlineResponseDTO.setResultMsg(getMessage());
			return false;
		}

	}

}
