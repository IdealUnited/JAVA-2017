/**
 *  File: 
 *  Description:
 *  Copyright 2006-2011 ttf Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.gateway.validate.onlinePay.api;

import org.apache.commons.lang.StringUtils;

import com.idealunited.fi.service.TradeDataSingnatureService;
import com.idealunited.gateway.dto.OnlinePayAPIRequestDTO;
import com.idealunited.gateway.dto.OnlinePayAPIResponseDTO;
import com.idealunited.inf.rule.AbstractAction;

/**
 * 验证订单号
 */
public class ExceptionAction extends AbstractAction {

	private TradeDataSingnatureService tradeDataSingnatureService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ttf.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected void doExecute(Object validateBean) throws Exception {

		OnlinePayAPIRequestDTO OnlinePayAPIRequestDTO = (OnlinePayAPIRequestDTO) validateBean;

		OnlinePayAPIResponseDTO OnlinePayAPIResponseDTO = OnlinePayAPIRequestDTO
				.getOnlinePayAPIResponseDTO();

		String signType = OnlinePayAPIRequestDTO.getSignType();
		String charsetType = OnlinePayAPIRequestDTO.getCharset();
		String partnerId=StringUtils.isNotBlank(OnlinePayAPIRequestDTO.getPlatformId())?
				OnlinePayAPIRequestDTO.getPlatformId():OnlinePayAPIRequestDTO.getPartnerId();

		String signData = OnlinePayAPIResponseDTO.generateSign();
		String signMsg = tradeDataSingnatureService.genSignMsgBySignType(
				signData, signType, charsetType, partnerId);
		OnlinePayAPIResponseDTO.setSignMsg(signMsg);
	}

	public void setTradeDataSingnatureService(
			TradeDataSingnatureService tradeDataSingnatureService) {
		this.tradeDataSingnatureService = tradeDataSingnatureService;
	}
}
