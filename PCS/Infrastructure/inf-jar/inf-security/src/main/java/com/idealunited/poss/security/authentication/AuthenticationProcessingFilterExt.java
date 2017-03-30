package com.idealunited.poss.security.authentication;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.web.bind.ServletRequestUtils;

import com.idealunited.poss.security.commons.SecurityConstants;
import com.idealunited.poss.security.model.SessionUserHolder;
import com.idealunited.poss.security.service.IAccessAuthorityService;
import com.idealunited.poss.security.service.ILoginService;
import com.idealunited.poss.security.service.IUserService;
import com.idealunited.poss.security.util.IpUtils;
import com.idealunited.poss.security.util.SecutiryRequestUtil;

public class AuthenticationProcessingFilterExt extends AuthenticationProcessingFilter{
	private Log logger = LogFactory.getLog("AuthenticationProcessingFilterExt");
	private ILoginService loginService;
	private IUserService userService;
	private IAccessAuthorityService accessAuthorityService;
	

	public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Request is to process authentication");
		}
		
		//验证验证码是否正确
		String randSession = (String) request.getSession().getAttribute("rand");
		String randRequest = ServletRequestUtils.getStringParameter(request, "rand","");
		if(!(randRequest.length()>0 &&randRequest.equalsIgnoreCase(randSession)) ){
			logger.warn("登录验证码有误:"+request.getRemoteHost());
			sendRedirect(request, response, SecurityConstants.ACEGI_EXCEPTION_MAPPING_ERROR_RAND_CODE);
			return;
		}
		///验证码校验成功
		//让session失效，重新取一个session
		request.getSession().invalidate();
		
		
		//如果是超级管理员
		String userName = obtainUsername(request);
		if(!(SecurityConstants.SUPER_ADMIN_A.equalsIgnoreCase(userName)|| SecurityConstants.SUPER_ADMIN_B.equalsIgnoreCase(userName))){
			///验证ip是否合法，不是本地ip才验证，不对内网进行过虑
			String userIp = SecutiryRequestUtil.getIp(request);
			String serverIp  = request.getLocalAddr();
			if(! serverIp.equals(userIp) ){
				if(! IpUtils.isLocalAreaIp(userIp)){
					if(! accessAuthorityService.validateIp(userIp)){
						logger.warn("未授权ip :"+ userIp);
						sendRedirect(request, response, SecurityConstants.ACEGI_EXCEPTION_MAPPING_ERROR_AUTH_IP);
						return;
					}
				}
			}
			///验证用户成功
			logger.info("验证ip成功 :"+ userIp+ "/t user:"+userName);
		}
		
		// 认证处理
		Authentication authResult;
		try {
			authResult = attemptAuthentication(request);
			if (authResult == null) {
				return;
			}
			
		} catch (AuthenticationException failed) {
			// 认证失败，登录日志中失败数+1，并保存最新日志内容
			unsuccessfulAuthentication(request, response, failed);
			return;
		}

		// 扩充用户信息(设置权限)
		try {
			SessionUserHolder userHolder = (SessionUserHolder) authResult.getPrincipal();
			extendUserDetails(request, response, userHolder);
		} catch (Exception e) {
			logger.error("处理登录用户的权限信息出错 [" + userName + "]");
			sendRedirect(request, response, SecurityConstants.ACEGI_EXCEPTION_MAPPING_ERROR_EXTEND_AUTH);
			return;
		}
		logger.info( userName + "登录成功"+"IP:"+SecutiryRequestUtil.getIp(request));
		successfulAuthentication(request, response, authResult);
	}

	/**
	 * 扩展用户信息(机构、工作组等)
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @param userDetails
	 *            认证通过对象
	 */
	private void extendUserDetails(HttpServletRequest httpRequest, HttpServletResponse httpResponse, SessionUserHolder userHolder) throws Exception {
		//设置用户资源列表
		List<String> list =  userService.queryResesByLoginCode(userHolder.getUsername());
		userHolder.setResources(list);
		//修改最后登录时间和ip
		String ip = httpRequest.getRemoteAddr();
		loginService.updateUserLoginInfo(userHolder.getUsername(), ip, new Date());
		//查询用户详情　设置用户的orgCode
		Map map =  userService.getUserByLoginCode(userHolder.getUsername());
		userHolder.setOrgCode((String)map.get("ORG_CODE"));
		
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @param accessAuthorityService the accessAuthorityService to set
	 */
	public void setAccessAuthorityService(
			IAccessAuthorityService accessAuthorityService) {
		this.accessAuthorityService = accessAuthorityService;
	}
	
	
	
}
