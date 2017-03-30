/**
 * 
 */
package com.idealunited.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.dto.ImportOrderDto;
import com.idealunited.dto.TradeOrder;
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
 * 核心服务接口
 * 
 * @author chaoyue
 *
 */
public class TxncoreOrderClientService {

	private final Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}

	/**
	 * 查询总订单
	 * 
	 * @param productSku
	 * @return
	 */
	public Page<TradeOrder> queryOrder(TradeOrder tradeOrder, Page page) {

		Map paraMap = MapUtil.bean2map(tradeOrder);
		paraMap.put("page", page);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_QUERY_ORDER.getCode(), sysTraceNo,
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
		List<TradeOrder> orders = MapUtil.map2List(TradeOrder.class,
				resultMapList);
		resultPage.setResult(orders);
		return resultPage;
	}

	public Page<Map> queryOrderInfo(Map queryMap, Page page) {

		queryMap.put("page", page);
		String reqMsg = JSonUtil.toJSonString(queryMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_QUERY_ORDER_INFO.getCode(), sysTraceNo,
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
		resultPage.setResult(resultMapList);
		return resultPage;
	}

	public Page<Map> queryOrderItemInfo(Map queryMap, Page page) {

		queryMap.put("page", page);
		String reqMsg = JSonUtil.toJSonString(queryMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_QUERY_ORDER_ITEM_INFO.getCode(), sysTraceNo,
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
		resultPage.setResult(resultMapList);
		return resultPage;
	}

	public Map createExpressOrder(List<String> orderItemIdList,
			Integer expressChannelId, Integer expressChannelItemId,String channelOrderItemId,String status) {
		logger.info("-----手工发送物流开始-----");
		Map paraMap = new HashMap();
		paraMap.put("orderItemIdList", orderItemIdList);
		paraMap.put("expressChannelId", expressChannelId);
		paraMap.put("expressChannelItemId", expressChannelItemId);
		paraMap.put("channelOrderItemId", channelOrderItemId);
		paraMap.put("status", status);

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_LOGISTICS_MANUAL_PROCESS.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result, new HashMap().getClass());

		logger.info("-----手工发送物流结束-----");
		return resultMap;
	}
	public Map createExpressOrderList(List<Map> orderItemListMap) {
		logger.info("-----手工发送物流开始-----");
		Map paraMap = new HashMap();
		paraMap.put("isbatchOrderProcess", "Y");
		paraMap.put("orderItemListMap", orderItemListMap);
		
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_LOGISTICS_MANUAL_PROCESS.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result, new HashMap().getClass());

		logger.info("-----手工发送物流结束-----");
		return resultMap;
	}

	public Map scanAndConfirmExpress(Map paraMap) {
		logger.info("-----确认发货开始-----");

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_SCAN_CONFIRM_EXPRESS.getCode(), sysTraceNo,
				SystemCodeEnum.POSS.getCode(),
				SystemCodeEnum.TXNCORE.getCode(),
				SystemCodeEnum.TXNCORE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result, new HashMap().getClass());

		logger.info("-----确认发货结束-----");
		return resultMap;
	}

	/**
	 * 手工创建订单
	 * 
	 * @param orderMap
	 * @return
	 */
	public Map createManualOrder(Map orderMap) {

		String reqMsg = JSonUtil.toJSonString(orderMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_CREATE_ORDER_BY_MANUAL.getCode(), sysTraceNo,
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

		return resultMap;
	}

	/**
	 * 批量导入订单
	 * 
	 * @param orderMap
	 * @return
	 */
	public Map importOrder(List<ImportOrderDto> orderMap) {

		Map paraMap = new HashMap();
		paraMap.put("list", orderMap);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_IMPORT_ORDER_BY_MANUAL.getCode(), sysTraceNo,
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

		return resultMap;
	}
}
