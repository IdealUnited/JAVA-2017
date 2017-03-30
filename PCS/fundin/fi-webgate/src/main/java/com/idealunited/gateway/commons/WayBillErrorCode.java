/**
 *  File: WayBillErrorCode.java
 *  Date: 2016年4月28日
 *  Author: LIULIANG 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description: 运单校验错误代码定义及描述
 *
 */
package com.idealunited.gateway.commons;

public class WayBillErrorCode {
	
	public final static String WAYBILL_ADD_REMARK_MAX_LENGTH_CODE = "0001";
	public final static String WAYBILL_ADD_REMARK_MAX_LENGTH_MSG = "安全信息中的扩展字段长度不能大于256";
	
	public final static String WAYBILL_ADD_PLATFORMID_MAX_LENGTH_CODE = "0002";
	public final static String WAYBILL_ADD_PLATFORMID_MAX_LENGTH_MSG = "商户所属平台商编号长度不能大于32";
	
	public final static String WAYBILL_ADD_VERSION_EMPTY_CODE = "0003";
	public final static String WAYBILL_ADD_VERSION_EMPTY_MSG = "版本号不能为空";
	
	public final static String WAYBILL_ADD_VERSION_ERROR_CODE = "0004";
	public final static String WAYBILL_ADD_VERSION_ERROR_MSG = "版本号信息不对，当前版本必须为1.0";
	
	public final static String WAYBILL_ADD_SITEID_ERROR_CODE = "0005";
	public final static String WAYBILL_ADD_SITEID_ERROR_MSG = "商户网站域名格式不对";
	
	public final static String WAYBILL_ADD_SITEID_MAX_LENGTH_CODE = "0006";
	public final static String WAYBILL_ADD_SITEID_MAX_LENGTH_MSG = "商户网站域名长度不能大于128";
	
	public final static String WAYBILL_ADD_SITEID_EMPTY_CODE = "0007";
	public final static String WAYBILL_ADD_SITEID_EMPTY_MSG = "商户网站域名不能为空";
	
	public final static String WAYBILL_ADD_PARTNERID_ERROR_CODE = "0008";
	public final static String WAYBILL_ADD_PARTNERID_ERROR_MSG = "商户ID不存在";
	
	public final static String WAYBILL_ADD_PARTNERID_MAX_LENGTH_CODE = "0009";
	public final static String WAYBILL_ADD_PARTNERID_MAX_LENGTH_MSG = "商户ID长度不能大于32";
	
	public final static String WAYBILL_ADD_PARTNERID_EMPTY_CODE = "0010";
	public final static String WAYBILL_ADD_PARTNERID_EMPTY_MSG = "商户ID不能为空";
	
	public final static String WAYBILL_ADD_QUERYORDERID_ERROR_CODE = "0011";
	public final static String WAYBILL_ADD_QUERYORDERID_ERROR_MSG = "商户请求流水号已经存在";
	
	public final static String WAYBILL_ADD_QUERYORDERID_MAX_LENGTH_CODE = "0012";
	public final static String WAYBILL_ADD_QUERYORDERID_MAX_LENGTH_MSG = "商户订单号长度不能大于32";
	
	public final static String WAYBILL_ADD_QUERYORDERID_EMPTY_CODE = "0013";
	public final static String WAYBILL_ADD_QUERYORDERID_EMPTY_MSG = "商户订单号不能为空";
	
	public final static String WAYBILL_ADD_OLDORDERID_MAX_LENGTH_CODE = "0014";
	public final static String WAYBILL_ADD_OLDORDERID_MAX_LENGTH_MSG = "原订单号长度不能大于32";
	
	public final static String WAYBILL_ADD_OLDORDERID_EMPTY_CODE = "0015";
	public final static String WAYBILL_ADD_OLDORDERID_EMPTY_MSG = "原订单号不能为空";
	
	public final static String WAYBILL_ADD_NEWWAYBILLNO_MAX_LENGTH_CODE = "0016";
	public final static String WAYBILL_ADD_NEWWAYBILLNO_MAX_LENGTH_MSG = "新运单号长度不能大于256";
	
	public final static String WAYBILL_ADD_NEWWAYBILLNO_EMPTY_CODE = "0017";
	public final static String WAYBILL_ADD_NEWWAYBILLNO_EMPTY_MSG = "新运单号不能为空";
	
	public final static String WAYBILL_ADD_WAYBILLNAME_MAX_LENGTH_CODE = "0018";
	public final static String WAYBILL_ADD_WAYBILLNAME_MAX_LENGTH_MSG = "快递公司名称长度不能大于64";
	
