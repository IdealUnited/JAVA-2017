package com.idealunited.fi.commons;

/**
 * 
 * @author hhj
 *
 */
public enum TradeStatusEnum {
	  /**交易状态
	   * '0:未付款;1:交易关闭;2:已付款;3:交易完成（含退款）;4:交易成功;5:交易失败 6支付成功待发货; 7 发货审核中;8 发货审核失败;10 冻结;11:已出款;12:拒付 ';
	   * **/ 
	NONPAYMENT("0","未付款","未付款"),
	/*TRADEOFF("1","交易关闭","交易关闭"),
	PAYMENTOVER("2","已付款","已付款"),*/
	TRADEOVER("3","交易完成(含退款)","退款"),
	TRADESUCCESS("4","交易成功","交易成功"),
	TRADEFAIL("5","交易失败","交易失败"),
	PAYMEN_SUCCESS_BE_SHIPPED ("6","支付成功待发货","支付成功待发货"),
	SHIPPENDING("7","发货审核中","发货审核中"),
	SHIPPENDFAILURE ("8","发货审核失败","发货审核失败"),
	FREEZEUP("10","冻结","冻结"),
	OUTOFMONEY("11","已出款","已出款"),
	REFUSEPAYMENT("12","拒付","拒付");
	
	public String getDescView() {
		return descView;
	}

	private final String code;
    private final String desc;
    private final String descView;
    
    /**
     * 私有构造方法
     * @param code
     * @param description
     */
    private TradeStatusEnum(String code, String desc,String descView) {
        this.code = code;
        this.desc = desc;
        this.descView = descView ;
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
    public String getDesc() {
        return desc;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     * @param code
     * @return
     */
    public static TradeStatusEnum getByCode(String code) {
        for (TradeStatusEnum tradeStatusEnum : values()) {
            if (tradeStatusEnum.getCode().equals(code)) {
                return tradeStatusEnum;
            }
        }
        return null;
    }

}
