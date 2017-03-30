package com.idealunited.util;

import org.apache.commons.lang.StringUtils;


/**
 * 浦发银行压缩文件对应子文件类型
 * @author snn
 *
 */
public enum SpdbZipFileTypeEnum {
	
	BUY_FOREX_BATCH_ZIP("01", "01,02,","购汇申请及明细文件"), 
	PAY_FOREX_BATCH_ZIP("03", "03,04,05","付汇申请及明细及退汇文件"), 
	SELL_FOREX_BATCH_ZIP("06", "06,07,","结汇申请及明细文件"), 
	RECEIPT_FOREX_BATCH_ZIP("08", "08,,","收汇申请明细文件"), 
	//浦发下发文件
	RATE_RET_ZIP("09", "09,","一日一价汇率文件"), 
	BUY_FOREX_BATCH_RET_ZIP("10", "10,11","购汇结果及登记结果文件"), 
	PAY_FOREX_BATCH_RET_ZIP("12", "12,","付汇结果文件"), 
	PAY_DECLARE_DETAIL_RET_ZIP("13", "13,","付汇登记结果文件"), 
	SELL_FOREX_BATCH_RET_ZIP("14", "14,15","结汇结果及登记结果文件"), 
	;

	private String code;
	private String subFileType;
	private String desc;

	private SpdbZipFileTypeEnum(String code,String subFileType, String desc) {
		this.code = code;
		this.subFileType = subFileType;
		this.desc = desc;
	}

	public static boolean isExists(String code) {
		for (SpdbZipFileTypeEnum item : SpdbZipFileTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public static String[] getSubFileTypeByCode(String code) {
		for (SpdbZipFileTypeEnum item : SpdbZipFileTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getSubFileType().split(",", -1);
			}
		}
		return null;
	}
	
	public static SpdbZipFileTypeEnum getSpdbFileTypeEnumByCode(String code) {
		for (SpdbZipFileTypeEnum item : SpdbZipFileTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String getSubFileType() {
		return subFileType;
	}

	public void setSubFileType(String subFileType) {
		this.subFileType = subFileType;
	}
	
	public static void main(String[] args) {
		System.out.println(getSubFileTypeByCode("01").length);
	}
}
