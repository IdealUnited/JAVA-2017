package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;

/**
 * 用户角色关系
 * 
 * @author wucan
 * @version
 * @data 2010-7-22 上午10:49:06
 */
public class UserRole extends BaseObject{

	private static final long serialVersionUID = 1L;
	private Long grantKy;
	private Long userKy;
	private Long roleKy;
	private int status;

	public Long getGrantKy() {
		return grantKy;
	}

	public void setGrantKy(Long grantKy) {
		this.grantKy = grantKy;
	}

	public Long getUserKy() {
		return userKy;
	}

	public void setUserKy(Long userKy) {
		this.userKy = userKy;
	}

	public Long getRoleKy() {
		return roleKy;
	}

	public void setRoleKy(Long roleKy) {
		this.roleKy = roleKy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}