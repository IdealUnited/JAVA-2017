/**
 * 
 */
package com.idealunited.fi.exception;

/**
 * @Description gateway异常代码枚举
 * @project 	gateway-api
 * @file 		ExceptionCodeEnum.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 ttf Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-7-21		Elx.OuYang		Create
 */
public enum ExceptionCodeEnum {

	
	/** 公共异常错误 0开头  **/
	UN_KNOWN_EXCEPTION("0000", "未知异常"),
	ILLEGAL_PARAMETER("0001", "参数错误"),
	DATETIME_FORMAT_ERROR("0002", "日期格式转换异常"),
	QUERY_COUNT_DB_ERROR("0003","数据库返回结果异常"),
	QUERY_QUERY_DB_ERROR("0004","数据库查询异常"),
	SYSTEM_EXCEPTION("0005", "系统调整中，请稍候"),
	UN_KONWN_HOST_EXCEPTION("0006", "未知主机异常"),
	SOCKET_TIME_OUT_EXCEPTION("0007", "连接超时异常"),
	HTTP_EXCEPTION("0008", "通讯异常"),
	PAY_LOCK_FAIL("0009", "支付可能已经成功，请查询交易记录或联系客服"),
	//运单出错信息
	WAYBILL_STATUS_ERROR("0010","订单状态必须处于'支付成功待发货或发货审核失败'"),
	
	/** 交易异常错误 1开头 */
	TRANSACTION_ERROR("1000", "交易异常"),
	REQUEST_PARAM_ERROR("1001", "请求参数错误"),
	PARTNER_IS_NOT_EXIST("1002","合作伙伴ID不存在"),
	ORDER_NOT_EXIST("1003", "订单不存在"),
	TRANSACTION_SUCCESS("1004", "交易成功"),
	TRANSACTION_FAIL("1005", "交易失败"),
	TRANSACTION_DUPLICATE("1006", "订单已支付成功"),
	ORDER_DOUBLED("1007","订单重复"),
	VALIDATE_SIGN_FAIL("1008","验签失败"),
	TRANSACTION_FINISH("1009","交易已完成"),
	PROTOCOL_LENGTH_ERROR("1010","参数长度错误"),
	VALIDATE_PROTOCOL_FAIL("1011","校验协议失败"),
	PROTOCOL_PARAMTYPE_ERROR("1012","接口参数类型错误"),
	ORDERAMOUNT_ERROR("1013","订单金额错误"),
	ORDER_UNPAID("1014","订单未支付"),
	CREATE_APP_NOFITY_INFO_ERROR("1015","创建应用通知信息失败"),
	PARTNER_PARAM_ERROR("1016","商户提交参数错误"),
	QUERY_MEMBER_ERROR("1017","查询会员信息出错"),
	FREEZE_AMOUNT_ERROR("1018","冻结会员账户出错"),
	ORDER_IS_COMPLETED_OR_SUCCESS("1019","订单已处理，不能重复提交 "),//add for mole_zou 2010.10.25
	CARD_ACCT_TYPE_NOT_EXIST("1020", "点卡账户类型不存在"),
	TRADE_TYPE_ERROR("1021", "错误的交易类型"),
	TRADE_MUTUAL_ERROR("1022", "交易双方不能相同"),
	TRADE_DATA_ERROR("1023", "交易报文不合法（包括非空字符，数字字符等）"),
	
	//收单请求异常--huhb 04-17
	WRONG_VERSION("1024", "请求服务版本号非法"),
	VALIDATE_SIGN_EXCEPTION("1025", "验签异常"),
	PARAM_CHECH_ERROR("1026", "订单重复，但数据不一致"),
	DETAILS_DUPLICATE_ERROR("1027", "提交订单明细内存在重复"),
	ORDER_AMOUNT_ERROR("1028", "订单金额错误"),
	TRANS_MUTUAL_ERROR("1029", "订单交易双方错误"),
	ACCT_NO_EXSITS("1030", "账户不存在"),
	PARTNER_ALLOW_IN("1031", "商户账户止入"),
	PARTNER_TRANSFER_IN("1032", "商户账户不允许转账入"),
	ACCT_ALLOW_OUT("1033", "账户止出"),
	ACCT_ALLOW_IN("1034", "账户止入"),
	BUYER_NON_EXSITS("1035", "买家账户不存在"),
	NON_ACCT_PAY("10360", "商户未设定余额支付"),
	NON_BANK_PAY("10361", "商户未设定网银支付"),
	NON_ECARD_PAY("10362", "商户未设定易卡支付"),
	NON_CONCARD_PAY("10363", "商户未设定消费卡支付"),
	NON_CONCARD_ORGCODE("10364", "消费卡支付时未设定orgCode"),
	CONCARD_ORGCODE_ERROR("10365", "消费卡的orgCode填写错误"),
	PAY_TYPE_ERROR("1037", "支付方式验证出错"),
	DIRECT_FLAG_ERROR("1038", "商户没有直连权限"),
	ORGCODE_ERROR("1039", "机构渠道设置错误"),
	DETAIL_MAX_ERROR("1040", "订单明细数目过大"),
	PARTNER_ALLOW_OUT("1041", "商户账户止出"),
	PARTNER_TRANSFER_OUT("1042", "商户账户不允许转账出"),
	PARTNER_FROZEN("1043", "商户账户冻结"),
	BUYER_MARK_ERROR("1044", "请输入正确的手机号或 Email地址"),
	FAILTIME_ERROR("1045", "订单失效时间错误"),
	ORDER_TIME_ERROR("1046", "订单提交时间或失效时间错误"),
	BUYER_SELLER_ERROR("1047", "买家卖家设置不正确（不能相同）"),
	SERIAL_ID_ERROR("1048", "商户重复发起请求"),
	PART_ORDER_DUPLICATE("1049", "提交订单部分存在"),
	DIRECT_SET_ERROR("1050", "直连设置错误（付款方式必须是网银支付）"),
	DIRECT_CHANNEL_ERROR("1051", "亲爱的用户，该资金渠道即将开通，敬请期待"),
	PAYTYPE_ERROR("1052", "支付方式设置错误"),
	BUYER_IS_INVALID("1053", "直连下买家设置不正确"),
	
	
	
