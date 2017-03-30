package com.idealunited.poss.security.model;
import java.util.Date;

/**
 * AccessAuthority entity. @author MyEclipse Persistence Tools
 */

public class AccessAuthority implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long authId;
	private String authDept;
	private String authUser;
	private String authIp;
	private Date creationDate;
	private Date updateDate;
	private Integer status;
	private String loginName;

	// Constructors




	public Long getAuthId() {
		return authId;
	}



	public void setAuthId(Long authId) {
		this.authId = authId;
	}



	public String getAuthDept() {
		return this.authDept;
	}

	public void setAuthDept(String authDept) {
		this.authDept = authDept;
	}

	public String getAuthUser() {
		return this.authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public String getAuthIp() {
		return this.authIp;
	}

	public void setAuthIp(String authIp) {
		this.authIp = authIp;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}