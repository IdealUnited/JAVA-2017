package com.idealunited.file.model;

import java.util.Date;

public class FileCodeMapping {
	private String family;
	private String category;
	private String code;
	private String name;
	private String value;
	private String descrite;
	private Date updateTime;
	private int status;

	public String getIdentity() {
		return family + category + code;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescrite() {
		return descrite;
	}

	public void setDescrite(String descrite) {
		this.descrite = descrite;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