	/**  订单明细部分  */
	PARAM_NULL_EXCEPTION("1054", "报文参数错误，请检查是否存在空值"),
	PARTNER_ORDER_ID_ERROR("1055", "商户订单号长度违约"),
	PARTNER_ORDER_AMOUNT_ERROR("1056", "订单明细金额长度违约"),
	PARTNER_GOODS_NAME_ERROR("1057", "商品名称长度违约"),
	PARTNER_GOODS_COUNT_ERROR("1058", "商品数量长度违约"),
	PARTNER_DISP_NAME_ERROR("1059", "商户显示名长度违约"),
	
	/** 易卡部分 */
	ECARD_RATE_IS_NULL("1060", "易卡费率为空，暂时不能付款"),
	ECARD_RATE_CONVERT_ERROR("1061", "易卡费率转换错误"),
	ECARD_CHARGE_ERROR("1062", "易卡充值失败，请稍候再试"),
	
	//持卡人信息判断
	CARDHOLDER_ACCT_IS_NULL("1063","持卡人账户名为空"),
	CARDHOLDER_NAME_IS_NULL("1064","持卡人姓户名为空"),
	CARDHOLDER_CERT_TYPE_ERROR("1065","持卡人证件类型不正确"),
	CARDHOLDER_CERT_NO_ERROR("1066","持卡人证件号"),
	CARDHOLDER_CARD_NO_ERROR("1067","持卡人卡号不正确"),
	CARDHOLDER_MOBILE_ERROR("1068","持卡人手机号不正确"),
	
	//-- bsp start
	WRONG_TRADE_TYPE("1101", "错误的交易类型"),
	NON_PLATFORM("1102", "平台商不能为空"),
	WRONG_PLATFORM("1103", "错误的平台商"),
	NON_BUYER("1104", "买家标识不能为空"),
	WRONG_TRANS_ACCOUNT("1105", "交易方设置错误"),
	WRONG_BUYER_STATUS("1106", "买家状态异常，不能进行交易"),
	SELLER_NON_EXSITS("1107", "卖家不存在"),
	WRONG_SELLER_STATUS("1108", "卖家状态异常，不能进行交易"),
	PLATFORM_NON_EXSITS("1109", "平台商不存在"),
	WRONG_PLATFORM_STATUS("1110", "平台商状态异常，不能进行交易"),
	WRONG_TRANS_RELATION("1111", "交易商不属于平台商体系"),
	WRONG_PAYMENT_ACCOUNT("1112", "付款账户错误"),
	WRONG_TRANS_OBJ("1113", "交易双方不能相同"),
	//-- bsp end
	
	//-- travel card interactive start
	RESPONSE_EXCEPTION("1201", "内部处理返回异常"),
	PROCESS_DUPLICATE("1202", "销卡重复回调，订单已经处理"),
	PROCESS_DELAY("1203", "暂时不能确定您前次支付情况，请稍后再试或者登录商旅卡系统查询扣款情况，若有不明情况，请联系客服"),
	PROCESS_DELAY_1("1204", "暂时不能确定您前次支付情况，请稍后再试"),
	CONNECTION_ERROR("E000", "与卡系统连接失败，不能支付，请稍后再试"),
	UNKNOW_ERROR("E001", "未知异常"), 
	INVAILD_PARAMETER("E002", "输入参数无效"),
	CONNECTION_ERROR_INFO("E103", "与卡系统连接失败，请稍后再试"),
	/**
	 * 余额不足
	 */
	ACCT_NO_SAVE_ACCOUNT("E003", "余额不足"),
	TRANS_SERIAL_NO_EXIST("E004","订单交易流水号已经存在"),	
	AGENT_CARD_NO_EXIST("E005","商旅卡基本信息不存在或卡被冻结"),	
	ACCT_CARD_NO_EXIST("E006","商旅卡账号信息不存在或账户被冻结 "),
	UPDATE_BALANCE_COUNT_STATUS_ERROR("E007","更新交易累计状态失败 "),	
	UPDATE_BTC_GRANDTOTAL_INFO_ERROR("E008","更新商旅卡累计失败 "),	
	AMOUNT_NOT_ZERO_ERROR("E009","金额不能小于等于0"),	
	PARAMETER_LIST_NOT_EXISTS("E010", "输入参数列表不能为空"),
	CARD_AGENT_PWD__EXISTS("E011", "输入商旅卡卡号或密码错误"),
	CARD_AGENT_STATUS_ERROR("E012","商旅卡号已失效或没有开卡"),
	MERCHANT_NOT_EXISTS("E013","网关商户号不能为空"),
	ORDER_DODE_NOT_EXISTS("E014","商户订单流水号不能为空"),
	TRANS_CODE_NOT_EXISTS("E015","交易类型不能为空"),
	CUR_CODE_NOT_EXISTS("E016","订单币种不能为空"),
	ORDER_AMOUNT_NOT_EXISTS("E017","订单金额不能为空"),
	ORDER_TIME_NOT_EXISTS("E018","订单时间不能为空"),
	ORIGIN_MERCHANT_NOT_EXISTS("E019","订单来源不能为空"),
	CARD_NO_NOT_EXISTS("E020","商旅卡号不能为空"),
	AGENT_PWD_NOT_EXISTS("E021","商旅卡密码不能为空"),
	ORDER_URL_NOT_EXISTS("E022","通知商户URL不能为空"),
	SIGN_DATA_ERROR("E023","验证参数，签名失败"),
	DATA_FORMAT_ERROR("E024","参数格式不正确"),
	MERCHANT_ERROR("E025","商户号不正确"),
	OLD_ORDER_CODE_NOT_EXISTS("E026","原订单号不存在"),
	RETURNS_AMOUNT_NOT_USED("E027","退款金额不能超过订单金额"),
	ORDER_CODE_LENGTH_ERROR("E028","订单长度不对"),

