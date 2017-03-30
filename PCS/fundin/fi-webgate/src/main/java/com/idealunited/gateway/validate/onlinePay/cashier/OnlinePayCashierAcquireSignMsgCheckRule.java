
package com.idealunited.gateway.validate.onlinePay.cashier;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.fi.service.TradeDataSingnatureService;
import com.idealunited.gateway.client.TxncoreClientService;
import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.dto.OnlinePayCashierResponseDTO;
import com.idealunited.inf.rule.MessageRule;

/**
 * 验证网关签名
 */
public class OnlinePayCashierAcquireSignMsgCheckRule extends MessageRule {

	private final Log logger = LogFactory.getLog(getClass());
	private TxncoreClientService txncoreClientService;
	private TradeDataSingnatureService tradeDataSingnatureService;

	public void setTradeDataSingnatureService(
			TradeDataSingnatureService tradeDataSingnatureService) {
		this.tradeDataSingnatureService = tradeDataSingnatureService;
	}

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

		String orderId = onlineRequestDTO.getOrderId();
		String partnerId =StringUtils.isNotBlank(onlineRequestDTO.getPlatformId())?onlineRequestDTO.getPlatformId() :onlineRequestDTO.getPartnerId();
		String signType = onlineRequestDTO.getSignType();
		String charsetType = onlineRequestDTO.getCharset();
		String signMsg = onlineRequestDTO.getSignMsg();

		String src = onlineRequestDTO.generateSign();

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
				onlineResponseDTO.setResultCode(getMessageId());
				onlineResponseDTO.setResultMsg(getMessage());
			}
			return isvalid;
		} catch (Exception e) {
			logger.error("sign check error:", e);
			onlineResponseDTO.setResultCode(getMessageId());
			onlineResponseDTO.setResultMsg(getMessage());
			return false;
		}
	}

}
