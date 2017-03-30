package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;

/**
 * 菜单资源
 * 
 * @author ddr 2012-6-1 重新改造
 * @version
 * @data 2010-7-22 上午10:40:20
 */
public class ResMenu extends BaseObject {

	private static final long serialVersionUID = 1L;

	private Long resKy;
	private String code;
	private String name;
	private String url;
	private Long parent;
	private String image;
	private Long position;
	private Integer status;
	private Integer level;
	private Integer authorityFlag;

	public Long getResKy() {
		return resKy;
	}

	public void setResKy(Long resKy) {
		this.resKy = resKy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuthorityFlag() {
		return authorityFlag;
	}

	public void setAuthorityFlag(Integer authorityFlag) {
		this.authorityFlag = authorityFlag;
	}

}