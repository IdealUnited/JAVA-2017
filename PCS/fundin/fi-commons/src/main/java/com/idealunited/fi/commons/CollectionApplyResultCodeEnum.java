package com.idealunited.fi.commons;

/**
 * 支付单报送返回码
 * @author Simen.Liu
 *
 */
public enum CollectionApplyResultCodeEnum {

	SUCC("0000","成功"),
	FAIL("0001","请求失败"),
	VERSION_ERROR("0002","版本号不正确"),
	ORDERNO_ERROR("0003","订单号不合法"),
	DATE_FORMAT_ILLEGAL("0004","提交时间格式错误"),
	AMOUNT_FORMAT_ERROR("0005","金额格式不正确"),
	PAY_TYPE_ERROR("0006","支付方式不正确"),
	DELIVER_GOODS_AUDIT_SUCC("0007","发货审核通过,交易成功"),
	DELIVER_GOODS_AUDIT_FAIL("0008","发货审核不通过"),
	ORDERNO_EXISTS("0049","订单号已经存在"),
	REFUND_HISTORY_FAIL("0301","退款历史记录失败"),
	REFUND_MERCHANT_NONEXISTENCE("0302","退款商户不存在"),
	PARAM_ISNULL("0303","报文必填参数为空"),
	MERCHANT_REFUND_DATE_FORMAT_ERROR("0304","商户退款订单时间不正确,以YYYYMMDDHHMMSS 格式的时间字符串"),
	REFUND_TRADE_GETWAYORDER_NONEXISTENCE("0305","退款交易对应的网关订单不存在"),
	REFUND_TRADE_PAYMENTORDER_NONEXISTENCE("0306","退款交易对应的支付订单不存在"),
	ORDER_STATUS_NOTSUCC("0307","即时交易：订单状态不为已成功，不能进行退款"),
	MERCHANT_REFUND_REPEAT_ERROR1("0308","商户退款订单处理完成，请勿重复退款"),
	MERCHANT_REFUND_AMOUNT_ERROR("0309","商户退款订单金额不正确,以分为单位的金额,最大长度不超过10"),
	FULL_REFUND_AMOUNT_ERROR("0310","全额退款类型,退款金额不符"),
	REFUND_FAIL_AMOUNT_LESS_PAYAMOUNT("0311","退款失败,退款金额大于支付余额"),
	REFUND_SEQ_EXIST("0312","退款请求序列号已经存在"),
	REFUND_ORDER_EXIST("0313","商户退款订单号已经存在"),
	SIGN_FAIL("0314","报文验签失败"),
	PAYER_ACCT_OFF_IN("0315","付款方帐户止入"),
	MERCHANT_OFF_IN_OR_OUT("0316","商户止入或者止出"),
	REFUND_LOGICA_VALIDATE_FAIL("0317","退款逻辑验证失败"),
	UPDATE_BALANCE_ERROR("0318","更新余额异常"),
	CACULATEFEE_FAIL("0319","手续费用计算失败"),
	CREATE_REFUND_ORDER_FAIL("0320","退款订单创建失败 "),
	ACCOUNTS_REFUND_FAIL("0321","帐务退款失败"),
	GETWAY_ORDER_EXCEPTION("0322","网关订单异常"),
	RECHARGE_REFUND_EXCEPTION("0323","充退异常"),
	CREATE_REFUND_ORDER_EXCEPTION("0324","构建退款订单异常"),
	update_REFUND_order_fail("0325","网关订单记帐成功修改退款订单失败"),
	PARAM_ERROR("0326","报文参数错误"),
	REFUND_fail_("0327","退款失败,退款金额大于商户可用余额"),
	AUMOT_IS_ZERO("0328","退款金额小于等于0"),
	MERCHANT_REFUND_REPEAT_ERROR2("0329","商户退款订单处理完成，请勿重复退款"),
	getway_order_not_over("0330","网关订单非交易完成状态,不可以进行退款"),
	ECARD_REFUND_2PAYER_FEE_FAIL("0331","易卡退付款方手续费失败"),
	REQ_VERSION_ERROR("0332","请求版本不正确"),
	BASE_INFO_VALIDATE_FAIL("0401","基本数据校验失败"),
	VALIDATE_BUSINESS_PARAM_FAIL("0402","校验业务参数失败"),
	QUERY_MERCHANT_REQ_GETWAY_HISTORY_FAIL("0403","查询商户请求网关历史记录失败"),
	API_FAIL("0404","查询API-失败"),
	DATE_VALIDATE_FAIL("0405","时间验证失败"),
	DATE_CONVERT_FAIL("0406","时间转换类型失败"),
	BETWEEN_STARTDATE_ERROR("0407","开始时间不能大于结束时间"),
	QUERY_DATE_MAX_15DAY("0408","查询时间最大范围只能15天以内"),
	CREATE_GETWAR_RESULT_FAIL("0409","创建网关返回商户结果失败"),
	QUERY_SIGN_FAIL("0410","查询验签失败"),
	ORDER_NONEXISTENCE("0411","查询订单不存在"),
	BALANCE_INSUFICIENTE("3084","余额不足"),
	PASSWORD_ERROR("3085","密码错误"),
	VIOLATE_CONFIDENTIALITY("3086","违反保密规定"),
	CARDNO_COUNT_LIMIT("3088","卡号次数超限"),
	CHEAT_ACTION("3089","有作弊嫌疑"),
	BANK_PARAM_ERROR("3091","银行参数错误"),
	TRADE_INVALID("3092","无效交易"),
	AMOUNT_INVALID("3093","金额无效"),
	CARD_NO_INVALID("3094","卡号无效"),
	TRADE_TIMEOUT("3095","交易超时"),
	VALIDDATE_FORMAT_ERROR("3096","有效期错误"),
	UNSUPPORTED_CARDTYPE("3097","不支持该卡种"),
	SYSTEM_EXCEPTION("3098","系统异常"),
	OTHER_EXCEPTION("3099","其他异常"),
	MERCHANT_NONEXISTENCE("4001","商户不存在"),
	REPEAT_PAYMENT("5001","订单重复支付"),
	FOR_DECLARE_FAIL("6001","报关失败");
	
	String code;
	String desc;
	
	/**
	 * CollectionApplyResultCodeEnum
	 * @param code
	 * @param desc
	 */
	CollectionApplyResultCodeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Getter CollectionApplyResultCodeEnum
	 * @param code
	 * @return CollectionApplyResultCodeEnum
	 */
	public static CollectionApplyResultCodeEnum getResultCodeEnum(String code){
		for(CollectionApplyResultCodeEnum carce : values()){
			if(carce.getCode().equals(code)){
				return carce;
			}
		}
		return null;
	}
	
	/**
	 * 是否存在枚举成员
	 * @param code
	 * @return true or false
	 */
	public static boolean isExists(String code){
		for(CollectionApplyResultCodeEnum carce : values()){
			if(carce.getCode().equals(code)){
				return true;
			}
		}
		return false;
	}
		
}
