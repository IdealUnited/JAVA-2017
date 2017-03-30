/**
 * 
 */
package com.idealunited.file.service;

import java.io.File;
import java.io.InputStream;

import com.idealunited.file.parser.dto.FileParseResult;

/**
 * @author chaoyue
 *
 */
public interface FileService {

	/**
	 * 解析文件
	 * 
	 * @param file
	 * @param code
	 * @return
	 */
	FileParseResult parserFileInfo(File file, String code);

	/**
	 * 
	 * @param in
	 * @param code
	 * @return
	 */
	FileParseResult parserFileInfo(InputStream in, String fileName, String code);

}
