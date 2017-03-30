/**
 *  File: BaseFileParser.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-11-10      Jason_wang      Changes
 *  
 *
 */
package com.idealunited.file.parser;

import java.io.File;
import java.io.InputStream;

import com.idealunited.file.parser.dto.FileParseResult;

/**
 * @author Jason_wang
 *
 */
public interface BaseFileParser {

	/**
	 * 解析文件
	 * 
	 * @param file
	 * @param code
	 *            映射解析器的code
	 * @return
	 * @throws Exception
	 */
	FileParseResult parserFileInfo(File file) throws Exception;

	/**
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	FileParseResult parserFileInfo(InputStream in, String fileName)
			throws Exception;
}