	ORIGIN_MERCHANT_ILLEGAL("E29","订单来源商户号非法"),
	ORDER_CODE_ILLEGAL("E30","订单号不合法"),
	TRANS_MERCHANTId_ILLEGAL("E31","订单来源商户号和前订单来源商户号不相符"),
	ORDER_AMOUNT_ILLEGAL("E032","订单金额格式不正确"),
	INSERT_INTERFACE_ERROR("E033","参数格式不正确"),
	CARD_NO_ILLEGAL("E034","商旅卡号格式不正确"),
	OLD_ORDER_CODE_ILLEGAL("E035","商旅卡号格式不正确"),
	CUR_CODE_ILLEGAL("E036","订单币种格式不正确"),
	UPDATE_BALANCE_SAVE_ERROR("E037", "更新余额失败"),
	//-- travel card interactive end
	
	/** 充值业务的异常2开头 */
	DEPOSITQUERY_ERROR("2001","查询不到充值记录"),
	DEPOSITBANKLINK_ERROR("2002","银行受理失败"),
	DEPOSITSENDINDDATAPROCESS_ERROR("2003","发送银行数据处理错误"),
	HEEPAYDEPOSIT_ERROR("2004","骏网充值返回结果处理失败"),
	DEPOSITBANKLINK_DOUBLE_SUCCESS("2005","银行重复发送成功信息"),
	AMOUNTFORMAT_ERROR("2006","金额格式化错误"),
	DEPOSITCHECKRULENOTFIND_ERROR("2007", "找不到充值约束协议"),
	MEMBERVERIFYNOTFOUND_ERROR("2008", "无法取得用户的认证"),
	MEMBER_FREEZE_ERROR("2009","账户已经冻结"),
	UN_TOPUP_ERROR("2010","不允许为空"),
	MEMBER_PROHIBIT_ERROR("2011","账户为止入状态"),
	NO_TRANSFER_ERROR("2012","不允许转账"),
	AMOUNT_FORMAT_ERROR("2013","金额格式不对"),
	EVERYTIME_BEST_ERROR("2014","超出每次交易最大限额"),
	PERSONAL_BEST_ERROR("2015","超出个人交易最大限额"),
	NO_BANK_ERROR("2016","选择银行为空"),
	EVERDAY_ERROR("2017","每日充值金额超出了限制"),
	EVERDAY_TIME_ERROR("2018","每日充值次数超出了限制"),
	EVERMOUTH_ERROR("2019","每月充值金额超出了限制"),
	EVERMOUTH_TIME_ERROR("2020","每月充值次数超出了限制"),
	
	RESOLVE_ERROR("2021","解析银行返回报文失败"),
	BANK_RETURN_ERROR("2022","银行返回结果出错"),
	GET_DEPOSITPROTOCOL_ERROR("2023","获取充值协议流水号失败"),
	GET_ORGREQUESTHISTORY_ERROR("2024","获取资金机构请求历史失败"),
	ALTER_ORGREQUESTHISTORY_ERROR("2025","更新资金机构请求历史失败"),
	GET_DEPOSITORDER_ERROR("2026","获取充值流水号失败"),
	UPDATE_AMOUNT_ERROR("2027","更新可充退金额失败"),
	UPDATE_DEPOSITPROTOCOL_ERROR("2028","更新充值协议失败"),
	B2B_DEPOSITACCOUNT_ERROR("2029"," B2B充值到企业的账户"),
	B2C_DEPOSITACCOUNT_ERROR("2030"," B2C充值到账户"),
	CREATE_ORGREQUESTHISTORY_ERROR("2031"," 创建资金机构返回历史失败"),
	SELECT_MEBACCOUNT_ERROR("2032"," 查询会员账户余额失败"),
	CREATE_DEPOSITORDER_ERROR("2033"," 创建充值定单失败"),
	SELECT_ACCOUNTINFO_ERROR("2034"," 查询账户信息失败"),
	SELECT_PAYMENTCHANNELITEM_ERROR("2035"," 查找支付渠道内部支付方式列表失败"),
	SELECT_DEPOSITPROTOCOL_ERROR("2036"," 查询充值协议失败"),
	SAVE_USERHIBAT_ERROR("2037"," 保存用户习惯失败"),
	SELECT_BANKMESSAGE_ERROR("2038"," 获取到银行的报文失败"),
	SELECT_DEPOSITPROTOCOLDTO_ERROR("2039"," 得到充值协议DTO失败"),
	SELECT_USERINFO_ERROR("2040"," 查询用户信息出错 "),
	
