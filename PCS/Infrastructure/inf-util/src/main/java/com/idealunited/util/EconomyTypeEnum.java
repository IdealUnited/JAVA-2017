/**
 * 
 */
package com.idealunited.util;

/**
 * 经济类型enum
 * @author snn
 *
 */
public enum EconomyTypeEnum {
	NZ("100","内资"),
	GYQZ("110","国有全资"),
	JTQZ("120","集体全资"),
	GFHZ("130","股份合作"),
	LY("140","联营"),
	GYLY("141","国有联营"),
	JTLY("142","集体联营"),
	GYYJTLY("143","国有与集体联营"),
	QTLY("149","其他联营"),
	YXZ("150","有限责任(公司)"),
	GYDZ("151","国有独资(公司)"),
	QTYXZR("159","其他有限责任(公司)"),
	GFYX("160","股份有限(公司)"),
	SY("170","私有"),
	SYDZ("171","私有独资"),
	SYHH("172","私有合伙"),
	SYYXZR("173","私营有限责任(公司)"),
	SYGFYX("174","私营股份有限(公司)"),
	GTJY("175","个体经营"),
	QTSY("179","其他私有"),
	QTNZ("190","其他内资"),
	GATTZ("200"," 港澳台投资"),
	NDHGATHZZ("210","内地和港澳台合资"),
	NDHGATHZI("220","内地和港澳台合作"),
	GATDZ("230","港澳台独资"),
	GATTZGFYX("240","港澳台投资股份有限(公司)"),
	QTGATTZ("290","其他港澳台投资"),
	GWTZ("300","国外投资"),
	ZWHZI("310","中外合资"),
	ZWHZZ("320","中外合作"),
	WZ("330","外资"),
	GWTZGFYX("340","国外投资股份有限(公司)"),
	QTGWTZ("390","其他国外投资"),
	JWJG("400","境外机构"),
	QT("900","其他");

	private String code;
	private String desc;

	private EconomyTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static boolean isExists(String code) {
		for (EconomyTypeEnum item : EconomyTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public static String getCurrencyNameByCode(String code) {
		for (EconomyTypeEnum item : EconomyTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getDesc();
			}
		}
		return null;
	}
	
	public static EconomyTypeEnum getCurrencyByCode(String code) {
		for (EconomyTypeEnum item : EconomyTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
