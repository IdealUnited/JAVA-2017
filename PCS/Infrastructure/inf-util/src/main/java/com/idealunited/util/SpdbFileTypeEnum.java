package com.idealunited.util;


/**
 * 浦发银行文件前辍及序列名称
 * @author snn
 *
 */
public enum SpdbFileTypeEnum {
	BUY_FOREX_BATCH("01", "BuyForexBatch","购汇申请文"), 
	BUY_FOREX_DETAIL_BATCH("02", "BuyForexDetailBatch","购汇明细文件"), 
	PAY_FOREX_BATCH("03", "PayForexBatch","付汇申请文件"), 
	PAY_FOREX_DETAIL_BATCH("04", "PayForexDetailBatch","付汇申请明细文件"), 
	RE_FOREX_DETAIL_BATCH("05", "ReForexDetailBatch","退汇申请明细文件"), 
	SELL_FOREX_BATCH("06", "SellForexBatch","结汇申请文件"), 
	SELL_FOREX_DETAIL_BATCH("07", "SellForexDetailBatch","结汇明细文件"), 
	RECEIPT_FOREX_BATCH("08", "ReceiptForexBatch","收汇申请明细文件"), 
	//浦发下发文件
	RATE_RET("09", "Rate","一日一价汇率文件"), 
	BUY_FOREX_BATCH_RET("10", "BuyForexBatch","购汇结果文件"), 
	REGISTER_FOREX_RET("11", "RegisterForex","购汇登记结果文件"), 
	PAY_FOREX_BATCH_RET("12", "PayForexBatch","付汇结果文件"), 
	DECLARE_DETAIL_RET("13", "DeclareDetail","付汇登记结果文件"), 
	SELL_FOREX_BATCH_RET("14", "SellForexBatch","结汇结果文件"), 
	REGISTER_SELL_FOREX_RET("15", "RegisterSellForex","结汇登记结果文件"), 
	ERROR_DES("16","ErrorDes","错误描述文件"),
	;

	private String code;
	private String seqName;
	private String desc;

	private SpdbFileTypeEnum(String code,String seqName, String desc) {
		this.code = code;
		this.seqName = seqName;
		this.desc = desc;
	}

	public static boolean isExists(String code) {
		for (SpdbFileTypeEnum item : SpdbFileTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public static String getSpdbFileTypeNameByCode(String code) {
		for (SpdbFileTypeEnum item : SpdbFileTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getSeqName();
			}
		}
		return null;
	}
	
	public static SpdbFileTypeEnum getSpdbFileTypeEnumByCode(String code) {
		for (SpdbFileTypeEnum item : SpdbFileTypeEnum.values()) {
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

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

}
