/**
 * 
 */
package com.idealunited.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.dto.Country;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.enums.SerCode;
import com.idealunited.inf.enums.SystemCodeEnum;
import com.idealunited.inf.params.HessianInvokeParam;
import com.idealunited.inf.service.HessianInvokeService;
import com.idealunited.inf.service.SysTraceNoService;
import com.idealunited.inf.utils.HessianInvokeHelper;
import com.idealunited.util.JSonUtil;
import com.idealunited.util.MapUtil;

/**
 * 国家信息服务接口
 * 
 * @author chaoyue
 *
 */
public class TxncoreCountryClientService {

	private final Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}

	/**
	 * 查询国家列表
	 * 
	 * @param ProductSku
	 * @param page
	 * @return
	 */
	public Page<Country> query(Country country, Page<Country> page) {

		Map paraMap = MapUtil.bean2map(country);

		if (null != page) {
			paraMap.put("page", page);
		}

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_QUERY_COUNTRY.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map<String, Object> resultMap = JSonUtil.toObject(result,
				new HashMap<String, Object>().getClass());
		logger.info("resultMap:" + resultMap);

		List<Map> resultMapList = (List<Map>) resultMap.get("countrys");
		Map pageMap = (Map) resultMap.get("page");
		Page resultPage = MapUtil.map2Object(Page.class, pageMap);
		List<Country> orders = MapUtil.map2List(Country.class, resultMapList);
		resultPage.setResult(orders);
		return resultPage;
	}
}
