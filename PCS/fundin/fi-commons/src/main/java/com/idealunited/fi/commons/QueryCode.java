
package com.idealunited.fi.commons;

import java.util.EnumMap;
import java.util.Map;


public enum QueryCode {
	
	
	/**
	 * 商户选择对账类型：支付订单
	 */
	Query_DZType_one(1),
	/**
	 * 商户选择对账类型：退款订单
	 */
	Query_DZType_Two(2),
	/**
	 * 编码方式  1 ：UTF-8
	 */
	Query_Charset_one(1),
	
	/**
	 * 签名类型  1 RSA 方式（推荐） 
	 */
	Query_SignType_one(1),
	
	/**
	 * 签名类型 2：MD5 方式
	 */
	Query_SignType_Two(2),
	
	/**
	 * 商户选择查询模式 1 单笔
	 */
	Query_Mode_One(1),
	
	/**
	 * 商户选择查询模式 2 批量
	 */
	Query_Mode_Two(2),
	
	/**
	 * 商户选择查询类型 1 支付订单
	 */
	Query_Type_One(1),
	
	/**
	 * 商户选择查询类型 1 退款订单
	 */
	Query_Type_Two(2);
	
    
    private int value;

    public int getValue() {
        return value;
    }

    QueryCode(int value) {
        this.value = value;
    }
    public final static Map<QueryCode, String> ORGCODEMAP;

    static {
        ORGCODEMAP = new EnumMap<QueryCode, String>(QueryCode.class);
        ORGCODEMAP.put(QueryCode.Query_Charset_one,  "编码方式 UTF-8");
        ORGCODEMAP.put(QueryCode.Query_SignType_one,  "签名类型  1 RSA");
        ORGCODEMAP.put(QueryCode.Query_SignType_Two,  "签名类型  2 MD5方式");
        ORGCODEMAP.put(QueryCode.Query_Mode_One,  "查询模式单笔");
        ORGCODEMAP.put(QueryCode.Query_Mode_Two,   "查询模式批量");
        ORGCODEMAP.put(QueryCode.Query_Type_One,  "查询类型支付订单");
        ORGCODEMAP.put(QueryCode.Query_Type_Two, "查询类型退款订单");
    }
    /**
     * 跟据value返回枚举对应的key
     * 
     * @param value
     * @return ACCTTYPE
     */
    public static QueryCode getOrderCodeMAPKey(int value) {
        QueryCode tmpKey = null;
        for (QueryCode tmpEnum : QueryCode.values()) {
            if (tmpEnum.value == value) {
                tmpKey = tmpEnum;
                break;
            }
        }

        return tmpKey;
    }
    /**
     * 返回OrderCode对应的描述.
     * @param value int.
     * @return String
     */
    public static String getOrderCodeDesc(final int value) {
        return QueryCode.ORGCODEMAP.get(
            QueryCode.getOrderCodeMAPKey(value));
    }
}
