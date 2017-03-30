/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.api;

import com.idealunited.fi.commons.SignTypeEnum;
import com.idealunited.gateway.dto.OnlinePayAPIRequestDTO;
import com.idealunited.gateway.dto.OnlinePayAPIResponseDTO;
import com.idealunited.inf.rule.MessageRule;

/**
 * 验证网关版本
 */
public class SignTypeCheckRule extends MessageRule {

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

		String signType = OnlinePayAPIRequestDTO.getSignType();

		if (SignTypeEnum.isExists(signType)) {
			return true;
		} else {
			OnlinePayAPIResponseDTO.setResultCode(getMessageId());
			OnlinePayAPIResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}
}
