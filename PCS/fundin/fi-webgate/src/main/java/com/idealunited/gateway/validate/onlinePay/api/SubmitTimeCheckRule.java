/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.api;

import java.text.SimpleDateFormat;

import com.idealunited.gateway.dto.OnlinePayAPIRequestDTO;
import com.idealunited.gateway.dto.OnlinePayAPIResponseDTO;
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

		OnlinePayAPIRequestDTO OnlinePayAPIRequestDTO = (OnlinePayAPIRequestDTO) validateBean;
		OnlinePayAPIResponseDTO OnlinePayAPIResponseDTO = OnlinePayAPIRequestDTO
				.getOnlinePayAPIResponseDTO();

		String submitTime = OnlinePayAPIRequestDTO.getSubmitTime();

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHMMSS");
		try {
			sf.parse(submitTime);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			OnlinePayAPIResponseDTO.setResultCode(getMessageId());
			OnlinePayAPIResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}
}