	MEBCODE_ERROR("2041","会员代码为空 "),
	UPDATE_ORGREQUESTHISTORY_ERROR("2042"," 更新资金机构返回历史失败"),
	PE_ERROR("2043","PE记账失败"),
	VALIDATE_DEALBANKPARAM_ERROR1("2045","无法获取选择的银行服务"),
	VALIDATE_DEALBANKPARAM_ERROR2("2046","金额输入错误,请输入正确金额"),
	VALIDATE_DEALBANKPARAM_ERROR3("2047","验证用户信息异常"),
	VALIDATE_DEALBANKPARAM_ERROR4("2048","获取资金渠道信息失败"),
	VALIDATE_DEALBANKPARAM_ERROR5("2049","验证充值金额失败"),
	VALIDATE_DEALBANKPARAM_ERROR6("2050","验证充值订单信息失败"),
	
	CALL_PAYMENT_ERROR("2051","调用付款失败，但充值成功，本次付款将作为一笔充值进入你的账户."),
	CALL_PAYMENT_DEPOSIT_ERROR("2052","调用充值功能失败."),
	RECONCILE_EXCEL_DOWNLOAD_ERROR("2053","excel下载总记录条数不能超过一万条."),
	RECONCILE_EXCEL_DOWNLOAD_ERROR2("2054","下载对账文件失败，请检查输入格式是否正确."),
	REFUND_EXCEL_DOWNLOAD_ERROR("2055","excel下载总记录条数不能超过一万条."),
	REFUND_EXCEL_DOWNLOAD_ERROR2("2056","下载对账文件失败，请检查输入格式是否正确."),
	
	DEPOSIT_CARD_STATUS_ERROR("2057","此卡不可用,请联系发卡机构."),
	DEPOSIT_CARD_AMOUNT_ERROR("2058","卡内金额不足"),
	DEPOSIT_CARD_INTERFACE_ERROR("2059","渠道卡外部接口异常"),
	DEPOSIT_CARD_BALANCE_ERROR("2060","易卡余额查询失败,请检查卡号密码是否正确."),
	DEPOSIT_CARD_STATUS_CHECK_ERROR("2061","易卡状态查询失败,请检查卡号是否正确."),
	DEPOSIT_CARD_STATUS_CONSUME_ERROR("2062","易卡扣费失败,请检查卡号密码是否正确."),
	VALIDATE_DEALBANKPARAM_ERROR7("2063","账号格式输入有误."),

	/*批量补单异常*/
	DEPOSIT_BATCH_REPAIR_ERROR001("2063","批次入库异常."),
	DEPOSIT_BATCH_REPAIR_ERROR002("2064","导入对账文件入库异常."),
	DEPOSIT_BATCH_REPAIR_ERROR003("2065","申请补单 入库出错."),
	DEPOSIT_BATCH_REPAIR_ERROR004("2066","补单审核 更新状态出错."),
	DEPOSIT_BATCH_REPAIR_ERROR005("2067","批量补单子出错."),
	
	/** 支付链*/
	PAYCHAIN_ACCT_ALLOW_IN("2068", "收款方账户止入"),
	
	/** 结账业务的异常3*/
	MA_UPDATE_BALANCE_EXCEPTION("3001","MA更新余额异常"),
	PE_RUNTIME_EXCEPTION("3002", "算费运行时异常"),
	
	
	/** 支付异常错误 4 开头*/
	PAYMENT_NOT_EXIST("4001", "支付流水不存在"),
	ILLEGAL_REPEAT_PAYMENT("4002", "重复支付"),
	GEN_EBANK_ORDER_EXCEPTION("4003", "无法按银行代码获取选择银行的服务"),
	PAYMENT_AMOUNT_UN_MATCH("4004", "支付金额不匹配"),
	ERROR_PAYMENT_STATUS("4005", "严重异常：错误的支付状态"),
	ERROR_CLOSE_PAYMENT_STATUS("4006", "严重异常：错误的关闭支付状态"),
	ERROR_CONFIRM_PAYMENT_STATUS("4007", "严重异常：错误的确认支付状态"),
	DEPOSIT_PROTOCOL_NOT_EXIST("4008", "充值协议流水不存在"),
	DEPOSIT_PROTOCOL_AMOUNT_UN_MATCH("4009", "充值协议金额不匹配"),
	ERROR_DEPOSIT_PROTOCOL_STATUS("4010", "严重异常：错误的充值协议状态"),
	PAYMENT_DETAIL_NOT_EXIST("4011", "支付明细流水不存在"),
	ILLEGAL_PAYMENT_TYPE("4012", "严重异常：错误的支付类型"),
	ACCOUNT_EXCEPTION("4013", "严重异常：账务异常"),
	ILLEGAL_REFUND("4014", "严重异常：不存在有效的可退款记录"),
	ERROR_REFUND_AMOUNT("4015", "可退款金额大于付款金额"),
	ERROR_DEPOSIT_BACK_AMOUNT("4016", "充退金额大于充值金额"),
	ORDER_AMOUNT_WRONG("4017", "支付订单金额不正确"),
	TRANS_INFO_NON_AGREE("4018", "交易信息前后两次不一致"),
	TRANS_INFO_ERROR("4019", "订单信息不正确"),
	BALANCE_DEAL_EXISTS("4020", "订单付款已经记帐成功"),
	PAYMENT_STATUS_ERROR("4021", "支付订单状态不允许发送支付通知"),
	BANK_PAY_ERROR("4022", "网银支付异常"),
	ACCT_PAY_EXCEPTION("4023", "账户支付异常"),
	BANK_CHANNEL_EXCEPTION("4024", "渠道获取异常，请稍候"),
	BUYER_IN_VALID("4025", "付款方账户未激活，无法进行付款，请激活或更换账户"),
	LOCK_TIMEOUT("4026", "付款处理中，请稍候登录支付查询付款状态，若有疑问，请联系客服"),
	ACCT_IS_DELETED("4027", "账户已经被删除"),
	ACCT_IS_TEMP("4028", "临时账户，请完善账户信息后才能使用"),
	PAYPWD_ERROR("4029", "支付密码错误"),
	ACCT_FROZEN("4030", "支付密码错误次数超限，账户冻结"),
	PAY_ACCT_ERROR("4031", "付款方与商户不能相同"),
	ACCT_BALANCE_ERROR("4032", "账户余额不足"),
	PAYMENT_ERROR("4033", "严重异常：支付业务处理异常"),
	PAYMENT_EXCEPTION("4034", "支付业务处理异常"),
	ECARD_CONSUME_FAIL("4035", "易卡消费失败"),
	PAYPWD_NULL("4036", "支付密码为空"),
	TEMP_CREATE_ERROR("4037", "临时账户创建失败"),
	BUYER_ERROR("4038", "支付订单的联系方式有误"),
	CONCARD_CONSUME_FAIL("4039", "消费卡消费失败"),
	
