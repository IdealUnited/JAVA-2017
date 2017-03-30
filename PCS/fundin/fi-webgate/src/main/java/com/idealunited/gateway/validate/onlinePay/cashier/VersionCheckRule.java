/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.cashier;

import com.idealunited.fi.commons.RequestVersionEnum;
import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.dto.OnlinePayCashierResponseDTO;
import com.idealunited.inf.rule.MessageRule;

/**
 * 验证网关版本
 */
public class VersionCheckRule extends MessageRule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ttf.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected boolean makeDecision(Object validateBean) throws Exception {

		OnlinePayCashierRequestDTO onlineRequestDTO = (OnlinePayCashierRequestDTO) validateBean;
		OnlinePayCashierResponseDTO onlineResponseDTO = onlineRequestDTO.getOnlinePayCashierResponseDTO();

		String version = onlineRequestDTO.getVersion();

		if (RequestVersionEnum.ONLINE_1_0.getCode().equals(version)) {
			return true;
		} else {
			onlineResponseDTO.setResultCode(getMessageId());
			onlineResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}

}
