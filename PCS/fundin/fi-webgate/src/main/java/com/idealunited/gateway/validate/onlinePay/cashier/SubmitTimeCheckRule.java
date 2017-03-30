/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.cashier;

import java.text.SimpleDateFormat;

import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.dto.OnlinePayCashierResponseDTO;
import com.idealunited.inf.rule.MessageRule;

/**
 * 验证提交时间
 */
public class SubmitTimeCheckRule extends MessageRule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ttf.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected boolean makeDecision(Object validateBean) throws Exception {

		OnlinePayCashierRequestDTO onlineRequestDTO = (OnlinePayCashierRequestDTO) validateBean;
		OnlinePayCashierResponseDTO onlineResponseDTO = onlineRequestDTO.getOnlinePayCashierResponseDTO();

		String submitTime = onlineRequestDTO.getSubmitTime();

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHMMSS");
		try {
			sf.parse(submitTime);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			onlineResponseDTO.setResultCode(getMessageId());
			onlineResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}
}
