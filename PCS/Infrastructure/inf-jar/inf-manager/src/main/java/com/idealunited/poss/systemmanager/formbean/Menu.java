package com.idealunited.poss.systemmanager.formbean;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 * @Description 
 * @project 	poss-systemmanager
 * @file 		Menu.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-8-1		Volcano.Wu			Create
 */
public class Menu implements Serializable {

	private String code; // 菜单ky
	private String text; // 菜单名称
	private String url; // url
	private String imgUrl; // image

	private List<Menu> sonMenuList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<Menu> getSonMenuList() {
		return sonMenuList;
	}

	public void setSonMenuList(List<Menu> sonMenuList) {
		this.sonMenuList = sonMenuList;
	}

}
