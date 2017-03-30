package com.pay.file.parser.groovy

import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader

import java.io.FileInputStream

import com.pay.file.parser.AbstractBaseFileParser
import com.pay.file.parser.dto.FileParseResult
import com.pay.util.StringUtil

import com.pay.file.parser.dto.GatewayReconciliationParserMode

/**		
 *  @author chma
 *  @Date 2015-5-31
 *  @Description 中银卡司对账文件解析
 *  @Copyright Copyright © 2004-2013 pay.com . All rights reserved.
 */
class Bocs_rc extends AbstractBaseFileParser {

	@Override
	public FileParseResult parserFile(File file) throws Exception {
		
		FileParseResult fileParseResult = new FileParseResult();
		
		FileInputStream infile = new FileInputStream(file);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(infile))
        def line = rd.readLine()
        def number=0
        while((line = rd.readLine()) != null){
            if(!line.startsWith("TotalRecord")){
				String[] str = line.split("\\|");
				GatewayReconciliationParserMode parserMode = new GatewayReconciliationParserMode();
				parserMode.setChannelOrderNo(str[19].trim());
				parserMode.setStatus("1");
				parserMode.setSettAmount(str[12]);
				fileParseResult.addItem(parserMode);
			}else{
				def totalRecord = line.substring(12, 22);
				fileParseResult.setTotalRecord(Integer.valueOf(totalRecord));
			}
        }
		
		return fileParseResult;
	}

}