	/** cnp异常错误 5 开头*/
	CNP_MAP_ORDER_ERROR("8113","cnp映射订单异常"),
	CNP_UPDATE_ORDER_ERROR("8114","cnp更新订单异常"),
	CNP_UPDATE_CNPTRANSACTION_ERROR("8116","cnp更新交易异常"),
	CNP_QUERY_ORDER_ERROR("8115","cnp查询订单异常"),
	CNP_QUERY_TRANSACTION_ERROR("8117","cnp查询交易异常"),
	CNP_QUERY_SETTLEMENT_ERROR("8118","cnp查询结算结果异常"),
	
	
	/** 清算异常错误 6 开头*/
	SETTLE_DEPOSIT_NOT_EXIST("6001", "充值清算流水不存在"),
	DEPOSIT_AMOUNT_UN_MATCH("6002", "充值金额不匹配"),
	ERROR_SETTLE_DEPOSIT_STATUS("6003", "严重异常：错误的充值清算状态"),
	ERROR_PROTOCOL_DEPOSIT("6004", "更新充值清算流水出错"),
	SETTLE_DEPOSIT_CREATE_ERROR("6005", "创建清算流水出错"),
	DEPOSIT_PROTOCOL_CREATE_ERROR("6006", "创建充值协议信息出错"),
	DEPOSIT_SETTLEDEPOSIT_BANKORDERID_ERROR("6007", "银行订单出错"),
	
	
	/** 银行异常错误 8-9开头*/
	BANK_NOT_SUPPORT_EXCEPTION("8001", "当前银行暂停服务"),
	BANKCHANNEL_REPEAT_ERROR("8002","银行渠道重复出错"),
	BANKSTATUS_ERROR("8003","银行状态出错"),
	BANKNAME_REPEAT_ERROR("8004","银行名称重复出错"),
	BANKID_REPEAT_ERROR("8005","银行简称重复出错"),
	BANKINFOID_REPEAT_ERROR("8006","银行基本信息重复出错"),
	BANK_MERCHANTID_NOT_EXIST_ERROR("8007", "银行商户号不存在"),
	BANK_RETURN_SIGNMSG_EXCEPTION("9001", "银行返回信息签名验证没有通过"),
	BANK_RETURN_UN_SUCCESS_EXCEPTION("9002", "银行返回信息支付失败"),
	BANK_CONNECTION_UN_SUCCESS_EXCEPTION("9003", "银行接口连接失败"),
	BANK_LOGIN_UN_SUCCESS_EXCEPTION("9004", "银行接口登陆失败"),
	BANK_PROCESS_UNKNOW_EXCEPTION("9099", "银行处理未知异常"),
	ERROR_UPDATE_PROTOCOLDEPOSIT("9098", "tempCArd"),
	
	
	/** 商旅卡异常错误 81-91开头*/
	WRONG_CARDNO("8101","错误的卡号或者卡密"),
	BALANCE_ERROR("8102","余额不足"),
	ADD_CARD_PAY_EXCEPTION("8103","预付费卡充值错误"),
	PAY_ONLINE_EXCEPTION("8104","创建预付费卡付款信息错误"),
	UPDATE_CHARGEORDER_EXCEPTION("8105","修改预付费卡订单信息错误"),
	UPDATE_TRADEORDERINFO_EXCEPTION("8106","修改预付费卡交易信息错误"),
	SEND_MSGSERVER_EXCEPTION("8107","预付费卡销卡请求发送错误"),
	CARD_PAY_EXCEPTION("8108","预付费卡支付错误"),
	CREATE_CHARGEORDER_EXCEPTION("8109","预付费卡订单创建错误"),
	CHANNELCARD_UNSPORTACCTTYPE("8110","点卡支付,不支持的账户类型"),
	CHANNELCARD_UNSPORTPAYTYPE("8111","点卡支付,不支持的交易类型"),
	CREATE_CHARGE_CARD_INFO_ERROR("8112","创建预付费卡销卡报文信息错误"),
	
