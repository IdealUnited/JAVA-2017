/**
 * 
 */
package com.idealunited.gateway.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.enums.SerCode;
import com.idealunited.inf.enums.SystemCodeEnum;
import com.idealunited.inf.params.HessianInvokeParam;
import com.idealunited.inf.service.HessianInvokeService;
import com.idealunited.inf.service.SysTraceNoService;
import com.idealunited.inf.utils.HessianInvokeHelper;
import com.idealunited.util.JSonUtil;

/**
 * 交易服务接口
 * 
 * @author Steven Lee
 *
 */
public class MaBrigeClientService {

	private Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService maInvokeService;
	
	public void setMaInvokeService(HessianInvokeService maInvokeService) {
		this.maInvokeService = maInvokeService;
	}	
}
