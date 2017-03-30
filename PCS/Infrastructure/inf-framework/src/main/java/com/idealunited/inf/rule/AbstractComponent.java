/**
 *  File: AbstractComponent.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;

/**
 * 验证规则通用组件
 * @author zliner
 *
 */
public abstract class AbstractComponent {
	/**
	 * 规则验证通用执行方法
	 * @param validateBean        待验证规则对象
	 * @throws Exception          验证中抛出运行时异常
	 */
	public abstract void execute(Object validateBean) throws Exception;
}
