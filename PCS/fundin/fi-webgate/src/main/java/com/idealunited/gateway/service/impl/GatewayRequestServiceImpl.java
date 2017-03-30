/**
 * 
 */
package com.idealunited.gateway.service.impl;

import com.idealunited.gateway.dao.GatewayRequestDAO;
import com.idealunited.gateway.model.GatewayRequest;
import com.idealunited.gateway.service.GatewayRequestService;

/**
 * @author chaoyue
 *
 */
public class GatewayRequestServiceImpl implements GatewayRequestService {

	private GatewayRequestDAO gatewayRequestDAO;

	public void setGatewayRequestDAO(GatewayRequestDAO gatewayRequestDAO) {
		this.gatewayRequestDAO = gatewayRequestDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.gateway.service.GatewayRequestService#saveGatewayRequest(com.
	 * pay.gateway.model.GatewayRequest)
	 */
	@Override
	public Long saveGatewayRequest(GatewayRequest request) {
		return (Long) gatewayRequestDAO.create(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.gateway.service.GatewayRequestService#findGatewayRequest(java
	 * .lang.String)
	 */
	@Override
	public GatewayRequest findGatewayRequest(Long memberCode, String orderId) {

		return gatewayRequestDAO.queryGatewayRequest(memberCode, orderId);
	}

}
