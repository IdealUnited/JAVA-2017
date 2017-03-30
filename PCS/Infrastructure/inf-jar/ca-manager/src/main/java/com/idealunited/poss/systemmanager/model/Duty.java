package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;


/**
 * 职位
 * 
 * @author wucan
 * @version
 * @data 2010-7-22 上午10:25:30
 */
public class Duty extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	private Long dutyKy;
	private Long staffKy;
	private Long positionKy;
	private String remark;
	private int status;
	private String dutyCode;
	private String dutyName;

	public Long getDutyKy() {
		return dutyKy;
	}

	public void setDutyKy(Long dutyKy) {
		this.dutyKy = dutyKy;
	}

	public Long getStaffKy() {
		return staffKy;
	}

	public void setStaffKy(Long staffKy) {
		this.staffKy = staffKy;
	}

	public Long getPositionKy() {
		return positionKy;
	}

	public void setPositionKy(Long positionKy) {
		this.positionKy = positionKy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

}