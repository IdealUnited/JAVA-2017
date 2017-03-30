/**
 * 
 */
package com.idealunited.fi.commons;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.idealunited.util.PinYinUtils;


/**
 * @author Steven Lee
 *
 */
public enum BankCodeEnum {

	CMB("","CMB","招商银行"),	
	CMBC("","CMBC","中国民生银行"),	
	GS("","GS","高盛"),
	HXBC("","HXBC","华夏银行"),
	BOC("","BOC","中国银行"),
	ABC("","ABC","中国农业银行"),
	BOCOM("","BOCOM","交通银行"),
	CIB("","CIB","兴业银行"),
	SPDB("","SPDB","浦东发展银行"),
	CITIC("","CITIC","中信银行"),
	CEB("","CEB","光大银行"),
	NBCB("","NBCB","宁波银行"),
	JZBC("","JZBC","锦州银行"),
	BEA("","BEA","东亚银行"),
	BSB("","BSB","包头市商业银行"),
	CDB("","CDB","承德银行"),
	CDRCB("","CDRCB","成都农村商业银行"),
	CRCB("","CRCB","重庆农村商业银行"),
	CQB("","CQB","重庆银行"),
	DLB("","DLB","大连银行"),
	DYCCB("","DYCCB","东营市商业银行"),
	ORBANK("","ORBANK","鄂尔多斯银行"),
	FJNXB("","FJNXB","福建省农村信用社"),
	GYB("","GYB","贵阳银行"),
	GRCB("","GRCB","广州农村商业银行"),
	WHSHB("","WHSHB","威海市商业银行"),
	URMQCCB("","URMQCCB","乌鲁木齐市商业银行"),
	GCB("","GCB","广州银行"),
	HEBB("","HEBB","哈尔滨银行"),
	HNNXB("","HNNXB","湖南省农村信用社"),
	HSB("","HSB","徽商银行"),
	CSRCB("","CSRCB","常熟农村商业银行"),
	JJCCB("","JJCCB","九江银行"),
	DAQINGB("","DAQINGB","龙江银行"),
	QHB("","QHB","青海银行"),
	SRB("","SRB","上饶银行"),
	SDEB("","SDEB","佛山顺德农村商业银行"),
	TZCB("","TZCB","台州市商业银行"),
	WZCB("","WZCB","温州银行"),
	HZCB("","HZCB","杭州银行"),
	CSCB("","CSCB","长沙银行"),
	BOS("","BOS","上海银行"),
	YZB("","YZB","鄞州农村合作银行"),
	CZCB("","CZCB","浙江稠州商业银行"),
	MTBANK("","MTBANK","浙江民泰商业银行"),
	NJCB("","NJCB","南京银行"),
	JSB("","JSB","江苏银行"),
	BHB("","BHB","河北银行"),
	LZB("","LZB","兰州银行"),
	NCB("","NCB","南昌银行"),
	QLBANK("","QLBANK","齐鲁银行"),
	YDRCB("","YDRCB","临汾市尧都区农村信用社"),
	WFCCB("","WFCCB","潍坊银行"),
	WJRCB("","WJRCB","吴江农商行"),
	WRCB("","WRCB","无锡农村商业银行"),
	YCCB("","YCCB","宜昌市商业银行"),
	ZJTLCB("","ZJTLCB","浙江泰隆商业银行"),
	JRCB("","JRCB","江阴农村商业银行"),
	GDB("","GDB","广发银行"),
	ICBC("","ICBC","中国工商银行"),
	CCB("","CCB","中国建设银行"),
	PSBC("","PSBC","中国邮政储蓄银行"),
	PAB("","PAB","平安银行"),
	BOBJ("","BOBJ","北京银行"),
	SRCB("","SRCB","上海农村商业银行"),
	TCCB("","TCCB","天津银行"),
	UMPAY  ("","UMPAY","联动优势"),
	CUCCPAY("","CUCCPAY","联通沃"),
	UNOINPAY("","UNOINPAY","银联"),
	QIWI("","QIWI","外币PPRO-QIWI通道")

	;
	private String value;		
	private String code;
	private String name;

	private BankCodeEnum(String value,String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static boolean isExists(String name) {
		for (BankCodeEnum item : BankCodeEnum.values()) {
			if (item.getCode().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static String  getCodeByName(String name) {
		for (BankCodeEnum item : BankCodeEnum.values()) {
			if (name.contains(item.getName()) || item.getName().contains(name)) {
				return item.getCode();
			}
		} 
		return "";
	}

	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static List< BankCodeEnum > getSortedBankList( )
	{
		BankCodeEnum[ ] bankArray = values( );
		Arrays.sort( bankArray, new Comparator< BankCodeEnum >( ) {
			@Override
			public int compare( BankCodeEnum bankCode1, BankCodeEnum bankCode2 )
			{
				return PinYinUtils.convertToPinYinString( bankCode1.getName( ) ).compareTo(
						PinYinUtils.convertToPinYinString( bankCode2.getName( ) ) );
			}
		} );

		return Arrays.asList( bankArray );
	}
}