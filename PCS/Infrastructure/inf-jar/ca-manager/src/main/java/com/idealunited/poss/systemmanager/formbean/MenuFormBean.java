package com.idealunited.poss.systemmanager.formbean;

import java.util.List;

/**
 * 角色管理 封装树菜单
 * 
 * @Description
 * @project poss-systemmanager
 * @file MenuFormBean.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 pay Corporation. All rights reserved.
 *          Date Author Changes 2010-8-5 Volcano.Wu Create
 */
public class MenuFormBean {

	private String resKy; // 流水号
	private String code; // 编号
	private String name; // 名称
	private String url; // url
	private String image; // 图片
	private String position; // 位置
	private String status; // 状态

	private String parent; // 父节点
	private List<MenuFormBean> childs; // 所有子节点
	private MenuFormBean parentMenu; // 父菜单

	private Integer authorityFlag;

	public MenuFormBean getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(MenuFormBean parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<MenuFormBean> getChilds() {
		return childs;
	}

	public void setChilds(List<MenuFormBean> childs) {
		this.childs = childs;
	}

	public String getResKy() {
		return resKy;
	}

	public void setResKy(String resKy) {
		this.resKy = resKy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getAuthorityFlag() {
		return authorityFlag;
	}

	public void setAuthorityFlag(Integer authorityFlag) {
		this.authorityFlag = authorityFlag;
	}

}
