package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;


/**
 * 角色
 * 
 * @author wucan
 * @version
 * @data 2010-7-22 上午10:43:52
 */
public class Role extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	private Long roleKy;
	private String roleCode;
	private String roleName;
	private String roleRemarks;
	private int status;

	public Long getRoleKy() {
		return roleKy;
	}

	public void setRoleKy(Long roleKy) {
		this.roleKy = roleKy;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRoleRemarks() {
		return roleRemarks;
	}

	public void setRoleRemarks(String roleRemarks) {
		this.roleRemarks = roleRemarks;
	}

}