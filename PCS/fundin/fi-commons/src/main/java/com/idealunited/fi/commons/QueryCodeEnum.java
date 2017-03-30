package com.idealunited.fi.commons;

/**
 * 
 * @author hhj
 *
 */
public enum QueryCodeEnum {
	FI_SPLIT("501","分账付款"),
	FI_SPLIT_REFUND("502","分账退款"),
	FI_B2CINCOME("100", "B2C网关充值"),
	FI_B2BINCOME("101","B2B网关充值"),
	FI_BIGINCOME("102","大额网关充值"),
	FI_YCARDINCOME("103","易卡充值"),
	FI_B2CPAY("104","B2C网关支付"),
	FI_B2BPAY("105","B2B网关支付"),
	FI_BIGPAY("106","大额网关支付"),
	FI_ACCTPAY("107","账户支付"),
	FI_YCARDPAY("108","易卡支付"),
	FI_PREPAID_RECHARGE("401", "预付卡充值"),	
	FI_PREPAID_PAY("402", "预付卡支付"),
	FI_PAY_REFUNDMENT("109","交易退款"),
	FI_MAN_POSTING("110","对账"),
	FI_CHARGE_REFUNDMENT("111","充值退款"),
	FI_DANBAOJY("112","担保交易"),
	FI_CREDIT_CARD_LARGE_DEPOSIT("119","信用卡大额充值"),	
	FI_CREDIT_CARD_LARGE_PAY("120","信用卡大额支付"),
	/** 汇总查询接口 Summary  */
	SUMMARY_VERSION_EXCEPTION("20000", "接口版本信息不能为空"),
	SUMMARY_SERIALID_EXCEPTION("20001", "请求序列号不能为空"),
	SUMMARY_TYPE_EXCEPTION("20002", "对账类型不能为空"),
	SUMMARY_PARTNERID_EXCEPTION("20003", "商户ID不能为空"),
	SUMMARY_CHARSET_EXCEPTION("20004", "编码方式不能为空"),
	SUMMARY_SIGNTYPE_EXCEPTION("20005", "签名类型不能为空"),
	SUMMARY_SIGNMSG_EXCEPTION("20006", "签名字符串不能为空"),
	
	/** 结果码  */
	RESULT_CODE_SUCCESS("0000","处理成功"),
	UN_ORDER_SUCCESS("0009","订单不存在"),
	
	/** 结果集长度   */
	RESULT_LENGTH_NO("0","无查询结果"),
	RESULT_LENGTH_EXCEPTION("-1","查询出现异常"),
	
	/** 对账类型   */
	PAYMENT_ORDER_ONE("1","支付订单"),
	REFUND_ORDER_TWO("2","退款订单"),
	
	/** 时间类型判断*/
	TIME_PASSDAY("30001","查询时间最大范围只能15天以内"),
	BEGINTIME_PASSDAY("30002","开始时间不能大于结束时间"),
	TIME_ERROR("30003","时间验证失败"),
	
	/** 支付订单状态   */
	PAYMENT_SUCCESS("2","成功"),
	PAYMENT_ORDER_SUCCESS("3,4","成功"),
	PAYMENT_ERROR("7","失败"),
	//PAYMENT_ZXONE("1,3,4,5,6","处理中"),
	PAYMENT_ZXONE("0","处理中"),
	
	/** 退款订单状态 */
	REFUND_CENTER("1","退款中"),
	REFUND_SUCCESS("2,4,5,6","退款成功"),
	REFUND_ERROR("3","退款失败");
	
	private final String code;
    private final String description;
    
    /**
     * 私有构造方法
     * @param code
     * @param description
     */
    private QueryCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
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
     * @param code
     * @return
     */
    public static QueryCodeEnum getByCode(String code) {
        for (QueryCodeEnum queryCodeEnum : values()) {
            if (queryCodeEnum.getCode().equals(code)) {
                return queryCodeEnum;
            }
        }
        return null;
    }

}
