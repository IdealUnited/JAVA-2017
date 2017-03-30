package com.idealunited.util;



public enum TradeOrderStatusEnum {
	/** 0---交易未付款 */
	WAIT_PAY(0, "wait_pay","交易未付款"),
	/** 1---交易已关闭 */
	CLOSED(1, "closed","交易已关闭"),
	
	/** 2---交易已付款 */
	PAYED(2, "payed","交易已付款"),
	
	/** 3---交易已完成（含退款） */
	COMPLETED(3, "completed","交易已退款"),
	
	/** 4---交易成功 */
	SUCCESS(4, "success","交易成功"),
	
	/** 5---交易失败(和交易关闭是一个数值)*/
	FAILED(5, "failed","交易失败"),
	
	/**6---交易成功待发货*/
	TRADE_SUCCESS_BACK(6, "tradeSuccess_back","交易成功待发货"),
	/**14---发货审核中*/
	SEND_GOODS_CHECKING(7, "sendGoods_checking","发货审核中"),
	/**15---发货审核失败*/
	SENDGOODS_CHECK_FAILRD(8, "sendGoods_checkFailrd","发货审核"),
	/**16---退款*/
	REFUND(9, "refund","退款"),
	/**17---解冻*/
	THAW(10, "thaw","解冻"),
	/**18---已出款*/
	ALREADY_PAYMENT (11, "already_payment","已出款"),
	/**19---拒付*/
	REFUSE_PAYMENT(12, "refuse_payment","拒付"),
	ALL(13, "all","所有状态");
	private final int code;
	private final String description;
	private final String description_zh;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	TradeOrderStatusEnum(int code, String description,String description_zh) {
		this.code = code;
		this.description = description;
		this.description_zh = description_zh;
	}

	public String getDescription_zh() {
		return description_zh;
	}

	/**
	 * @return Returns the code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static TradeOrderStatusEnum getByCode(int code) {
		for (TradeOrderStatusEnum status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 通过枚举<code>description</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static TradeOrderStatusEnum getByDescription(String description) {
		for (TradeOrderStatusEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
}
