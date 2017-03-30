package com.idealunited.poss.security.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

import com.idealunited.poss.security.commons.SecurityConstants;

public class SessionUserHolder extends User {
	/**
	 * serialVersionUID
	 */
	private Locale locale;
	private Date loginTime;// 登录时间
	private String userKy;// 主键
	private String userType;// 用户类型
	private String loginIp;//用户的ip的地址

	private String orgCode;// 机构编码,部门编号
	private boolean handleInfo = false;

//	private List<String> groupCodes = new ArrayList<String>();// 所在工作组
//
//	private List<String> resMenuCodes = new ArrayList<String>();
	
	//所有资源列表，用于权限
	private List<String> resources = new ArrayList<String>();

	public SessionUserHolder(String userKy,String userName, String password,  GrantedAuthority[] authorities) {
		super(userName, password, true, true, true, true, authorities);
		this.userKy=userKy;
	}

	public boolean isSuperManagerOfA() {
		return SecurityConstants.SUPER_ADMIN_A.equals(getUsername());
	}

	public boolean isSuperManagerOfB() {
		return SecurityConstants.SUPER_ADMIN_B.equals(getUsername());
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserKy() {
		return userKy;
	}


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	// ---------------------机构信息

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public boolean isHandleInfo() {
		return handleInfo;
	}

	public void setHandleInfo(boolean handleInfo) {
		this.handleInfo = handleInfo;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	/**
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	 

}
