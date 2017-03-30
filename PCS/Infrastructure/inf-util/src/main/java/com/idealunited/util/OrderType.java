/**
 * 
 */
package com.idealunited.util;

/**
 * @author NEW
 *
 */
public enum OrderType {

	WITHDRAW(0,"提现"),
	PAY2ACCT(1,"付款到账户"),
	BATCHPAY2ACCT(2,"批付到账户"),
	PAY2BANK(3,"付款到银行"),
	BATCHPAY2BANK(4,"批付到银行"),
	FUNDADJUSTMENT(5,"资金调拨"),
	INTERNAREMIT(7,"国际汇款");

	private int value;
	
	private String desc;
	
	OrderType(int value,String desc){
		this.value = value;
		this.desc = desc;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDesc() {
		return desc;
	}

	public static OrderType fromOrderTypeValue( int value )
	{
		OrderType result = null;
		for ( OrderType type : values( ) )
		{
			if ( type.getValue( ) == value )
			{
				result = type;
				break;
			}
		}

		return result;
	}
}