	/** 查询接口*/
	ORDER_VERSION_EXCEPTION("90000", "接口版本信息不能为空及长度不能大于4位"),
	ORDER_SERIALID_EXCEPTION("90001", "请求序列号不能为空及长度不能大于32位"),
	ORDER_MODE_EXCEPTION("90002", "查询模式不能为空及长度不能大于1位"),
	ORDER_TYPE_EXCEPTION("90003", "查询类型不能为空及长度不能大于1位"),
	ORDER_PARTNERID_EXCEPTION("90004", "商户ID不能为空及长度不能大于32位"),
	ORDER_CHARSET_EXCEPTION("90005", "编码方式不能为空及长度不能大于1位"),
	ORDER_SIGNTYPE_EXCEPTION("90006", "签名类型不能为空及长度不能大于1位"),
	ORDER_SIGNMSG_EXCEPTION("90007", "签名字符串不能为空及长度不能大于256位"),
	ORDER_ORDERID_EXCEPTION("90008", "选择单笔查询：商户订单号不能为空及长度不能大于32位"),
	ORDER_MODEERROR_EXCEPTION("90009", "查询模式不在范围之内(1-2)"),
	ORDER_TYPEERROR_EXCEPTION("90010", "查询类型不在范围之内(1-2)"),
	QUERY_ORDERNUM_EXCEPTION("0402", "校验业务参数失败 "),
	QUERY_CHARSET_EXCEPTION("90012", "选择编码方式不在范围之内(1)"),
	QUERY_SIGNTYPE_EXCEPTION("90013", "选择报文签名类型不在范围之内(1-2)"),
	ORDER_DZTYPEERROR_EXCEPTION("90014", "选择对账类型不在范围之内(1-2)"),
	ORDER_NUMERROR_EXCEPTION("0401", "基本数据校验失败"),
	ORDER_GATEWAYHISTORY_EXCEPTION("0403", "查询商户请求网关历史记录失败"),
	ORDER_TIMEZH_EXCEPTION("0406", "时间转换类型失败"),
	ORDER_API_EXCEPTION("0404", "查询API失败"),
	ORDER_BACKRESULT_EXCEPTION("0409", "创建网关返回商户结果失败"),
	ORDER_SERIARIDTWO_EXCEPTION("90020", "查询商户请求网关历史记录失败  请求序列号重复"),
	ORDER_REQUESTHISTORY_EXCEPTION("90021", "查询商户请求网关历史记录--失败"),
	ORDER_SUMAPI_EXCEPTION("90022", "查询API-SummaryQueryServiceImpl.querySummary失败"),
	ORDER_REMARK_EXCEPTION("90023", "扩展字段不能为空及长度不能大于256位"),
	QUERYVALIDATE_SIGN_FAIL("0410","查询验签失败"),
	QUERY_ORDER_FAIL("0411","查询订单不存在"),
	
	TIME_PASSDAY("0408","查询时间最大范围只能15天以内"),
	BEGINTIME_PASSDAY("0407","开始时间不能大于结束时间"),
	TIMT_YZ_ERROR("0405","时间验证失败"),
	
	
	// 手机收单新加
	RESOLVE_REQUEST_INFO_EXCEPTION("0801", "解析下单报文异常"),
	RESOLVE_REQUEST_PARAMETER_EXCEPTION("0802", "明细参数不正确"),
	ACQUIRE_BUSINESS_EXCEPTION("0803", "收单业务预期外异常"),
	ACQUIRE_SIGNBUSINESS_EXCEPTION("0804", "下单失败"),
	ACQUIRE_UNQUERY_EXCEPTION("0805", "无法获知下单情况"),
	ACQUIRE_TIME_EXCEPTION("0806", "系统调整中,请稍候"),
	ACQUIRE_CONNECTION_ERROR("0807", "系统连接失败，请稍后再试"),
	
	//分账 解冻 Check
	SHARE_NUMERRORCHECK_EXCEPTION("0101", "基本数据校验失败"),
	SHARE_BUSINSESSCHECK_EXCEPTION("0102", "校验业务参数失败"),
	SHARE_ORDER_NOTEXIST("0103", "分账定单不存在"),
	SHARE_AMOUNTCHECK_EXCEPTION("0104", "金额校验失败"),
	SHARE_AMOUNTFREE_EXCEPTION("0105", "分账订单金额已解冻，无需再解冻"),
	SHARE_PARIDAMOUNTFREE_EXCEPTION("0106", "商户部分金额已解冻，无需再解冻"),
	
