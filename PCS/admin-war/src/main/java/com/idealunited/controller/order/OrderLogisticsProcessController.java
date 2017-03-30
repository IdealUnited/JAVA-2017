/**
 *  File: OrderManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreChannelClientService;
import com.idealunited.client.TxncoreOrderClientService;
import com.idealunited.dto.ExpressChannel;
import com.idealunited.dto.ExpressChannelItem;
import com.idealunited.dto.TradeOrder;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.util.DateUtil;
import com.idealunited.util.MapUtil;
import com.idealunited.util.SpringControllerUtils;
import com.idealunited.util.StringUtil;

/**
 * 订单发货处理
 */
public class OrderLogisticsProcessController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private String nextStepView;

	private String scanView;
	private String scanList;

	private String leftView;
	private String leftList;

	private TxncoreOrderClientService txncoreOrderClientService;
	private TxncoreChannelClientService txncoreChannelClientService;
	
	private static final String KEY_BEGIN_TIME = "beginTime";
	private static final String KEY_END_TIME = "endTime";

	/**
	 * 产品库存管理初始页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String startTime = DateUtil.skipDateTimeStr( new Date( ), 0 );
		String endTime = DateUtil.skipDateTimeStr( new Date( ), +1 );

		return new ModelAndView(initView).addObject( KEY_BEGIN_TIME, startTime ).addObject(
				KEY_END_TIME, endTime );
	}

	/**
	 * 查询订单
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, OrderCriteria orderCriteria)
			throws Exception {

		Page<TradeOrder> page = PageUtils.getPage(request); // 分页
		Map queryMap = MapUtil.bean2map(orderCriteria);
		Page<Map> resultPage = txncoreOrderClientService.queryOrderItemInfo(
				queryMap, page);
		page.setTotalCount(page.getTotalRecord());
		page.setPageNo(page.getTargetPage());
		Map resultMap = new HashMap();

		ExpressChannel tempParam = new ExpressChannel();
		List<ExpressChannel> expressChannelList = txncoreChannelClientService
				.queryExpressChannel(tempParam);
		resultMap.put("expressChannelList", expressChannelList);

		List<Relation> relationList = new ArrayList<Relation>();

		for (int i = 0; i < expressChannelList.size(); i++) {
			ExpressChannel expressChannel = (ExpressChannel) expressChannelList
					.get(i);
			ExpressChannelItem paramObject = new ExpressChannelItem();
			paramObject.setChannelId(expressChannel.getId());
			List<ExpressChannelItem> expressChannelItemList = txncoreChannelClientService
					.queryExpressChannelItem(paramObject).getResult();

			for (int j = 0; j < expressChannelItemList.size(); j++) {
				ExpressChannelItem expressChannelItem = (ExpressChannelItem) expressChannelItemList
						.get(j);
				Relation relation = new Relation();
				relation.setFatherCode(expressChannel.getId().toString());
				relation.setCode(expressChannelItem.getId().toString());
				relation.setName(expressChannelItem.getName());
				relationList.add(relation);
			}
		}
		resultMap.put("relationList", relationList);
		resultMap.put("page", resultPage);
		return new ModelAndView(listView, resultMap);
	}

	public ModelAndView nextStep(HttpServletRequest request,
			HttpServletResponse response, OrderCriteria orderCriteria)
			throws Exception {
		String sequenceIdList = request.getParameter("sequenceList");
		String channelOrderItemId = StringUtil.null2String(request
				.getParameter("channelOrderItemId"));
		String status = StringUtil.null2String(request.getParameter("status"));
		ModelAndView view = new ModelAndView(nextStepView);
		ExpressChannel tempParam = new ExpressChannel();
		List<ExpressChannel> expressChannelList = txncoreChannelClientService
				.queryExpressChannel(tempParam);

		List<Relation> relationList = new ArrayList<Relation>();

		for (int i = 0; i < expressChannelList.size(); i++) {
			ExpressChannel expressChannel = (ExpressChannel) expressChannelList
					.get(i);
			ExpressChannelItem paramObject = new ExpressChannelItem();
			paramObject.setChannelId(expressChannel.getId());
			List<ExpressChannelItem> ExpressChannelItemList = txncoreChannelClientService
					.queryExpressChannelItem(paramObject).getResult();

			for (int j = 0; j < ExpressChannelItemList.size(); j++) {
				ExpressChannelItem expressChannelItem = (ExpressChannelItem) ExpressChannelItemList
						.get(j);
				Relation relation = new Relation();
				relation.setFatherCode(expressChannel.getId().toString());
				relation.setCode(expressChannelItem.getId().toString());
				relation.setName(expressChannelItem.getName());
				relationList.add(relation);
			}
		}

		view.addObject("sequenceIdList", sequenceIdList)
				.addObject("expressChannelList", expressChannelList)
				.addObject("relationList", relationList)
				.addObject("channelOrderItemId", channelOrderItemId)
				.addObject("status", status);
		return view;
	}

	public ModelAndView createExpressOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		try {
			String sequenceIdList = request.getParameter("sequenceList");
			List<String> orderItemIdList = new ArrayList();
			String[] sequenceArrayList = StringUtils.split(sequenceIdList, ",");
			List<Map> orderItemListMap=new ArrayList();
			for (int i = 0; i < sequenceArrayList.length; i++) {
				if(StringUtils.isNotEmpty(sequenceArrayList[i])){
					String[] logisticParamArray =StringUtils.split(sequenceArrayList[i],"|");
					if(3==logisticParamArray.length){
						Map paramMap=new HashMap();
						paramMap.put("orderItemId", logisticParamArray[0]);
						paramMap.put("expressChannelId", logisticParamArray[1]);
						paramMap.put("expressChannelItemId", logisticParamArray[2]);
//						paramMap.put("channelOrderItemId", logisticParamArray[3]);
//						paramMap.put("status", logisticParamArray[4]);
						orderItemListMap.add(paramMap);
					}
				}
				orderItemIdList.add(sequenceArrayList[i]);

			}
			if(null==orderItemListMap||orderItemListMap.size()==0){
				SpringControllerUtils.renderText(response, "false|" + "系统未知异常，请登录重试");
			}
			Map resultMap = txncoreOrderClientService.createExpressOrderList(orderItemListMap);
			
			if (!StringUtil.isEmpty(resultMap.get("responseCode") + "")
					&& "0000".equals(resultMap.get("responseCode") + "")) {
				SpringControllerUtils.renderText(response, "true");
			} else {
				String reason = StringUtil.null2String(resultMap
						.get("ReasonMessage"));
				SpringControllerUtils.renderText(response, "false|" + reason);
			}
			String responseCode = resultMap.get("responseCode")+"";
			String responseDesc = resultMap.get("responseDesc")+"";
			if (ResponseCodeEnum.SUCCESS.getCode().equals(responseCode)) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(responseDesc);
			}
			return null;
			
//			
//			
//
//			if (!StringUtil.isEmpty(resultMap.get("responseCode") + "")
//					&& "0000".equals(resultMap.get("responseCode") + "")) {
//				SpringControllerUtils.renderText(response, "true");
//			} else {
//				String reason = StringUtil.null2String(resultMap
//						.get("ReasonMessage"));
//				SpringControllerUtils.renderText(response, "false|" + reason);
//			}
//		} catch (Exception e) {
//			SpringControllerUtils.renderText(response, "fasle");
//		}
//		return null;
	}

	/**
	 * 出库扫描初始化
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView scanInit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView(scanView);
	}

	/**
	 * 查询出库订单
	 * 
	 * @param request
	 * @param response
	 * @param orderCriteria
	 * @return
	 * @throws Exception
	 */
	public ModelAndView scanOrder(HttpServletRequest request,
			HttpServletResponse response, OrderCriteria orderCriteria)
			throws Exception {

		Page<TradeOrder> page = PageUtils.getPage(request); // 分页
		Map queryMap = MapUtil.bean2map(orderCriteria);
		Page<Map> resultPage = txncoreOrderClientService.queryOrderItemInfo(
				queryMap, page);
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		return new ModelAndView(scanList, resultMap);
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setNextStepView(String nextStepView) {
		this.nextStepView = nextStepView;
	}

	public void setScanView(String scanView) {
		this.scanView = scanView;
	}

	public void setScanList(String scanList) {
		this.scanList = scanList;
	}

	public void setLeftView(String leftView) {
		this.leftView = leftView;
	}

	public void setLeftList(String leftList) {
		this.leftList = leftList;
	}

	public void setTxncoreOrderClientService(
			TxncoreOrderClientService txncoreOrderClientService) {
		this.txncoreOrderClientService = txncoreOrderClientService;
	}

	public void setTxncoreChannelClientService(
			TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

}
