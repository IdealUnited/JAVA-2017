package com.idealunited.poss.systemmanager.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.idealunited.client.TxncoreRepositoryClientService;
import com.idealunited.dto.Repository;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.model.Users;
import com.idealunited.poss.systemmanager.service.IUserService;

/**
 * 用户新增
 * 
 * @author wucan
 * @descript
 * @data 2010-7-22 下午12:08:12
 * @author 戴德荣
 * @date 2010-11-30
 */

public class UserAddController extends SimpleFormController {

	protected final Log log = LogFactory.getLog(getClass());
	private TxncoreRepositoryClientService txncoreRepositoryClientService;
	private IUserService userService;

	public ModelAndView ajaxOnSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		UserFormBean userFormBean = (UserFormBean) command;
		PrintWriter out = response.getWriter();
		try {
			userService.saveUser(userFormBean);
			out.print("success");
		} catch (Exception e) {
			log.error("UserAddController.ajaxOnSubmit保存用户出现异常", e);
			out.print("error");

		}
		return null;
	}

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		UserFormBean userFormBean = (UserFormBean) command;
		// 判断是否被注册过
		Users exitsUser = userService.findUserByLoginId(userFormBean
				.getUserCode());
		if (exitsUser != null && exitsUser instanceof Users) {
			return showForm(request, errors, getFormView()).addObject(
					"command", userFormBean).addObject("error",
					userFormBean.getUserCode() + "已经存在，请更名！");
		}
		try {
			userService.saveUser(userFormBean);
		} catch (Exception e) {
			log.error("UserAddController.onSubmit保存用户出现异常", e);
		}
		return new ModelAndView("redirect:user.do");
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map paraMap = new HashMap();

		Map userMap = userService.queryAllDutyAndOrg();

		paraMap.putAll(userMap);
		List<Repository> repositorys = txncoreRepositoryClientService
				.queryRepository().getResult();
		paraMap.put("repositorys", repositorys);
		return paraMap;

	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		return new UserFormBean();
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setTxncoreRepositoryClientService(
			TxncoreRepositoryClientService txncoreRepositoryClientService) {
		this.txncoreRepositoryClientService = txncoreRepositoryClientService;
	}

}
