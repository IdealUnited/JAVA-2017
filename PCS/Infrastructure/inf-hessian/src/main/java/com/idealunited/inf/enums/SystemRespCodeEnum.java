package com.idealunited.inf.enums;


/**
 * 系统返回码映射
 * @author peiyu.yang
 * @since 2015年9月5日17:58:06
 */
public enum SystemRespCodeEnum {
    
	ABC101("3117","The request is missing one or more required fields","缺少参数"
		  ,"101","The request is missing one or more required fields"),
	ABC102("3091","One or more fields in the request contains invalid data.","参数错误"
			,"102","One or more fields in the request contains invalid data."),
	ABC104("3107","authorization number error","授权号错误"
				  ,"104","authorization number error"),
	ABC110("3118","Only a partial amount was approved.","部分金额接受"
						  ,"110","Only a partial amount was approved."),
	ABC150("3091","Invalid Institute Parameter","银行参数错误"
								  ,"150","Accquire bank general system failure."),
	ABC151("3091","Invalid Institute Parameter","银行参数错误"
										  ,"151","The request was received but there was a server timeout. This error does not"),
	ABC152("3091","Invalid Institute Parameter","银行参数错误"
								  ,"152","The request was received, but a service did not finish running in time."),
	ABC200("3108","The authorization request was approved by the issuing bank but declined by CyberSource because it did not pass the Address Verification System (AVS)check","发卡行拒绝"
										  ,"200","The authorization request was approved by the issuing bank but declined by CyberSource because it did not pass the Address Verification System (AVS)check"),
	ABC201("3109","The issuing bank has questions about the request. You do not receive an authorization code programmatically, but you might receive one verbally by calling the processor","发卡行有疑义"
												  ,"201","The issuing bank has questions about the request. "
	+ "You do not receive an authorization code programmatically, but you might receive one verbally by calling the processor"),
	ABC202("3102","Expired card","过期卡","202","Expired card. You might also receive this value if the expiration date you provided"),
	ABC203("3100","Do not honor","交易受限","203","General decline of the card. No other information was provided by the issuing"),
	ABC204("3084","Card balance is insufficient","卡余额不足","204","Insufficient funds in the account."),
	ABC205("3119","Stolen or lost card.","挂失卡","205","Stolen or lost card."),
	ABC207("3091","Invalid Institute Parameter","银行参数错误","207","Issuing bank unavailable."),
	ABC208("3100","Do not honor","交易受限","208","Inactive card or card not authorized for card-not-present transactions."),
	ABC209("3104","Invalid Card Verification Value (CVV)","校验码错误","209","CVN did not match."),
	ABC210("3093","Exceed amount limit","金额超出限制","210","The card has reached the credit limit."),
	ABC211("3104","Invalid Card Verification Value (CVV)","校验码错误 ","211","Invalid CVN."),
	ABC221("3120","The customer matched an entry on the processor’s negative file","命中发卡行黑名单","221","The customer matched an entry on the processor’s negative file."),
	ABC230("3104","Invalid Card Verification Value (CVV)","校验码错误","230","The authorization request was approved by the issuing bank but declined by"),
	ABC231("3094","Restricted Card","受限制卡","231","Invalid account number."),
	ABC232("3097","The card type is not accepted by the payment processor","不支持该卡种","232","The card type is not accepted by the payment processor."),
	ABC233("3092","Invalid Transaction","无效交易","233","General decline by the processor."),
	ABC234("3099","System Busy","其他异常","234","There is a problem with the information in your CyberSource account"),
	ABC235("3004","The amount of order error","订单金额错误","235","The requested capture amount exceeds the originally authorized amount."),
	ABC236("3091","Invalid Institute Parameter","银行参数错误","236","Processor failure."),
	ABC237("3121","The authorization has already been reversed","授权已撤销","237","The authorization has already been reversed"),
	ABC238("3122","The authorization has already been captured","授权已请款","238","The authorization has already been captured"),
	ABC239("3004","The amount of order error","订单金额错误","239","The requested transaction amount must match the previous transaction amount"),
	ABC240("3101","Invalid Card Information","卡信息错误","240","The card type sent is invalid or does not correlate with the credit card number"),
	ABC241("3099","System Busy","其他异常","241","The request ID is invalid."),
	ABC242("3107","authorization number error","授权号错误","242","authorization number error"),
	ABC243("3105","Duplicate Reversal","重复交易","243","The transaction has already been settled or reversed."),
	ABC246("3105","Duplicate Reversal","重复交易","246","The transaction has already been settled or reversed."),
	ABC247("3121","The authorization has already been reversed","授权已撤销","247","You requested a credit for a capture that was previously voided."),
	ABC250("3091","Invalid Institute Parameter","银行参数错误","250","The request was received, but there was a timeout at the payment processor."),
	ABC254("3123","stand-alone credits are not allowed","stand-alone credits are not allowed","254","stand-alone credits are not allowed"),
	ABC480("3100","Do not honor","交易受限","480",""),
	ABC520("3099","System Busy","其他异常","520",""),
	ABC000("3099","System Busy","其他异常","000",""),
	
