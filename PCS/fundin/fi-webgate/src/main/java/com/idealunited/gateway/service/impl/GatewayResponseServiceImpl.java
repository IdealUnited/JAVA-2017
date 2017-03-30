/**
 * 
 */
package com.idealunited.gateway.service.impl;

import com.idealunited.gateway.dao.GatewayResponseDAO;
import com.idealunited.gateway.model.GatewayResponse;
import com.idealunited.gateway.service.GatewayResponseService;

/**
 * @author chaoyue
 *
 */
public class GatewayResponseServiceImpl implements GatewayResponseService {

	private GatewayResponseDAO gatewayResponseDAO;

	public void setGatewayResponseDAO(GatewayResponseDAO gatewayResponseDAO) {
		this.gatewayResponseDAO = gatewayResponseDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.gateway.service.GatewayRequestService#saveGatewayRequest(com.
	 * pay.gateway.model.GatewayRequest)
	 */
	@Override
	public Long saveGatewayResponse(GatewayResponse request) {
		return (Long) gatewayResponseDAO.create(request);
	}

}
