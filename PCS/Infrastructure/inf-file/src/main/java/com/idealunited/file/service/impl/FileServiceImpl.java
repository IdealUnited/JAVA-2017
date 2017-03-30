/**
 * 
 */
package com.idealunited.file.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.file.model.FileCodeMapping;
import com.idealunited.file.parser.BaseFileParser;
import com.idealunited.file.parser.dto.FileParseResult;
import com.idealunited.file.service.FileService;
import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.util.StringUtil;

import groovy.lang.GroovyClassLoader;

/**
 * @author chaoyue
 *
 */
public class FileServiceImpl implements FileService {

	private final Log logger = LogFactory.getLog(getClass());
	private BaseDAO fileCodeMappingDAO;

	@Override
	public FileParseResult parserFileInfo(File file, String code) {

		if (null == file) {
			return null;
		}
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		Map paraMap = new HashMap();
		paraMap.put("CODE", code);
		paraMap.put("STATUS", "1");
		FileCodeMapping fileCodeMapping = (FileCodeMapping) fileCodeMappingDAO
				.findObjectByCriteria("queryCodeMappings", paraMap);

		if (null == fileCodeMapping
				|| StringUtil.isEmpty(fileCodeMapping.getValue())) {
			logger.error("获取文件解析器为空！！ ,CODE:" + code);
			return null;
		}

		FileParseResult fileParseResult = null;

		try {
			ClassLoader parent = this.getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			Class<?> groovyClass = loader.parseClass(new File(fileCodeMapping
					.getValue()));// groovy文件路径，可放在工程里面或者外面任何目录下，计划放在opt目录下方便动态部署
			BaseFileParser groovyObject = (BaseFileParser) groovyClass
					.newInstance();

			fileParseResult = groovyObject.parserFileInfo(file);
		} catch (Exception e) {
			logger.error("parse error:", e);
		}

		return fileParseResult;
	}

	public void setFileCodeMappingDAO(BaseDAO fileCodeMappingDAO) {
		this.fileCodeMappingDAO = fileCodeMappingDAO;
	}

	@Override
	public FileParseResult parserFileInfo(InputStream in, String fileName,
			String code) {
		if (null == in) {
			return null;
		}
		if (StringUtil.isEmpty(code)) {
			return null;
		}

		Map paraMap = new HashMap();
		paraMap.put("CODE", code);
		paraMap.put("STATUS", "1");
		FileCodeMapping fileCodeMapping = (FileCodeMapping) fileCodeMappingDAO
				.findObjectByCriteria("queryCodeMappings", paraMap);

		if (null == fileCodeMapping
				|| StringUtil.isEmpty(fileCodeMapping.getValue())) {
			logger.error("获取文件解析器为空！！ ,CODE:" + code);
			return null;
		}

		FileParseResult fileParseResult = null;

		try {
			ClassLoader parent = this.getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			Class<?> groovyClass = loader.parseClass(new File(fileCodeMapping
					.getValue()));// groovy文件路径，可放在工程里面或者外面任何目录下，计划放在opt目录下方便动态部署
			BaseFileParser groovyObject = (BaseFileParser) groovyClass
					.newInstance();

			fileParseResult = groovyObject.parserFileInfo(in, fileName);
		} catch (Exception e) {
			logger.error("parse error:", e);
		}

		return fileParseResult;
	}

}
