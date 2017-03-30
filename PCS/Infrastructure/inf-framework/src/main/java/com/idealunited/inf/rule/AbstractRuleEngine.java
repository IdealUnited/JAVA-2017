/**
 *  File: AbstractRuleEngine.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;


/**
 * 通用规则验证引擎
 * @author zliner
 *
 */
public abstract class AbstractRuleEngine {
	//验证引擎设置第一个验证规则
	private AbstractComponent firstStep;
	//get
	public AbstractComponent getFirstStep() {
		return firstStep;
	}
	//set注入
	public void setFirstStep(final AbstractComponent param) {
		this.firstStep = param;
	} 
	 /**
	  * 规则引擎处理验证请求
	 * @param validateBean        待验证请求
	 * @throws Exception          验证中抛出运行时异常   
	 */
	public abstract void processRequest(Object validateBean) throws Exception;
	
}
