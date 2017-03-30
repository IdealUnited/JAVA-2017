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

import com.idealunited.dto.Repository;
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
 * 仓库服务接口
 * 
 * @author chaoyue
 *
 */
public class TxncoreRepositoryClientService {

	private final Log logger = LogFactory.getLog(getClass());
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}

	/**
	 * 新建仓库
	 * 
	 * @return
	 */
	public void createRepository(Repository repository) {

		List<Repository> orders = new ArrayList<Repository>();
		orders.add(repository);
		Map paraMap = new HashMap();
		paraMap.put("orders", orders);
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_REPOSITORY_CREATE.getCode(), sysTraceNo,
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
	}

	/**
	 * 查询仓库列表
	 * 
	 * @return
	 */
	public Page<Repository> queryRepository() {

		Map paraMap = new HashMap();
		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.POSS.getCode());
		String result = invokeService.invoke(
				SerCode.TXNCORE_REPOSITORY_QUERY.getCode(), sysTraceNo,
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
		List<Repository> orders = MapUtil.map2List(Repository.class,
				resultMapList);
		resultPage.setResult(orders);
		return resultPage;
	}
}
