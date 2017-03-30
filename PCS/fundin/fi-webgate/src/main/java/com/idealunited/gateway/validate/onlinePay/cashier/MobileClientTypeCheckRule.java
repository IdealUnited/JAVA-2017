
package com.idealunited.gateway.validate.onlinePay.cashier;

import com.idealunited.gateway.client.TxncoreClientService;
import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.dto.OnlinePayCashierResponseDTO;
import com.idealunited.inf.rule.MessageRule;
/**
 * 验证订单号重复
 */
public class MobileClientTypeCheckRule extends MessageRule {

	private TxncoreClientService txncoreClientService;
	
	

	public void setTxncoreClientService(TxncoreClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pay.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected boolean makeDecision(Object validateBean) throws Exception {

		OnlinePayCashierRequestDTO onlineRequestDTO = (OnlinePayCashierRequestDTO) validateBean;
		OnlinePayCashierResponseDTO onlineResponseDTO = onlineRequestDTO.getOnlinePayCashierResponseDTO();

		String userAgent = onlineRequestDTO.getUserAgent();
		
		//判断是否是微信浏览器
		if (userAgent==null || !userAgent.contains("MicroMessenger")) {
			return true;
		} else {
			onlineResponseDTO.setResultCode(getMessageId());
			onlineResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}

}
