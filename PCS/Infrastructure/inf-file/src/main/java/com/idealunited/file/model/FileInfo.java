package com.idealunited.file.model;

import java.util.Date;

public class FileInfo {
	private String 	fileId	;//	id主键
	private String 	fileName	;//	文件名
	private String 	filePath	;//	文件路径
	private String 	fileType	;//	"文件类型：1:客户信息登记维护上传(客户到银行)5: 付汇交易明细对照文件上传(客户到银行)6: 收汇交易明细对照文件上传(客户到银行)"
	private Integer recordNum	;//	记录数
	private String 	encryptKey	;//	密钥
	private String 	status	;//	状态：1上载中,2上载成功,3上载失败4通知成功5通知失败
	private Date 	createDate	;//	创建时间
	private Date 	updateDate	;//	更新时间
	private String uploadFlag;	//1上传给银行的文件;2从银行下载的文件
	private String errCode;		//返回错误码
	private String errDesc;		//返回错误码描述
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}
	public String getEncryptKey() {
		return encryptKey;
	}
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
}
