/**
 * 
 */
package com.idealunited.gateway.service;

import com.idealunited.gateway.model.GatewayRequest;

/**
 * @author chaoyue
 *
 */
public interface GatewayRequestService {

	/**
	 * 保存网关请求
	 * 
	 * @param request
	 * @return
	 */
	Long saveGatewayRequest(GatewayRequest request);

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	GatewayRequest findGatewayRequest(Long memberCode, String orderId);
}
