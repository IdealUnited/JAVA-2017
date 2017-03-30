/**
 * 
 */
package com.idealunited.controller.order;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chaoyue
 *
 */
public class ImportCommand {

	private String platform;

	private MultipartFile file;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
