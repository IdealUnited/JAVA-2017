/**
 *  File: RuleEngine.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;

/**
 * 验证规则引擎
 * @author zliner
 *
 */
public class RuleEngine extends AbstractRuleEngine {
	 /**
	  * 规则引擎处理验证请求
	 * @param validateBean        待验证请求
	 * @throws Exception          验证中抛出运行时异常   
	 */
	public void processRequest(Object validateBean) throws Exception {
		getFirstStep().execute(validateBean);
	}

}
