package com.idealunited.controller.stock;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件包装类
 */
public class StockFileUploadBean {

	private String repositoryId;
	private MultipartFile file;

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

}
