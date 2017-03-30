/**
 *  File: StopExecuteAction.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;

/**
 * 停止规则校验通用处理
 * @author zliner
 *
 */
public class StopExecuteAction extends AbstractAction {
	/**
	 * 规则验证中规则执行处理逻辑方法
	 * @param validateBean        待验证规则对象 
	 * @throws Exception          验证中抛出运行时异常
	 */
	protected void doExecute(Object validateBean) throws Exception {
		setNextStep(null);
	}

}
