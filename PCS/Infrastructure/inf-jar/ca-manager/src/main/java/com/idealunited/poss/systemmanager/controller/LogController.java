package com.idealunited.poss.systemmanager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessLog;
import com.idealunited.poss.systemmanager.service.ILogService;

/**
 * 后台系统日志
 * sandy
 */
public class LogController extends MultiActionController {

	private String initView;
	private String listView;
	private String downView;
	private ILogService logService;


	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView(initView);
	}

	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, AccessLog accessLog)
			throws Exception {

		Map<String, Page<AccessLog>> model = new HashMap<String, Page<AccessLog>>();

		Page<AccessLog> page = PageUtils.getPage(request); // 分页
		page = logService.search(page, accessLog);
		model.put("page", page);
		return new ModelAndView(listView, model);
	}
	
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response, AccessLog accessLog)
					throws Exception {
		
		Map<String, Page<AccessLog>> model = new HashMap<String, Page<AccessLog>>();
		setResonseHeader(request, response);
		Page<AccessLog> page = PageUtils.getPage(request); // 分页
		page = logService.search(page, accessLog);
		page.setPageNo(1);
		page.setPageSize(page.getTotalCount());
		page = logService.search(page, accessLog);
		model.put("page", page);
		return new ModelAndView(downView, model);
	}
	
	protected void setResonseHeader(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Expires", "0");
		response.setHeader("Pragma" ,"public");
		response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Cache-Control", "public");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("log.xls").getBytes("UTF-8"),
						"ISO8859_1"));
	}
	
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}
	
	public void setDownView(String downView) {
		this.downView = downView;
	}

}
