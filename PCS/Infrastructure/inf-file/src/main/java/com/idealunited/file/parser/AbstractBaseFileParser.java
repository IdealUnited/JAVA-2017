/**
 *  <p>File: AbstractFileParser.java</p>
 *  <p>Description:</p>
 *  <p>Copyright: © 2004-2013 pay.com . All rights reserved.版权所有</p>
 *	<p>Company: </p>
 *  @author zengli
 *  @version 1.0  
 */
package com.idealunited.file.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.file.parser.dto.FileParseResult;

/**
 * <p>
 * </p>
 * 
 * @author zengli
 * @since 2011-6-1
 * @see
 */
public abstract class AbstractBaseFileParser implements BaseFileParser {

	private final Log logger = LogFactory.getLog(AbstractBaseFileParser.class);

	protected static final int BANK_STATUS_1 = 101;
	protected static final int BANK_STATUS_2 = 102;
	protected static final int BANK_STATUS_3 = 103;
	protected static final int FILE_INFO_STATUS_DEFUALT = 1; // 有效

	/**
	 * 解析成功
	 */
	public static final Integer TXT_FILE_READ_SUCCESS = 0;
	/**
	 * 文件格式错误
	 */
	public static final Integer TXT_FILE_FORMAT_ERROR = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.exp.fundout.bankfile.spi.parser.BaseFileParser#parserFileInfo(java
	 * .util.Map)
	 */
	@Override
	public FileParseResult parserFileInfo(File file) throws Exception {

		if (null == file) {
			return null;
		}
		try {
			FileParseResult fileParseResult = parserFile(file);
			return fileParseResult;
		} catch (FileNotFoundException e) {
			logger.info("文件不存在..." + "stack info : ", e);
			throw new Exception("解析异常,没有找到对应的文件", e);
		} catch (Exception e) {
			logger.info("groovy解析异常" + "stack info : ", e);
			throw new Exception("groovy解析异常..", e);
		}
	}

	@Override
	public FileParseResult parserFileInfo(InputStream in, String fileName)
			throws Exception {

		if (null == in) {
			return null;
		}
		try {
			FileParseResult fileParseResult = parserFile(inputstreamtofile(in,
					fileName));
			return fileParseResult;
		} catch (Exception e) {
			logger.info("groovy解析异常" + "stack info : ", e);
			throw new Exception("groovy解析异常..", e);
		}
	}

	public File inputstreamtofile(InputStream ins, String fileName) {
		File file = new File(fileName);
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 
	 * @param fileParseResult
	 */
	abstract protected FileParseResult parserFile(File file) throws Exception;
}
