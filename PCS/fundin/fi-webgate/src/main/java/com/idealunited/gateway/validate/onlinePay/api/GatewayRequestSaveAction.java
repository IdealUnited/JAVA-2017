package com.idealunited.gateway.validate.onlinePay.api;

import java.util.Date;

import com.idealunited.gateway.commons.BusinessType;
import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.model.GatewayRequest;
import com.idealunited.gateway.service.GatewayRequestService;
import com.idealunited.inf.rule.AbstractAction;
import com.idealunited.util.MapUtil;

/**
 * 保存网关请求
 */
public class GatewayRequestSaveAction extends AbstractAction {

	private GatewayRequestService gatewayRequestService;
	
	
	public void setGatewayRequestService(GatewayRequestService gatewayRequestService) {
		this.gatewayRequestService = gatewayRequestService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ttf.ruleengine.AbstractRule#makeDecision(java.lang.Object)
	 */
	@Override
	protected void doExecute(Object validateBean) throws Exception {

//		OnlinePayCashierRequestDTO onlineRequestDTO = (OnlinePayCashierRequestDTO) validateBean;
//		OnlinePayCashierResponseDTO onlineResponseDTO = onlineRequestDTO.getOnlinePayCashierResponseDTO();
//
//		String requestIP = onlineRequestDTO.getCustomerIP();
//		Long memberCode = Long.valueOf(onlineRequestDTO.getPartnerId());
//		Long requestId = saveRequest(requestIP, onlineRequestDTO);
//		//onlineResponseDTO.setRequestId(requestId);
//
//		MemberBaseInfoBO memberBaseInfoBO = memberQueryService
//				.queryMemberBaseInfoByMemberCode(Long.valueOf(memberCode));
//		onlineResponseDTO.setSellerName(memberBaseInfoBO.getName());
	}

	private Long saveRequest(String requestIP,
			OnlinePayCashierRequestDTO crosspayRequestDTO) {
		GatewayRequest gatewayRequest = new GatewayRequest();
		gatewayRequest.setBusinessType(BusinessType.ACQUIRE.getType());
		gatewayRequest.setCreateDate(new Date());
		gatewayRequest.setFromDomain(requestIP);
		gatewayRequest.setOrderId(crosspayRequestDTO.getOrderId());
		gatewayRequest.setPartnerId(Long.valueOf(crosspayRequestDTO
				.getPartnerId()));
		gatewayRequest.setRequestContext(MapUtil
				.bean2String(crosspayRequestDTO));
		gatewayRequest.setServiceVersion(crosspayRequestDTO.getVersion());
		gatewayRequest.setSignMsg(crosspayRequestDTO.getSignMsg());
		gatewayRequest.setSignType(Integer.valueOf(crosspayRequestDTO
				.getSignType()));
		gatewayRequest.setCharset(Integer.valueOf(crosspayRequestDTO
				.getCharset()));
		gatewayRequest.setStatus(1);
		gatewayRequest.setRequestIp(requestIP);
		return gatewayRequestService.saveGatewayRequest(gatewayRequest);
	}

}
