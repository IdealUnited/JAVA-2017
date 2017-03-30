package com.idealunited.controller.product;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件包装类
 */
public class SkuFileUploadBean {

	private String platform;

	private MultipartFile file;

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
