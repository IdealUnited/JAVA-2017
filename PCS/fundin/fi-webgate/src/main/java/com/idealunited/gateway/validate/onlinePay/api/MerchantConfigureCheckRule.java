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

import com.idealunited.gateway.client.TxncoreClientService;
import com.idealunited.gateway.dto.OnlinePayAPIRequestDTO;
import com.idealunited.gateway.dto.OnlinePayAPIResponseDTO;
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

		OnlinePayAPIRequestDTO OnlinePayAPIRequestDTO = (OnlinePayAPIRequestDTO) validateBean;
		OnlinePayAPIResponseDTO OnlinePayAPIResponseDTO = OnlinePayAPIRequestDTO
				.getOnlinePayAPIResponseDTO();

		try {
			String partnerId = OnlinePayAPIRequestDTO.getPartnerId();
			Map<String, String> resultMap = txncoreClientService
					.partnerConfigQuery(partnerId, "code1");

			if (null != resultMap
					&& !StringUtil.isEmpty(resultMap.get("value"))) {
				return true;
			} else {
				OnlinePayAPIResponseDTO.setResultCode(getMessageId());
				OnlinePayAPIResponseDTO.setResultMsg(getMessage());
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			OnlinePayAPIResponseDTO.setResultCode(getMessageId());
			OnlinePayAPIResponseDTO.setResultMsg(getMessage());
			return false;
		}

	}

}