	BOC01("3091","Invalid Institute Parameter","银行参数错误","01","请与银行联系"),
	BOC03("3091","Invalid Institute Parameter","银行参数错误","03","无效商户"),
	BOC05("3100","Do not honor","交易受限","05","不批准交易"),
	BOC08("3091","Invalid Institute Parameter","银行参数错误","08","请与银行联系"),
	BOC12("3092","Invalid Transaction","无效交易","12","无效交易"),
	BOC13("3093","Invalid Amount","金额无效","13","无效金额"),
	BOC14("3103","Invalid Account","无效账户","14","无效卡号"),
	BOC15("3103","Invalid Account","无效账户","15","无此发卡行"),
	BOC19("3092","Invalid Transaction","无效交易","19","请重做交易"),
	BOC21("3092","Invalid Transaction","无效交易","21","不做任何处理"),
	BOC25("3092","Invalid Transaction","无效交易","25","无此交易"),
	BOC26("3105","The transaction has already been settled or reversed.","重复交易","26","重复交易"),
	BOC28("3092","Invalid Transaction","无效交易","28","交易无法处理"),
	BOC29("3099","System Busy","其他异常","29","文件更新拒绝"),
	BOC30("3091","Invalid Institute Parameter","银行参数错误","30","格式错误"),
	BOC33("3102","Expired card","过期卡","33","过期卡"),
	BOC34("3089","Suspected of Cheating","有作弊嫌疑","34","作弊嫌疑(没收卡)"),
	BOC35("3089","Suspected of Cheating","有作弊嫌疑","35","联系保安(没收卡)"),
	BOC37("3089","Suspected of Cheating","有作弊嫌疑","37","联系卡中心(没收卡)"),
	BOC38("3106","Exceeds PIN Retry","PIN输入超过次数","38","PIN输入超次（没收卡）"),
	BOC39("3094","Restricted Card","受限制卡","39","无此账户"),
	BOC40("3092","Invalid Transaction","无效交易","40","非法功能"),
	BOC41("3119","Stolen or lost card.","挂失卡","41","挂失卡(没收卡)"),
	BOC43("3119","Stolen or lost card.","挂失卡","43","被窃卡(没收卡)"),
	BOC51("3084","Card balance is insufficient","卡余额不足","51","余额不足"),
	BOC54("3102","Expired card","过期卡","54","过期卡"),
	BOC55("3085","Password not Matches","密码错误","55","密码错"),
	BOC56("3101","Invalid Card Information","卡信息错误","56","无此卡记录"),
	BOC57("3092","Invalid Transaction","无效交易","57","非法交易"),
	BOC59("3089","Suspected of Cheating","有作弊嫌疑","59","有作弊嫌疑"),
	BOC60("3110","Refer to card issuer","请联系发卡银行","60","请与银行联系"),
	BOC61("3084","Card balance is insufficient","卡余额不足","61","超出取款限额"),
	BOC62("3094","Restricted Card","受限制卡","62","受限制卡"),
	BOC63("3086","Security Policy Violation","违反保密规定","63","违反安全保密规定"),
	BOC64("3093","Invalid Amount","金额无效","64","无效原金额"),
	BOC65("3088","Exceed Times Limitation","卡号次数超限","64","取款次数超过次数"),
	BOC67("3119","Stolen or lost card.","挂失卡","67","没收卡"),
	BOC75("3106","Exceeds PIN Retry","输入超过次数","75","PIN输入超过次数"),
	BOC77("3099","System Busy","其他异常","77","结算不平"),
	BOC78("3089","Suspected of Cheating","有作弊嫌疑","78","止付卡"),
	BOC79("3089","Suspected of Cheating","有作弊嫌疑","79","非法帐户"),
	BOC80("3092","Invalid Transaction","无效交易","80","交易拒绝"),
	BOC81("3094","Restricted Card","受限制卡","81","卡已作废"),
	BOC84("3099","System Busy","其他异常","84","联网暂断,重做交易"),
	BOC87("3099","System Busy","其他异常","87","PIN密钥同步错"),
	BOC88("3099","System Busy","其他异常","88","MAC密钥同步错"),
	BOC90("3099","System Busy","其他异常","90","主机轧帐稍候工作"),
	BOC91("3095","Transaction Timeout","交易超时","91","交易超时"),
	BOC95("3099","System Busy","其他异常","95","结算不平,上送交易"),
	BOC96("3099","System Busy","其他异常","96","系统异常"),
	BOC97("3099","System Busy","其他异常","97","终端号错误"),
	BOC98("3091","Invalid Institute Parameter","银行参数错误","98","暂与发卡行失去联络"),
	BOC99("3085","Unable to Verify PIN","密码错误","99","PIN格式错"),
	BOCN0("3092","Invalid Transaction","无效交易","N0","不匹配的交易"),
	BOCQ2("3096","Invalid Expiration","有效期错误","Q2","有效期错"),
	BOCSK("3103","Invalid Account","无效账户","SK","无效卡校验"),
	BOCY1("","","","Y1","脱机批准（用于EMV脱机交易时使用的值）"),
	BOCY2("","","","Y2","（保留在EMV脱机交易使用的值）"),
	BOCY3("","","","Y3","无法联机，脱机批准（用于EMV脱机交易使用的值，暂不使用。本规范规定一旦发起了联机请求，就不允许再进行脱机批准流程）"),
	BOCZ1("","","","Z1","请先签到"),
	BOCZ2("3099","System Busy","其他异常","Z2","积分不够"),
	BOCZ3("3099","System Busy","其他异常","Z3","分期期数错"),
	BOCZ4("3099","System Busy","其他异常","Z4","分期计划错"),
	BOCZ6("3092","Invalid Transaction","无效交易","Z6","无效交易币种"),	
	BOCZ7("","","","Z7","上批未结，请先结完上批"),	
	BOCZ8("3097","Unsupported Card Type","不支持该卡种","Z8","不支持该卡种"),	
	BOCZ9("","","","Z9","银联EMV卡完成交易请使用预授权完成通知交易重新上送"),	
	BOCZA("","","","ZA","根据银联规定，完成通知交易不能撤消"),
	BOCZB("","","","ZB","根据银联规定，完成通知交易不能撤消"),
	BOCZC("","","","ZC","请用刷卡方式进行交易"),
	BOC10("3099","System Busy","其他异常","10","前置机拒绝交易"),
	BOCZH("3099","System Busy","其他异常","ZH","交易超时"),	

	
	CRE02("3091","Invalid Institute Parameter","银行参数错误","02","Refer to card issuer"),
	CRE03("3110","Refer to card issuer","请联系发卡银行","03","Invalid merchant"),
	CRE04("3100","Refer to card issuer","交易受限","04","Do not Honour"),
	CRE05("3100","Do not honor","交易受限","05","Do not Honour"),
	CRE06("3092","Invalid Transaction","无效交易","06","Invalid Transaction for Terminal"),
	CRE07("3099","System Busy","其他异常","07","Honour with ID"),
	CRE08("3091","Invalid Institute Parameter","银行参数错误","08","Time-Out"),
	CRE09("3099","System Busy","其他异常","09","No Original"),
	CRE10("3124","Unable to Reverse","不允许撤销","10","Unable to Reverse"),
	CRE11("3118","Only a partial amount was approved.","部分金额接受","11","Partial Approval"),
	CRE12("3094","Restricted Card","受限制卡","12","Invalid transaction card / issuer / acquirer"),
	CRE13("3093","Invalid Amount","金额无效","13","Invalid amount"),
	CRE14("3094","Restricted Card","受限制卡","14","Invalid card number"),
	CRE17("3094","Restricted Card","受限制卡","17","Invalid Capture date (terminal business date)"),
	CRE19("3091","Invalid Institute Parameter","银行参数错误","19","System Error; Re-enter transaction"),					
	CRE20("3125","No From Account","No From Account","20","No From Account"),					
	CRE21("3126","No To Account","No To Account","21","No To Account"),					
	CRE22("3127","No Checking Account","No Checking Account","22","No Checking Account"),					
	CRE23("3128","No Saving Account","No Saving Account","23","No Saving Account"),					
	CRE24("3129","No Credit Account","No Credit Account","24","No Credit Account"),					
	CRE30("3091","Invalid Institute Parameter","银行参数错误","30","Format error"),					
	CRE34("3103","Invalid Account","无效账户","34","Implausible card data"),					
	CRE39("3100","Do not honor","交易受限","39","Transaction Not Allowed"),					
	CRE41("3119","Stolen or lost card.","挂失卡","41","Lost Card, Pickup"),					
	CRE42("3119","Stolen or lost card.","挂失卡","42","Special Pickup"),					
	CRE43("3119","Stolen or lost card.","挂失卡","43","Hot Card, Pickup (if possible)"),					
	CRE44("3119","Stolen or lost card.","挂失卡","44","Pickup Card"),					
	CRE51("3084","Card balance is insufficient","卡余额不足","51","Not sufficient funds"),					
	CRE54("3102","Expired card","过期卡","54","Expired card"),					
	CRE55("3085","Unable to Verify PIN","密码错误","55","Incorrect PIN; Re-enter"),					
	CRE57("3020","Service type is temporary not support","交易拒绝","57","Transaction not permitted on card"),					
	CRE58("3099","System Busy","其他异常","58","Txn Not Permitted On Term"),					
	CRE59("3089","Suspected of Cheating","有作弊嫌疑","59","Suspected Fraud"),					
	CRE61("3093","Exceed amount limit","金额超出限制","61","Exceeds amount limit"),					
	CRE62("3094","Restricted Card","受限制卡","62","Restricted card"),					
	CRE63("3099","System Busy","其他异常","63","MAC Key Error"),					
	CRE65("3088","Exceed Times Limitation","卡号次数超限","65","Exceeds frequency limit"),					
	CRE66("3130","Exceeds Acquirer Limit","Exceeds Acquirer Limit","66","Exceeds Acquirer Limit"),					
	CRE67("3094","Restricted Card","受限制卡","67","Retain Card; no reason specified"),					
	CRE68("3095","Transaction Timeout","交易超时","68","Response received too late.Used to indicate the reason for sending a reversal – an authorisation response was not returned within the time limit"),					
	CRE75("3106","Exceeds PIN Retry","PIN输入超过次数","75","Exceeds PIN Retry"),		
	CRE76("3103","Invalid Account","无效账户","76","Invalid Account"),		
	CRE77("3091","Invalid Institute Parameter","银行参数错误","77","Issuer Does Not Participate In The Service"),		
	CRE78("3099","System Busy","其他异常","78","Function Not Available"),		
	CRE79("3099","System Busy","其他异常","79","Key Validation Error"),		
	CRE80("3099","System Busy","其他异常","80","Approval for Purchase Amount Only"),		
	CRE81("3085","Password not Matches","密码错误","81","Unable to Verify PIN"),		
	CRE82("3104","Invalid Card Verification Value (CVV)","校验码错误","82","Invalid Card Verification Value (CVV)"),		
	CRE83("3004","Invalid order amount","订单金额错误","83","INot declined (Valid for all zero amount transactions)"),		
	CRE84("3099","System Busy","其他异常","84","Invalid Life Cycle of transaction"),		
	CRE85("3099","System Busy","其他异常","85","No Keys To Use"),		
	CRE86("3099","System Busy","其他异常","86","K M E Sync Error"),		
	CRE87("3085","Unable to Verify PIN","密码错误","87","PIN Key Error"),		
	CRE88("3099","System Busy","其他异常","88","MAC sync Error"),		
	CRE89("3086","Security Policy Violation","违反保密规定","89","Security Violation"),		
	CRE91("3103","Invalid Account","无效账户","91","Issuer not available"),		
	CRE92("3103","Invalid Account","无效账户","92","Invalid Issuer"),		
	CRE93("3091","Invalid Institute Parameter","银行参数错误","93","Transaction cannot be completed"),		
	CRE95("3099","System Busy","其他异常","95","Contact Acquirer"),		
	CRE96("3099","System Busy","其他异常","96","System malfunction"),		
	CRE97("3099","System Busy","其他异常","97","No Funds Transfer"),		
	CRE98("3105","Duplicate Reversal","重复交易","98","Duplicate Reversal"),		
	CRE99("3105","Duplicate Reversal","重复交易","99","Duplicate Transaction"),		
	CREN3("3099","System Busy","其他异常","N3","Cash Service Not Available"),		
	CREN4("3099","System Busy","其他异常","N4","Cash Back Request Exceeds Issuer Limit"),		
	CREN7("3104","Invalid Card Verification Value (CVV)","校验码错误","N7","N7 (visa), “Decline CVV2 failure"),		
	CRER0("3099","System Busy","其他异常","R0","Stop Payment Order"),		
	CRER1("3099","System Busy","其他异常","R1","Revocation of Authorisation Order"),		
	CRER3("3099","System Busy","其他异常","R3","Revocation of all Authorisations Order"),		
	CRE1301("1008","Invalid Payment Method","支付方式验证出错","-13","Transaction must include valid 3D secure data"),		
	CRE1201("1002","Invalid Request parameters","请求参数不合法","-12","Card Secure Code has to be present"),		
	CRE1101("3015","The currency is not supported","不支持币种","-11","Currency is not supported for the given merchant"),		
	CRE1001("3099","System Busy","其他异常","-10","Unclassified Error"),		
	CRE901("1002","Invalid Request parameters","请求参数不合法","-9","Parameter is malformed"),		
	CRE801("1034","","签名参数值不匹配","-8","Package Signature is malformed"),		
	CRE701("2106","","链接中断","-7","No Response from the gateway. Connection is broken"),		
	CRE501("3100","Do not honor","交易受限","-5","Transaction has been rejected. Processing has been stopped"),												
	CRE301("3103","Invalid account number","无效账户","-3","Account status was not updated"),												
	CRE201("2024","Account does not exist","账户不存在","-2","Account does not exist"),												
	CRE101("3103","Invalid account number","无效账户","-1","Account already exists"),												
	CRE1("3100","Do not honor","交易受限","1","Transaction has been denied by the gateway"),												
	CRE2("3089","High risk level","高风险交易","2","Transaction has been denied by the gateway due to its fraud high risk"),												
	CRE3("3089","High risk level","高风险交易","3","Transaction has been denied by the gateway due to its AVS high risk"),												
	CRE4("3089","Transaction Timeout","交易超时","4","Transaction has been denied by the gateway due to the interchange timeout"),												
	CRE5("3100","Do not honor","交易受限","5","Transaction has been declined."),												
	CRE7("3099","System Busy","其他异常","7","Redirect URL issued"),																	
	CRE9("3094","Invalid transaction card / issuer / acquirer","受限制卡","9","Transaction has been denied by the gateway due to the LUHN check failure"),
	CRE100("1008","Invalid Payment Method","支付方式验证出错","100","Transaction is 3D enrolled."),																			
	