	public final static String WAYBILL_ADD_WAYBILLNAME_EMPTY_CODE = "0019";
	public final static String WAYBILL_ADD_WAYBILLNAME_EMPTY_MSG = "快递公司名称不能为空";
	
	public final static String WAYBILL_ADD_PARTNERID_STATUSEXP_CODE = "0020";
	public final static String WAYBILL_ADD_PARTNERID_STATUSEXP_MSG = "商户状态异常";
	
	public final static String WAYBILL_ADD_PARTNERID_ACCT_FROZEN_CODE = "0021";
	public final static String WAYBILL_ADD_PARTNERID_ACCT_FROZEN_MSG = "商户账户已被冻结，请联系客户人员";
	
	public final static String WAYBILL_ADD_PARTNERID_ACCT_ALLOWOUT_CODE= "0022";
	public final static String WAYBILL_ADD_PARTNERID_ACCT_ALLOWOUT_MSG = "商户账户止出，请联系客户人员";
	
	public final static String WAYBILL_ADD_CHARSET_EMPTY_CODE = "0023";
	public final static String WAYBILL_ADD_CHARSET_EMPTY_MSG = "编码方式不能为空";

	public final static String WAYBILL_ADD_CHARSET_ERROR_CODE = "0024";
	public final static String WAYBILL_ADD_CHARSET_ERROR_MSG = "编码方式错误";
	
	public final static String WAYBILL_ADD_SIGNTYPE_EMPTY_CODE = "0025";
	public final static String WAYBILL_ADD_SIGNTYPE_EMPTY_MSG = "签名类型不能为空";
	
	public final static String WAYBILL_ADD_SIGNTYPE_ERROR_CODE = "0026";
	public final static String WAYBILL_ADD_SIGNTYPE_ERROR_MSG = "签名类型错误";
	
	public final static String WAYBILL_ADD_SIGNMSG_EMPTY_CODE = "0027";
	public final static String WAYBILL_ADD_SIGNMSG_EMPTY_MSG = "签名字符串不能为空";
	
	public final static String WAYBILL_ADD_SIGNMSG_MAX_LENGTH_CODE = "0028";
	public final static String WAYBILL_ADD_SIGNMSG_MAX_LENGTH_MSG = "签名字符串不能大于512";
	
	public final static String WAYBILL_ADD_SIGNMSG_ERROR_CODE = "0029";
	public final static String WAYBILL_ADD_SIGNMSG_ERROR_MSG = "签名字符串验签错误";	

	public final static String WAYBILL_ADD_STATUS_TTRDETYPE_ERROR_CODE = "0030";
	public final static String WAYBILL_ADD_STATUS_TTRDETYPE_ERROR_MSG = "交易状态或支付类型错误";	

	public final static String WAYBILL_ADD_SITEID_NOT_AUTHORIZED_CODE = "0031";
	public final static String WAYBILL_ADD_SITEID_NOT_AUTHORIZED_MSG = "未授权的域名";	
	
	public final static String WAYBILL_ADD_RESERVE1_MAX_LENGTH_CODE = "0032";
	public final static String WAYBILL_ADD_RESERVE1_MAX_LENGTH_MSG = "保留字段1长度不能大于256";
	
	public final static String WAYBILL_ADD_RESERVE2_MAX_LENGTH_CODE = "0033";
	public final static String WAYBILL_ADD_RESERVE2_MAX_LENGTH_MSG = "保留字段2长度不能大于256";
	
	public final static String WAYBILL_ADD_RESERVE3_MAX_LENGTH_CODE = "0034";
	public final static String WAYBILL_ADD_RESERVE3_MAX_LENGTH_MSG = "保留字段3长度不能大于256";
	
	public final static String WAYBILL_ADD_RESERVE4_MAX_LENGTH_CODE = "0035";
	public final static String WAYBILL_ADD_RESERVE4_MAX_LENGTH_MSG = "保留字段4长度不能大于256";
	
	public final static String WAYBILL_ADD_RESERVE5_MAX_LENGTH_CODE = "0036";
	public final static String WAYBILL_ADD_RESERVE5_MAX_LENGTH_MSG = "保留字段5长度不能大于256";
	
	public final static String WAYBILL_ADD_OLDORDERID_NOT_FIND_EMPTY_CODE = "0037";
	public final static String WAYBILL_ADD_OLDORDERID_NOT_FIND_EMPTY_MSG = "订单不存在";
	
	public final static String WAYBILL_ADD_PLATFORMID_EMPTY_CODE = "0038";
	public final static String WAYBILL_ADD_PLATFORMID_EMPTY_MSG = "商户所属平台商编号不能为空";
	
}
