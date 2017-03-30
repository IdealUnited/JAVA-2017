/**
 * 
 */
package com.idealunited.inf.enums;

/**
 * @author chaoyue
 *
 */
public enum SerCode {
	
	// 通用
	HEART_CHECK("000000","心跳检测"),
	
	TXNCORE_CREATE_SKU("103001","新建SKU"),
	TXNCORE_UPDATE_SKU("103002","更新SKU"),
	TXNCORE_AUDIT_SKU("103003","批量审核SKU"),
	TXNCORE_QUERY_SKU("103004","查询SKU"),
	
	TXNCORE_CREATE_PRODUCT("103005","批量新建产品信息"),
	TXNCORE_UPDATE_PRODUCT("103006","批量更新产品信息"),
	TXNCORE_AUDIT_PRODUCT("103007","批量审核产品信息"),
	TXNCORE_QUERY_PRODUCT("103008","查询产品信息"),
	
	TXNCORE_CREATE_ORDER("103009","批量创建总订单"),
	TXNCORE_UPDATE_ORDER("103010","批量更新总订单"),
	TXNCORE_QUERY_ORDER("103011","查询总订单"),
	
	TXNCORE_CREATE_ORDER_ITEM("103012","批量创建明细订单"),
	TXNCORE_UPDATE_ORDER_ITEM("103013","批量更新明细订单"),
	TXNCORE_QUERY_ORDER_ITEM("103014","查询明细订单"),
	
	TXNCORE_QUERY_ORDER_INFO("103015","查询订单信息"),
	
	TXNCORE_PRODUCT_TYPE_CREATE("103016","创建产品类别"),
	TXNCORE_PRODUCT_TYPE_UPDATE("103017","更新产品类别"),
	TXNCORE_PRODUCT_TYPE_QUERY("103018","查询产品类别"),
	
	TXNCORE_REPOSITORY_CREATE("103019","创建仓库类别"),
	TXNCORE_REPOSITORY_QUERY("103020","查询仓库类别"),
	
	TXNCORE_QUERY_CHANNEL_ORDER("103021","查询渠道总订单"),
	
	TXNCORE_QUERY_CHANNEL_ITEM_ORDER("103022","查询渠道明细订单"),
	
	TXNCORE_CREATE_CHANNEL("103023","创建渠道"),
	TXNCORE_UPDATE_CHANNEL("103024","更新渠道"),
	TXNCORE_QUERY_CHANNEL("103025","查询渠道"),
	
	TXNCORE_CREATE_CHANNEL_ITEM("103026","创建渠道明细"),
	TXNCORE_UPDATE_CHANNEL_ITEM("103027","更新渠道明细"),
	TXNCORE_QUERY_CHANNEL_ITEM("103028","查询渠道明细"),
	
	TXNCORE_CREATE_QUOTATION("103029","创建报价单"),
	TXNCORE_UPDATE_QUOTATION("103030","更新报价单"),
	TXNCORE_QUERY_QUOTATION("103031","查询报价单"),
	
	TXNCORE_QUERY_COUNTRY("103032","查询国家信息"),
	
	TXNCORE_QUERY_CURRENCY_EXCHANGE_RATE("103033","查询汇率信息"),
	TXNCORE_EDIT_CURRENCY_EXCHANGE_RATE("103034","修改汇率信息"),
	
	TXNCORE_EXPRESS_PROCESS("103035","发货处理"),
	
	TXNCORE_LOGISTICS_PROCESS("103036","物流下单处理"),
	TXNCORE_LOGISTICS_MANUAL_PROCESS("103037","物流手工下单处理"),
	TXNCORE_QUERY_ORDER_ITEM_INFO("103038","查询订单明细信息"),
	
	TXNCORE_SCAN_CONFIRM_EXPRESS("103039","扫描确认出货"),
	
	TXNCORE_CREATE_ORDER_BY_MANUAL("103040","手工创建订单"),
	TXNCORE_IMPORT_ORDER_BY_MANUAL("103041","导入订单"),
	
	TXNCORE_DEL_SKU("103042","删除SKU"),
	
	TXNCORE_DEL_PRODUCT("103043","删除产品信息"),
	
	PRE_EXECUTE_ORDER_SYN("10501","总订单同步"),
	PRE_EXECUTE_ORDERITEM_SYN("10502","订单子订单同步"),
	PRE_SUBMIT_FULSHIPPING("10503","上传发货信息"),
	
	PRE_YANWEN_CHANNEL_QUERY("10504","燕文渠道查询"),
	PRE_YANWEN_ORDER_SUBMIT("10505","燕文物流单创建"),
	PRE_YANWEN_ORDER_QUERY("10506","燕文物流单查询"),
	;

	private String code;
	private String desc;
	
	SerCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
