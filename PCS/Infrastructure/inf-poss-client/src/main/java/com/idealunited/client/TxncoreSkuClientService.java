/**
 * 
 */
package com.idealunited.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.dto.ProductSku;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.inf.enums.SerCode;
import com.idealunited.inf.enums.SystemCodeEnum;
import com.idealunited.inf.params.HessianInvokeParam;
import com.idealunited.inf.service.HessianInvokeService;
import com.idealunited.inf.service.SysTraceNoService;
import com.idealunited.inf.utils.HessianInvokeHelper;
import com.idealunited.util.JSonUtil;
import com.idealunited.util.MapUtil;

/**
 * 产品服务接口
 * 
 * @author chaoyue
 *
 */
public class TxncoreSkuClientService {

	private final Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}

	/**
	 * 新建sku
	 * 
	 * @return
	 */
	public Map createSku(List<ProductSku> productSkus) {

		Map paraMap = new HashMap();
		paraMap.put("orders", productSkus);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_CREATE_SKU.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result,
				new HashMap<String, Object>().getClass());
		logger.info("resultMap:" + resultMap);
		return resultMap;
	}

	/**
	 * 更新sku
	 * 
	 * @param productSku
	 * @return
	 */
	public boolean updateSku(ProductSku productSku) {

		List<ProductSku> orders = new ArrayList<ProductSku>();
		orders.add(productSku);
		Map paraMap = new HashMap();
		paraMap.put("orders", orders);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_UPDATE_SKU.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map<String, String> resultMap = JSonUtil.toObject(result,
				new HashMap<String, String>().getClass());
		logger.info("resultMap:" + resultMap);

		String responseCode = resultMap.get("responseCode");
		return ResponseCodeEnum.SUCCESS.getCode().equals(responseCode);
	}

	public boolean delSku(String skuCode) {

		Map paraMap = new HashMap();
		paraMap.put("skuCode", skuCode);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(SerCode.TXNCORE_DEL_SKU.getCode(),
				sysTraceNo, SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map<String, String> resultMap = JSonUtil.toObject(result,
				new HashMap<String, String>().getClass());
		logger.info("resultMap:" + resultMap);

		String responseCode = resultMap.get("responseCode");
		return ResponseCodeEnum.SUCCESS.getCode().equals(responseCode);
	}

	/**
	 * 审核sku
	 * 
	 * @param productSku
	 * @return
	 */
	public boolean auditSku(List<ProductSku> productSkus) {

		Map paraMap = new HashMap();
		paraMap.put("orders", productSkus);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_AUDIT_SKU.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map<String, String> resultMap = JSonUtil.toObject(result,
				new HashMap<String, String>().getClass());
		logger.info("resultMap:" + resultMap);

		String responseCode = resultMap.get("responseCode");
		return ResponseCodeEnum.SUCCESS.getCode().equals(responseCode);
	}

	/**
	 * 查询产品列表
	 * 
	 * @param ProductSku
	 * @param page
	 * @return
	 */
	public Page<ProductSku> queryProduct(ProductSku productSku,
			Page<ProductSku> page) {

		Map paraMap = MapUtil.bean2map(productSku);
		paraMap.put("page", page);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_QUERY_SKU.getCode(), sysTraceNo,
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

		List<Map> resultMapList = (List<Map>) resultMap.get("resultList");
		Map pageMap = (Map) resultMap.get("page");
		Page resultPage = MapUtil.map2Object(Page.class, pageMap);
		List<ProductSku> orders = MapUtil.map2List(ProductSku.class,
				resultMapList);
		resultPage.setResult(orders);
		return resultPage;
	}
}
