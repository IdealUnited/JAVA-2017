package com.idealunited.fi.commons;

public enum FundoutOrderTradeStatusEnum {
	  /**订单状态
100：初始状态
101：处理中
102：申请失败
111：处理成功
112：处理失败
113：已退票
	   * **/ 
	/*INITIAL("100","初始状态","初始状态"),
     PAYMEN_SUCCESS_BE_SHIPPED ("113","已退票","已退票"),
	PAYMENTOVER("102","申请失败","请失败"),*/
	PROCESSING("101","处理中","处理中"),
	TRADESUCCESS("111","处理成功","交易成功"),
	TRADEFAIL("112","处理失败","交易失败");
	

	
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
  private FundoutOrderTradeStatusEnum(String code, String desc,String descView) {
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
  public static FundoutOrderTradeStatusEnum getByCode(String code) {
      for (FundoutOrderTradeStatusEnum tradeStatusEnum : values()) {
          if (tradeStatusEnum.getCode().equals(code)) {
              return tradeStatusEnum;
          }
      }
      return null;
  }

}