	//分账处理
	SHARE_SYSTEM_EXCEPTION("1211", "内部异常-验签字符串获取异常"),
	SHARE_PARAM_ERROR("1212", "必填的字段内容有误"),
	SHARE_PARTNER_ERROR("1213", "分账商户不存在"),
	SHARE_PRODUCT_ERROR("1214", "商户未开通分账产品"),
	SHARE_DETAIL_ERROR("1215", "分账明细不能为空"),
	SHARE_CONTACT_ERROR("1216", "分账参与方不存在"),
	SHARE_AMOUNT_ERROR("1217", "分账金额超过原订单金额"),
	SHARE_OWNER_AMOUNT_ERROR("1218", "付款方实分金额小于手续费"),
	MOTO_ORDER_STATUS_ERROR("1219", "原订单状态不正确，不能进行分账"),
	SHARE_ORDER_STATUS_ERROR("1220", "分账订单状态不正确"),
	SHARE_DETAIL_QUERY_ERROR("1221", "分账明细订单错误"),
	SHARE_RESPONSE_ERROR("1222", "获取通知内容失败"),
	SHARE_RESPONSE_DETAIL_QUERY_ERROR("1223", "获取通知参数明细部失败"),
	SHARE_GEN_SIGN_ERROR("1224", "生成返回通知加签串失败"),
	SHARE_MOTO_ORDER_NOTEXIST("1225", "原始订单不存在"),
	SHARE_DETAIL_AMOUNT_ERROR("1226", "分账明细订单金额格式有误"),
	SHARE_REQ_VERSION_ERROR("1227", "分账请求的服务版本有误"),
	SHARE_REQ_FREEZE_FLG_ERROR("1228", "分账请求的冻结标志有误"),
	SHARE_RULE_CODE_ERROR("1229", "分账规则代码有误"),
	SHARE_FEE_TYPE_ERROR("1230", "分账的手续费承担方式有误"),
	SHARE_CHARSET_ERROR("1231", "分账的编码方式有误"),
	SHARE_SIGNTYPE_ERROR("1232", "分账的加签方式有误"),
	SHARE_AMOUNT_NOT_EQUAL("1233", "分账总金额不等于原订单金额"),
	SHARE_BATCHUNFREE_EXCEPTION("1234", "批量解冻更新异常"),
	SHARE_BATCHUNFREE_BALANCE_ERROR("1235", "批量解冻更新失败"),
	SHARE_BATCHUNFREESTATUS_EXCEPTION("1236", "解冻更新状态异常"),
	SHARE_ACCOUNTING_ERROR("1237", "分账账务处理异常"),
	SHARE_TYPE_UNSUPPORT("1238", "原订单不支持分账"),
	SHARE_ACCT_BALANCE_ERROR("1239", "商户账户余额不足,不能支付手续"),
	SHARE_TYPE_ERROR("1240", "分账交易类型错误"),
	SHARE_RULE_ERROR("1241", "分账规则校验错误"),
	SHARE_AMOUNT_PASSED_ERROR("1242", "分账金额大于可分账金额"),
	SHARE_ORDER_NULL_ERROR("1243", "分账订单不存在"),
	SHARE_BATCH_IN_HAND_ERROR("1244", "订单中存在批次正在处理中"),
	//分帐退款6开头 
	SPLIT_REFUND_VERIFY_EXCEPTION("0601","分帐请求参数异常"),
	SPLIT_ORDER_NOT_EXIST("0602","分帐订单不存在"),
	SPLIT_ORDER_STATE_FAIL("0603","分帐订单状态错误"),
	SPLIT_DETAIL_NOT_EXIST("0604","分帐明细不存在"),
	SPLIT_DETAIL_STATE_FAIL("0605","分帐明细状态错误"),
	SPLIT_PARTNER_VERIFY_FAIL("0606","分帐退款商户验证异常"),
	SPLIT_PAYEE_VERIFY_FAIL("0607","分帐分润方商户验证异常"),
	SPLIT_REFUND_AMOUNT_FAIL("0608","分帐明细退款金额错误"),
	SPLIT_BALANCE_IS_NOT_ENOUGH("0609","分润商户余额不足"),
	SPLIT_REFUND_RECORDE_FAIL("0610","分帐退款记录订单失败"),
	SPLIT_ORDER_UPDATE_FAIL("0611","分账退订单更新失败"),
	SPLIT_ACCOUNTING_FAIL("0612","分账退款记账失败"),
	SPLIT_REFUND_FAIL("0613","分账退款系统异常"),
	REFUND_AMOUNT_FAIL("0614","退款金额大于可退金额"),
	
