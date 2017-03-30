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
public class TxncoreClientService {

	private Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}
	
	public Map partnerConfigQuery(String partnerId, String code) {

		Map<String, String> paraMap = new HashMap();
		paraMap.put("partnerId", partnerId);
		paraMap.put("code", code);

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.WEBGATE.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_PARTNERCONFIG_QUERY.getCode(), sysTraceNo,
				SystemCodeEnum.WEBGATE.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result, new HashMap().getClass());

		String responseCode = (String) resultMap.get("responseCode");
		String responseDesc = (String) resultMap.get("responseDesc");
		Map<String, String> returnMap = (Map) resultMap.get("result");

		if (logger.isInfoEnabled()) {
			logger.info("responseCode:" + responseCode);
			logger.info("responseDesc:" + responseDesc);
			logger.info("returnMap:" + returnMap);
		}

		return returnMap;
	}	
	
}
