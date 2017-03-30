package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;


/**
 * 
 * @Description 
 * @project 	poss-systemmanager
 * @file 		Org.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-7-26		Volcano.Wu			Create
 */
public class Org extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	private Long orgKy; // 主键
	private String orgCode; // 组织单元编码
	private String orgName; // 组织单元名称
	private Integer orgKind; // 组织单元类型
	private Integer orgType; // 组织单元形态类型
	private int orgLevel;
	private Long parentOrg; // 所属上级行政组织单无
	private Long orgMgr;
	private Long areaCode;
	private int status;

	public Long getOrgKy() {
		return orgKy;
	}

	public void setOrgKy(Long orgKy) {
		this.orgKy = orgKy;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgKind() {
		return orgKind;
	}

	public void setOrgKind(Integer orgKind) {
		this.orgKind = orgKind;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public int getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Long getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Long parentOrg) {
		this.parentOrg = parentOrg;
	}

	public Long getOrgMgr() {
		return orgMgr;
	}

	public void setOrgMgr(Long orgMgr) {
		this.orgMgr = orgMgr;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}