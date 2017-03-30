/**
 * 
 */
package com.idealunited.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.dto.ProductStock;
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
 * 库存对接接口
 * 
 * @author chaoyue
 *
 */
public class TxncoreStockClientService {

	private final Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}

	/**
	 * 新建库存
	 * 
	 * @return
	 */
	public Map createProduct(List<ProductStock> productStocks) {

		Map paraMap = new HashMap();
		paraMap.put("orders", productStocks);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_CREATE_PRODUCT.getCode(), sysTraceNo,
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
	 * 批量更新库存
	 * 
	 * @param productSku
	 * @return
	 */
	public boolean updateProduct(List<ProductStock> productStocks,
			String operator) {

		Map paraMap = new HashMap();
		paraMap.put("orders", productStocks);
		paraMap.put("operator", operator);

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_UPDATE_PRODUCT.getCode(), sysTraceNo,
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

	public boolean delProduct(Long id) {

		Map paraMap = new HashMap();
		paraMap.put("id", id);

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_DEL_PRODUCT.getCode(), sysTraceNo,
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
	 * 审核产品
	 * 
	 * @param productSku
	 * @return
	 */
	public boolean auditProduct(List<ProductStock> productStocks,
			String operator) {

		Map paraMap = new HashMap();
		paraMap.put("orders", productStocks);
		paraMap.put("operator", operator);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_AUDIT_PRODUCT.getCode(), sysTraceNo,
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
	 * 查询库存
	 * 
	 * @param productStock
	 * @return
	 */
	public Page<ProductStock> queryProduct(ProductStock productStock, Page page) {

		Map paraMap = MapUtil.bean2map(productStock);

		if (null != page) {
			paraMap.put("page", page);
		}

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_QUERY_PRODUCT.getCode(), sysTraceNo,
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
		List<Map> listMap = (List<Map>) resultMap.get("resultList");
		Map pageResult = (Map) resultMap.get("page");
		Page pg = MapUtil.map2Object(Page.class, pageResult);
		List<ProductStock> orders = MapUtil.map2List(ProductStock.class,
				listMap);
		pg.setResult(orders);
		return pg;
	}

}
