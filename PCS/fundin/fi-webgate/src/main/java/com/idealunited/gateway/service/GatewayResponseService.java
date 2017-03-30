/**
 * 
 */
package com.idealunited.gateway.service;

import com.idealunited.gateway.model.GatewayResponse;

/**
 * @author chaoyue
 *
 */
public interface GatewayResponseService {

	/**
	 * 保存网关请求
	 * 
	 * @param request
	 * @return
	 */
	Long saveGatewayResponse(GatewayResponse response);
}
