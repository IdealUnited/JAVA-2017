package com.test;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.idealunited.file.parser.BaseFileParser;
import com.idealunited.file.parser.dto.FileParseResult;

/**
 * @author lIWEI
 * @Date 2011-5-31
 * @Description
 * @Copyright Copyright (c) 2004-2013 pay.com . All rights reserved. 版权所有
 */
public class GroovyTest {

	public static void main(String[] args) throws Exception, IOException {

		GroovyTest t = new GroovyTest();
		ClassLoader parent = t.getClass().getClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class<?> groovyClass = loader.parseClass(new File(
				"/opt/exp/parser/Amazon_order.groovy"));// groovy文件路径，可放在工程里面或者外面任何目录下，计划放在opt目录下方便动态部署

		Object parser = groovyClass.newInstance();
		BaseFileParser groovyObject = (BaseFileParser) parser;
		File file = new File(
				"/Users/lijingneng/Downloads/XY.txt");
		FileParseResult fileParseResult = groovyObject.parserFileInfo(file);

		
		
		if (null != fileParseResult.getList()
				&& !fileParseResult.getList().isEmpty()) {
			for (int i = 0; i < fileParseResult.getList().size(); i++) {
				System.out.println(fileParseResult.getList().get(i));
			}
		}

	}
}