	SHARE_REFUND_PARAM_ERROR("0615", "必填的数字型字段内容有误"),
	SHARE_REFUND_REQ_VERSION_ERROR("0616", "分账退款API请求的服务版本有误"),
	SHARE_REFUND_PARTNER_ERROR("0617", "分账退款API平台商不存在"),
	SHARE_REFUND_DESTTYPE_ERROR("0618", "分账退款的退款目的地有误"),
	SHARE_REFUND_SIGNTYPE_ERROR("0619", "分账退款的加签方式有误"),
	SHARE_REFUND_CHARSET_ERROR("0620", "分账退款的编码方式有误"),
	SHARE_REFUND_RULE_VALIDATE_ERROR("0621", "分账退款规则明细获取失败"),
	
	
	/**
	 * 神州行卡网关错误信息
	 */
	CHINATRAVEL_ERROR_CODE_01("TY01", "不支持此类型盛大卡"),
	CHINATRAVEL_ERROR_CODE_02("TY02", "不支持的卡类型或面值"),
	CHINATRAVEL_ERROR_CODE_03("TY03", "不支持面值为3元的网易卡"),
	CHINATRAVEL_ERROR_CODE_04("TY04", "产品维护"),
	CHINATRAVEL_ERROR_CODE_05("TY05", "充值卡面额选择错误"),
	CHINATRAVEL_ERROR_CODE_06("TY06", "当前银行代码不可用"),
	CHINATRAVEL_ERROR_CODE_07("TY07", "该产品未开通"),
	CHINATRAVEL_ERROR_CODE_08("TY08", "该卡官方处理失败，可再次提交"),
	CHINATRAVEL_ERROR_CODE_09("TY09", "该卡密提交过于频繁，请您稍后再试"),
	CHINATRAVEL_ERROR_CODE_10("TY10", "该卡为久游矩阵卡"),
	CHINATRAVEL_ERROR_CODE_11("TY11", "该卡为久游神兵传奇专用卡"),
	CHINATRAVEL_ERROR_CODE_12("TY12", "该卡为骏网专用或限制卡"),
	CHINATRAVEL_ERROR_CODE_13("TY13", "该卡为搜狐实体卡"),
	CHINATRAVEL_ERROR_CODE_14("TY14", "该卡为新疆专区卡"),
	CHINATRAVEL_ERROR_CODE_15("TY15", "该卡无法充值，请联系购卡经销商 "),
	CHINATRAVEL_ERROR_CODE_16("TY16", "该卡已被寄存"),
	CHINATRAVEL_ERROR_CODE_17("TY17", "该卡已过有效期"),
	CHINATRAVEL_ERROR_CODE_18("TY18", "该卡已经使用"),
	CHINATRAVEL_ERROR_CODE_19("TY19", "该卡只能充洛克王国业务"),
	CHINATRAVEL_ERROR_CODE_20("TY20", "官方维护，支付通道暂时关闭"),
	CHINATRAVEL_ERROR_CODE_21("TY21", "卡处于锁定状态"),
	CHINATRAVEL_ERROR_CODE_22("TY22", "卡号或密码不正确"),
	CHINATRAVEL_ERROR_CODE_23("TY23", "卡号或密码不正确,卡密不存在或此卡已经使用过"),
	CHINATRAVEL_ERROR_CODE_24("TY24", "卡密不存在或此卡已经使用过"),
	CHINATRAVEL_ERROR_CODE_25("TY25", "卡内余额不足"),
	CHINATRAVEL_ERROR_CODE_26("TY26", "商户订单号重复"),
	CHINATRAVEL_ERROR_CODE_27("TY27", "系统忙,请稍后再试"),
	CHINATRAVEL_ERROR_CODE_28("TY28", "浙江省移动维护"),
	CHINATRAVEL_ERROR_CODE_29("TY29", "验签错误"),
	CHINATRAVEL_ERROR_CODE_30("TY30", "未知错误"),
	CHINATRAVEL_ERROR_CODE_31("TY31", "参数错误"),
	
	/**
	 * 分账中心（2期）
	 */
	SHARINGDETAIL_DUPP("5001", "分账明细重复了"),
	NON_ABLE_SHARING("5002", "没有可再分明细"),
	NON_SHARING_ORDER("5003", "没有找到分账主订单"),
	CONFIG_RULE_ERROR("5004", "配置规则标志暂不支持"),
	SHARING_AMT_NOTENOUGH("5005", "分账金额不够"),
	SHARING_AMT_ERROR("5006", "分账金额不正确（金额没分完）"),
	SHARING_MOTO_STATUS_ERROR("5007", "分账原订单状态不正确"),
	SHARING_NOTICE_ERROR("5008", "分账结束获取通知内容错误"),
	SHARING_DETAIL_ERROR("5009", "规则有误"),
	
	/**
	 * 行业卡消费
	 */
	CONSUME_CHANNEL_ITEM_ERROR("0701", "消费卡渠道配置错误"),
	CONSUME_SUBMIT_TYPE_ERROR("0702", "销卡提交方式错误"),
	
	
	CROSS_PAY_ERROR("9050", "跨境支付设置错误（付款方式必须是跨境支付）"),
	CROSS_EXCHANGE_RATE_ERROR("9051", "跨境支付（商户没有可用汇率）"),
	
	//IVR语音提交
	IVR_ORDER_ERROR("I0001","订单参数失败"),
	IVR_ERROR("I0002","订单提交失败"),
	
	NO_TRANSACTION_RATE("N0001","没有找到汇率"),
	
	NOT_SUPPORT_CURRENCY("N0002","不支持该币种"),
	
	NO_SETTLEMENT_RATE("N0003","没有找到结算汇率"),
	
	NO_FEE_RATE("N0004","没有找到费率"),
	
	PREAUTH_NOT_EXIST("P0001","预授权不存在"),
	PREAUTH_INF_NOT_EXIST("P0002","授权交易信息不完整")
	;
	private final String code;
    private final String description;
    
    /**
     * 私有构造方法
     * @param code
     * @param description
     */
    private ExceptionCodeEnum(String code, String description) {
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
    public static ExceptionCodeEnum getByCode(String code) {
        for (ExceptionCodeEnum exceptionCode : values()) {
            if (exceptionCode.getCode().equals(code)) {
                return exceptionCode;
            }
        }
        return null;
    }
    
    
    /**
     * 通过枚举<code>code</code>获得枚举
     * @param code
     * @return
     */
    public static ExceptionCodeEnum getByDesc(String desc) {
        for (ExceptionCodeEnum exceptionCode : values()) {
            if (exceptionCode.getDescription().contains(desc)) {
                return exceptionCode;
            }
        }
        return null;
    }
}