	KASI1000("9000","System is maintaining","系统维护中","1000","交易处理中"),		
	KASI1001("9000","System is maintaining","系统维护中","1001","系统维护中，暂停交易"),
	KASI1101("1003","Error Occured While verifying Signature","验签异常","1101","MD5校验错"),
	KASI1102("1002","Invalid Request parameters","请求参数不合法","1102","数据字段长度不符合定义"),
	KASI1103("2003","Merchant Not Exists","商户ID不存在","1103","商户未开通"),
	KASI1104("1000","Invalid Parameters","参数异常","1104","接入码错误"),
	KASI1105("3092","Invalid Transaction","无效交易","1105","无结账交易"),
	KASI1106("2003","Merchant Not Exists","商户ID不存在","1106","该商户号不存在"),
	KASI1107("1011","The institute of direct connection has not been activated","直连渠道未开通","1107","交易通道未开通"),
	KASI3301("1002","Invalid Request parameters","请求参数不合法","3301","存在必填字段未上送"),
	KASI3302("2000","Transaction is abnormal","交易异常","3302","交易类型未开通"),
	KASI3304("2006","Currency Not Matches","币种不匹配","3304","交易币种错误"),
	KASI3305("3097","The card type is not accepted by the payment processor","不支持该卡种","3305","卡种未开通"),
	KASI3306("3093","Exceed amount limit","金额超出限制","3306","交易金额大于可交易金额"),
	KASI3307("3099","System Busy","其他异常","3307","EDC交易不能使用DCC编号"),
	KASI3308("3093","Exceed amount limit","金额超出限制","3308","授权完成金额超出预授权金额"),
	KASI3309("3095","Transaction Timeout","交易超时","3309","主机超时"),
	KASI3310("1002","Invalid Request parameters","请求参数不合法","3310","票据号已存在"),
	KASI3311("1002","Invalid Request parameters","请求参数不合法","3311","字段不符合要求"),
	KASI3312("3092","Invalid Transaction","无效交易","3312","无效终端"),
	KASI3313("3099","System Busy","其他异常","3313","网关类型填写错误"),
	KASI3314("1000","Invalid Parameters","参数异常","3314","Order_no已存在"),
	KASI3315("3099","System Busy","其他异常","3315","DCC交易不能使用EDC编号"),
	KASI3316("3099","System Busy","其他异常","3316","DCC交易未能匹配DCC汇率查询"),
	KASI3317("3020","Transaction not permitted on card","交易拒绝","3317","授权号已被使用，请求被拒绝"),
	KASI3318("3020","Transaction not permitted on card","交易拒绝","3318","交易/授权已撤销,请求被拒绝"),
	KASI3319("1000","Invalid Parameters","参数异常","3319","票据号不能全为0"),
	KASI3320("3013","Refund amount is larger than payment amount","可退款金额大于付款金额","3320","退款金额大于可退金额"),
	KASI3321("1002","Invalid Request parameters","请求参数不合法","3321","非DCC交易禁止上送DCC交易字段"),
	KASI3322("3088","Exceed Times Limitation","卡号次数超限","3322","卡交易次数超过上限"),
	KASI3323("0052","Your Payment Request Failed Due to Charge Failure!","高风险交易 ","3323","商务卡黑名单拒付"),
	KASI3324("0052","Your Payment Request Failed Due to Charge Failure!","高风险交易 ","3324","商户黑名单拒付"),
	KASI3325("0052","Your Payment Request Failed Due to Charge Failure!","高风险交易 ","3325","全局黑名单拒付"),
	KASI3123("3099","System Busy","其他异常","3123","杂币原交易货币代码不正确"),
	KASI3124("3099","System Busy","其他异常","3124","杂币交易卡号cardbin有问题"),
	KASI3125("1023","Transaction is abnormal","订单金额格式非法，必须为纯数字，不带小数点","3125","杂币金额格式不正确"),
	KASI3126("3100","Do not honor","交易受限","3126","商户VISA卡每日交易笔数超过上限"),
	KASI3127("3100","Do not honor","交易受限","3127","商户VISA卡每日交易累计金额超过上限"),
	KASI3128("3100","Do not honor","交易受限","3128","网关商户VISA卡每日交易笔数超过上限"),
	KASI3129("3100","Do not honor","交易受限","3129","网关商户VISA卡每日交易累计金额超过上限"),
	KASI3226("3100","Do not honor","交易受限","3226","商户MASTERCARD卡每日交易笔数超过上限"),
	KASI3227("3100","Do not honor","交易受限","3227","商户MASTERCARD卡每日交易累计金额超过上限"),
	KASI3228("3100","Do not honor","交易受限","3228","网关商户MASTERCARD卡每日交易笔数超过上限"),
	KASI3229("3100","Do not honor","交易受限","3229","网关商户MASTERCARD卡每日交易累计金额超过上限"),
	KASI3326("3100","Do not honor","交易受限","3326","商户JCB卡每日交易笔数超过上限"),
	KASI3327("3100","Do not honor","交易受限","3327","商户JCB卡每日交易累计金额超过上限"),
	KASI3328("3100","Do not honor","交易受限","3328","网关商户JCB卡每日交易笔数超过上限"),
	KASI3329("3100","Do not honor","交易受限","3329","网关商户JCB卡每日交易累计金额超过上限"),
	KASI3350("3099","System Busy","其他异常","3350","冲正完成"),
	KASI3331("5005","Faild to receive notification from institute","银行返回信息失败","3331","3D返回字段有误"),
	KASI3336("3099","System Busy","其他异常","3336","无法获取原始3D交易相关的明细"),
	KASI3341("5005","Faild to receive notification from institute","银行返回信息失败","3341","Risk返回字段有误"),
	KASI4001("3095","Transaction Timeout","交易超时","4001","持卡人跳转超时"),
	KASI4002("3095","Transaction Timeout","交易超时","4002","3D验证发卡行返回超时"),
	KASI4003("3095","Transaction Timeout","交易超时","4003","风控review超时"),
	KASI4401("2002","Duplicate Order Number Detected, Please Return to Merchant Website and Submit Again.","您的订单号已存在,请变更订单号，重新提交。","4401","交易已存在"),
	KASI4402("3002","The order does not exist.","订单不存在","4402","无法匹配原始交易"),
	KASI4404("3096","Invalid Expiration","有效期错误","4404","有效期输入有误"),
	KASI5501("3099","System Busy","其他异常","5501","Mpi ve连接异常"),
	KASI5502("3099","System Busy","其他异常","5502","Mpi acs连接异常"),
	KASI5503("3099","System Busy","其他异常","5503","Mpi ve连接超时"),
	KASI5504("3099","System Busy","其他异常","5504","Mpi acs连接超时"),
	KASI5505("3099","System Busy","其他异常","5505","Mpi ve失败"),
	KASI5506("3099","System Busy","其他异常","5506","Mpi acs失败"),
	KASI5507("3099","System Busy","其他异常","5507","Mpi 3D验证过程错误"),
	KASI5600("3099","System Busy","其他异常","5600","风控成功"),
	KASI5601("3099","System Busy","其他异常","5601","风控需要进行review"),
	KASI5602("3099","System Busy","其他异常","5602","风控拒绝"),
	KASI5603("3099","System Busy","其他异常","5603","风控错误"),
	KASI5699("3099","System Busy","其他异常","5699","进行事后风控"),
	KASI9901("3110","Refer to card issuer","请联系发卡行","9901","查發卡行"),
	KASI9902("3110","Refer to card issuer","请联系发卡行","9902","查發卡行"),
	KASI9903("2003","Merchant Not Exists","商户ID不存在","9903","無效商戶"),
	KASI9904("3103","Invalid account number","无效账户","9904","沒收卡"),
	KASI9905("3099","System Busy","其他异常","9905","不予承兌"),
	KASI9907("3103","Invalid account number","无效账户","9907","特殊條件下沒收卡"),
	KASI9912("3092","Invalid Transaction","无效交易","9912","無效交易"),
	KASI9913("3100","Do not honor","交易受限","9913","無效金額"),
	KASI9914("3103","Invalid account number","无效账户","9914","無效卡號"),
	KASI9915("3100","Do not honor","交易受限","9915","無此發卡行"),
	KASI9919("3100","Do not honor","交易受限","9919","重新輸入交易"),
	KASI9930("9006","Message format is not available","报文格式错误","9930","格式錯誤"),
	KASI9933("3102","Expired card","过期卡","9933","過期的卡"),
	KASI9934("3094","Exceed amount limit","受限制卡","9934","有作弊嫌疑"),
	KASI9935("3099","System Busy","其他异常","9935","商戶需與收單行保安部門聯系"),
	KASI9936("3094","Exceed amount limit","受限制卡","9936","受限制的卡"),
	KASI9937("3099","System Busy","其他异常","9937","商戶呼收單行保安部門"),
	KASI9938("3106","Exceeds PIN Retry","PIN输入超过次数","9938","超過允許的PIN試輸入"),
	KASI9939("3103","Invalid account number","无效账户","9939","無此信用卡賬戶"),
	KASI9941("3094","Exceed amount limit","受限制卡","9941","挂失卡"),
	KASI9943("3094","Exceed amount limit","受限制卡","9943","被窃卡"),
	KASI9951("2023","","账户余额不足","9951","無足够的存款"),
	KASI9954("3102","Expired card","过期卡","9954","過期的卡"),
	KASI9955("3099","System Busy","其他异常","9955","不正確的PIN"),
	KASI9957("3100","Do not honor","交易受限","9957","不允許持卡人進行的交易"),
	KASI9958("3100","Do not honor","交易受限","9958","不允許終端進行的交易"),
	KASI9959("3100","Do not honor","交易受限","9959","有作弊嫌疑"),
	KASI9960("3099","System Busy","其他异常","9960","商戶與保安部聯系"),
	KASI9961("2023","","账户余额不足","9961","超出取款金額限制"),
	KASI9962("3094","Exceed amount limit","受限制卡","9962","受限制的卡"),
	KASI9963("3086","Security Policy Violation","违反保密规定","9963","違反安全保密規定"),
	KASI9966("3099","System Busy","其他异常","9966","商戶呼收單行保安部"),
	KASI9968("3099","System Busy","其他异常","9968","收到的回答太遲"),
	KASI9975("3106","Exceeds PIN Retry","PIN输入超过次数","9975","允許的輸入PIN次數超限"),
	KASI9976("3110","Refer to card issuer","请查询发卡银行","9976","請聯絡發卡銀行"),
	KASI9977("3004","The amount of order error","订单金额错误","9977","金額錯誤"),
	KASI9989("3099","System Busy","其他异常","9989","終端機代號錯誤"),
	KASI9991("3099","System Busy","其他异常","9991","發卡行或交換中心不能操作"),
	KASI9992("3099","System Busy","其他异常","9992","金融機構或中間網絡設施找不到或無法達到"),
	KASI9993("3100","Do not honor","交易受限","9993","交易違法，不能完成"),
	KASI9994("3105","Duplicate Reversal","重复交易","9994","重覆交易"),
	KASI9995("3099","System Busy","其他异常","9995","調節控制錯"),
	KASI9996("3092","Invalid Transaction","无效交易","9996","系統失效"),
	KASI9998("3110","Refer to card issuer","请查询发卡银行","9998","交換中心收不到發卡行應答"),
	KASI9999("3099","System Busy","其他异常","9999","PIN 格式錯"),
	KASI99XX("3100","Do not honor","交易受限","99XX","沒法支持交易"),
	KASI99YX("3100","Do not honor","交易受限","99YX","Card Bin not allow DCC transaction"),
	KASI99YY("3099","System Busy","其他异常","99YY","Card Bin allow DCC transaction"),
	KASI99YZ("3100","Do not honor","交易受限","99YZ","YZ 雙幣卡拒付"),
	KASI99N7("3104","Invalid Card Verification Value (CVV)","CVV2错误","99N7","CSC校验错误"),
				

