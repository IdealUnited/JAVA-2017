package com.idealunited.fi.commons;

import org.apache.commons.lang.StringUtils;

/**
 * 商户订单通知补发---查询类型
 */
public enum TradeOrderSearchTypeEnum {

	MERCHANT_ORDER_NO( 1, "商户订单号" ), 
	TRADE_ORDER_NO( 2, "网关订单流水号" ), 
	PAYMENT_ORDER_NO( 3, "支付订单流水号" ), 
	CHANNEL_ORDER_NO( 4, "渠道订单流水号" );

	private final int code;
	private final String description;

	private TradeOrderSearchTypeEnum( int code, String description )
	{
		this.code = code;
		this.description = description;
	}

	public int getCode( )
	{
		return this.code;
	}

	public String getDescription( )
	{
		return this.description;
	}

	public static TradeOrderSearchTypeEnum fromSearchType( final int searchType )
	{
		TradeOrderSearchTypeEnum result = null;
		for ( TradeOrderSearchTypeEnum search : values( ) )
		{
			if ( search.getCode( ) == searchType )
			{
				result = search;
				break;
			}
		}

		return result;
	}

	public static TradeOrderSearchTypeEnum fromSearchType( String searchType )
	{
		searchType = StringUtils.trimToEmpty( searchType );
		if ( StringUtils.isEmpty( searchType ) || !StringUtils.isNumeric( searchType ) )
		{
			return null;
		}

		return fromSearchType( Integer.parseInt( searchType ) );
	}
}