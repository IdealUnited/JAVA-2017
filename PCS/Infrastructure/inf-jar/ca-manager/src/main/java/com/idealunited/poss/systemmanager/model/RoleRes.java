package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;

/**
 * 角色资源关系
 * 
 * @author wucan
 * @version
 * @data 2010-7-22 上午10:44:33
 */
public class RoleRes extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	private Long grantKy;
	private Long roleKy;
	private Long resKy;
	private String resTypePrefix;
	private int status;

	public Long getGrantKy() {
		return grantKy;
	}

	public void setGrantKy(Long grantKy) {
		this.grantKy = grantKy;
	}

	public Long getRoleKy() {
		return roleKy;
	}

	public void setRoleKy(Long roleKy) {
		this.roleKy = roleKy;
	}

	public Long getResKy() {
		return resKy;
	}

	public void setResKy(Long resKy) {
		this.resKy = resKy;
	}

	public String getResTypePrefix() {
		return resTypePrefix;
	}

	public void setResTypePrefix(String resTypePrefix) {
		this.resTypePrefix = resTypePrefix;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}