	MSG9000("9000","System is maintaining","系统维护中","",""),
	MSG9001("9001","HTTP request timeout","HTTP请求超时","",""),
	MSG9002("9002","System runtime exception","未知异常","",""),
	MSG9003("9003","MQ is abnormal","MQ异常","",""),
	MSG9004("9004","MQ Error","MQ出错","",""),
	MSG9005("9005","System running exception","系统运行时异常","",""),
	MSG9006("9006","Message format is not available","报文格式错误","",""),
	MSG1000("1000","Invalid Parameters","参数异常","",""),
	MSG1001("1001","Error Occured While Converting Message","报文转换异常","",""),
	MSG1002("1002","Invalid Request parameters","请求参数不合法","",""),
	MSG1003("1003","Error Occured While verifying Signature","验签异常","",""),
	MSG1004("1004","Failed to Verify Signature","验签失败","",""),
	MSG1005("1005","Error Occured While Signing","加签异常","",""),
	MSG1006("1006","Faild to Sign Message","加签失败","",""),
	MSG1007("1007","Parameter Type Not Matches","参数类型不对","",""),
	MSG1008("1008","Invalid Payment Method","支付方式验证出错","",""),
	MSG1009("1009","The Merchant doesn't have privilege of connect to institute directly","商户没有直连权限","",""),
	MSG1011("1011","The institute of direct connection has not been activated","直连渠道未开通","",""),
	MSG1012("1012","Invalid Parameter Length","参数长度错误","",""),
	MSG1019("1019","Sign Method Can Not Be Empty","签名类型不能为空","",""),
	MSG2003("2003","Merchant Not Exists","商户ID不存在   ","",""),
	MSG2004("2004","Error Occurred  While Notifying the Merchant","通知商户异常   ","",""),
	MSG2005("2005","Invalid Order Amount","订单金额错误   ","",""),
	MSG2006("2006","Currency Not Matches","币种不匹配   ","",""),
	MSG2007("2007","Target institute error","目标机构渠道错   ","",""),
	MSG2008("2008","Initiator of the refund must be specified","必须指定退款发起方   ","",""),
	MSG2015("2015","deposit to this account has been disabled","此账户已禁用收款功能   ","",""),
	MSG2016("2016","withdraw from this account has been disabled","此账户已禁用出款功能   ","",""),
	MSG2018("2018","","暂不支持的汇率转换币种","",""),
	MSG2019("2019","","通知数据没有找到","",""),
	MSG2020("2020","","已经成功通知商户，无法再次发送   ","",""),
	MSG20210("2021","","订单状态异常","",""),
	MSG20211("2021","","收銀台模板沒有找到","",""),
	MSG2022("2022","","收銀台模板處理失敗","",""),
	MSG2023("2023","","账户余额不足","",""),
	MSG2024("2024","","账户不存在","",""),
	MSG2100("2100","Status code error","状态码错误","",""),
	MSG2102("2102","","收单未成功或已退款","",""),
	MSG2103("2103","","域名解析错误","",""),
	MSG2104("2104","Connection refused","连接拒绝","",""),
	MSG2105("2105","The request timeout","请求超时","",""),
	msg2106("2106","","链接中断","",""),
	msg2107("2107","","SSL证书算法","",""),
	msg2108("2108","","SSL地址格式错误","",""),
	msg2109("2109","","SSL Key错误","",""),
	msg2030("2030","","代购协议已过期","",""),
	msg2031("2031","","代购协议周期类型错误","",""),
	msg2032("2032","","代购协议扣款日期不为今天","",""),
	msg2033("2033","","代购协议不存在","",""),
	msg2034("2034","","代购协议已失效","",""),
	msg2035("2035","","代购协议参数错误","",""),
	msg3000("3000","Payment is abnormal","支付异常 ","",""),
	msg3001("3001","Available refund amount is not enough","可退款金额不足 ","",""),
	msg3002("3002","The order does not exist","订单不存在 ","",""),
	msg3003("3003","The order has already been paid","订单已支付成功 不可重复支付 ","",""),
	msg3004("3004","Invalid order amount","订单金额错误 ","",""),
	msg3005("3005","withdraw from this account has been disabled","账户止出 ","",""),
	msg3006("3006","deposit to this account has been disabled","账户止入 ","",""),
	msg3007("3007","The Original Payment Order Number Does not Exist","支付流水不存在 ","",""),
	msg3008("3008","Duplicated Payment Request","重复提交支付请求 ","",""),
	msg3009("3009","Acquiring Institute Error","获取银行渠道异常 ","",""),
	msg3010("3010","Payment Amount Not Matches","支付金额不匹配 ","",""),
	msg3011("3011","Unknown Payment Status","未知的支付状态 ","",""),
	msg3012("3012","Information of the payment instruction does not exist","支付指令信息不存在 ","",""),
	msg3013("3013","Refund amount is larger than payment amount","可退款金额大于付款金额 ","",""),
	msg3014("3014","Payment at institute side is invalid","银行渠道支付异常 ","",""),
	msg3015("3015","The currency is not supported","不支持币种 ","",""),
	msg3016("3016","Payment URL and merchant URL are not matched","支付提交网址与商户预设网址不匹配 ","",""),
	msg3017("3017","Some order number you supplied are not matched","提交的参数中，多个订单号不匹配 ","",""),
	msg3018("3018","The order has been done","订单已关闭或已完成 ","",""),
	msg3019("3019","Information of the remittance is not unique","汇款信息单系统内不唯一 ","",""),
	msg3020("3020","Transaction not permitted on card","交易拒绝 ","",""),
	msg3021("3021","The remittance's amount you typed is less than the order's amount, please check your real remittance's amount and retry it","您输入的汇款金额小于订单金额，请确认您的实际汇款金额，如果确实小于订单金额请撤销后重新汇款 ","",""),
	msg3022("3022","Currency of remittance is different from order's","汇款币种与订单币种不一致 ","",""),
	msg3023("3023","The order is auditing and could not be changed","订单已进入审核流程不可更改 ","",""),
	msg3024("3024","Institute order does not exist","机构订单不存在 ","",""),
	msg3025("3025","Institutions do not exist refund orders","机构退款订单不存在 ","",""),
	msg3026("3026","Channels resulting state is empty","渠道结果状态为空 ","",""),
	msg3027("3027","Exception notification Merchant","通知商户异常 ","",""),
	msg3028("3028","Please check the refund amount, currency against the order amount, currency has been, and to ensure its value is less than or equal to the amount of refund","计算可退款金额异常 请检测您提交的退款金额和币种是否与订单金额和币种一直，并保证其数值小于等于可退款金额。 ","",""),
	msg3029("3029","","退款申请调 失败 退款失敗 ","",""),
	msg3030("3030","Do not duplicate payments","频繁的支付订单 ","",""),
	msg3084("3084","Insufficient funds in the account.","卡余额不足 ","",""),
	msg3085("3085","Unable to Verify PIN","密码错误 ","",""),
	msg3086("3086","Security Policy Violation","违反保密规定 ","",""),
	msg3088("3088","Exceed Times Limitation","卡号次数超限 ","",""),
	msg3089("3089","Suspected of Cheating","有作弊嫌疑","",""),
	msg3091("3091","Invalid Institute Parameter","银行参数错误 ","",""),
	msg3092("3092","Invalid Transaction","无效交易 ","",""),
	msg3093("3093","Exceed amount limit","金额超出限制 ","",""),
	msg3094("3094","Invalid transaction card / issuer / acquirer","受限制卡 ","",""),
	msg3095("3095","Transaction Timeout","交易超时 ","",""),
	msg3096("3096","Invalid Expiration","有效期错误 ","",""),
	msg3097("3097","The card type is not accepted by the payment processor","不支持该卡种 ","",""),
	msg3098("3098","System Busy","系统异常 ","",""),
	msg3099("3099","System Busy","其他异常 ","",""),
	msg3100("3100","Do not honor","交易受限 ","",""),
	msg3101("3101","Invalid Card Information","卡信息错误","",""),
	msg3102("3102","Expired card","过期卡 ","",""),
	msg3103("3103","Invalid account number","无效账户 ","",""),
	msg3104("3104","Invalid Card Verification Value (CVV)","校验码错误 ","",""),
	msg3105("3105","Duplicate Reversal","重复交易 ","",""),
	msg3106("3106","Exceeds PIN Retry","输入超过次数 ","",""),
	msg3107("3107","The merchant reference code for this authorization request matches the merchant reference code of another authorization request that you sent within the past 15minutes","授权号错误 ","",""),
	msg3108("3108","The authorization request was approved by the issuing bank but declined by CyberSource because it did not pass the Address Verification System (AVS)check","发卡行拒绝 ","",""),
	msg3109("3109","The issuing bank has questions about the request. You do not receive an authorization code programmatically, but you might receive one verbally by calling the processor","发卡行有疑义（金额过大等） ","",""),
	msg3110("3110","Refer to card issuer","请联系发卡银行 ","",""),
	msg3111("3111","Inactive card or card not authorized for card-not-present transactions","无效卡或卡片未激活 ","",""),
	msg3112("3112","","高风险-跨国交易 ","",""),
	msg3113("3113","","高风险-高风险国家 ","",""),
	msg3114("3114","","高风险-同身份对应多卡 ","",""),
	msg3115("3115","","高风险-频繁交易 ","",""),
	msg3116("3116","","高风险-其他综合风险 ","",""),
	msg3117("3117","The request is missing one or more required fields","缺少参数","",""),
	msg3118("3118","Only a partial amount was approved.","部分金额接受","",""),
	msg3119("3119","Stolen or lost card.","挂失卡","",""),
	msg3120("3120","The customer matched an entry on the processor’s negative file.","命中发卡行黑名单","",""),
	msg3121("3121","The authorization has already been reversed","授权已撤销","",""),
	msg3122("3122","The authorization has already been captured","授权已请款","",""),
	msg3123("3123","stand-alone credits are not allowed","stand-alone credits are not allowed","",""),
	msg3124("3124","Unable to Reverse","不允许撤销","",""),
	msg3125("3125","No From Account","No From Account","",""),
	msg3126("3126","No To Account","No To Account","",""),
	msg3127("3127","No Checking Account","No Checking Account","",""),
	msg3128("3128","No Saving Account","No Saving Account","",""),
	msg3129("3129","No Credit Account","No Credit Account","",""),
	msg3130("3130","Exceeds Acquirer Limit","Exceeds Acquirer Limit","",""),
	msg4000("4000","Settled error","清算对账异常","",""),
	msg4001("4001","Failed to create settlement stream","创建清算流水出错","",""),
	msg4002("4002","Institute settlement is failed","银行对账单出错","",""),
	msg5000("5000","Institute is abnormal","资金机构异常","",""),
	msg5001("5001","Service of the institute is temporarily stoped","资金机构暂停服务","",""),
	msg5002("5002","Status of the institute is abnormal","资金机构状态出错","",""),
	msg5003("5003","Merchant number does not exist in institue","银行商户号不存在","",""),
	msg5004("5004","Invalid signature at institute side","银行返回签名验证异常","",""),
	msg5005("5005","Faild to receive notification from institute","银行返回信息失败","",""),
	msg5006("5006","Failed to connect to institute","银行接口连接失败","",""),
	msg5007("5007","Unknown error from institute","银行处理未知异常","",""),
	msg5008("5008","Institute is abnormal","银行渠道异常","",""),
	msg5009("5009","The notification's amount from institute does not match","银行回调通知金额不符","",""),
	msg5010("5010","Unsupported service of the institute","资金机构不支持本服务","",""),
	msg5011("5011","Real paid amount is less than order's","实际入账金额小于订单金额","",""),
	msg5012("5012","Real refund amount is more than order's","实际退款金额大于订单金额","",""),
	msg5013("5013","","渠道重试","",""),
	msg6000("6000","Database error","数据库异常","",""),
	msg6001("6001","Database error","数据库查询异常","",""),
	msg6002("6002","Database error","数据库结果异常","",""),
	msg7000("7000","Offline remittance is abnormal","线下汇款审核异常","",""),
	msg7001("7001","The order is auditing, please do not apply again","订单已进入审核流程，不可重复申请","",""),
	msg7002("7002","Real paid currency is different from order's","实际入款币种与订单币种不一致","",""),
	msg7003("7003","Real paid amount is less than order's","实际入款金额比订单金额小","",""),
	msg8000("8000","Gateway order does not exist","网关订单没有找到","",""),
	msg8001("8001","Gateway order has already been held on","网关订单已经挂账","",""),
	msg8002("8002","Gateway order has been wholly refunded","网关订单已经全额退款","",""),
	msg8003("8003","Transaction has been settled","交易已结算","",""),
	msg8004("8004","Unfinished transaction","交易未成功","",""),
	msg8005("8005","Gateway order has not been held on","网关订单未挂账","",""),
	msg8006("8006","Settle data does not exist","交易结算数据没有找到","",""),
	msg8007("8007","PE losses are fail","PE挂账失败","",""),
	msg8008("8008","PE release fails","该渠道不支持挂账和解挂","",""),
	msg8009("8009","This channel does not support reconciliation losses are linked","PE释放失败","",""),
	msg90001("9000","","商户配置获取失败","","");
	
	private String respCode;//返回给商户的编码
	private String respDescEn;//返回给商户结果码的描述（英文）
	private String respDEscCn;//返回给商户结果码的描述（中文）
	private String systemCode;//系统返回的结果码
	private String systemDesc;//系统返回的结果码描述

	SystemRespCodeEnum(String respCode, String respDescEn,String respDEscCn,
			String systemCode,String systemDesc) {
		this.respCode = respCode;
		this.respDescEn = respDescEn;
		this.respDEscCn = respDEscCn;
		this.systemCode = systemCode;
		this.systemDesc = systemDesc;
	}

	public String getRespCode() {
		return respCode;
	}
	public String getRespDescEn() {
		return respDescEn;
	}
	public String getRespDEscCn() {
		return respDEscCn;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public String getSystemDesc() {
		return systemDesc;
	}

	public static SystemRespCodeEnum getResponseCodeEnum(String value) {
		if (value != null) {
			for (SystemRespCodeEnum nameEnum : values()) {
				if (nameEnum.getSystemCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}

	public static boolean isResponseCodeEnum(String value) {
		if (value != null) {
			for (SystemRespCodeEnum nameEnum : values()) {
				if (nameEnum.getSystemCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

}
