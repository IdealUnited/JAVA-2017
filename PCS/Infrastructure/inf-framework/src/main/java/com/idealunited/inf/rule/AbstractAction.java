/**
 *  File: AbstractAction.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;


/**
 * 验证规则处理节点
 * @author zliner
 *
 */
public abstract class AbstractAction extends AbstractComponent {

	//规则引擎中配置的下一步待处理组件(可为验证规则或执行规则)
	private AbstractComponent nextStep;
	//get
	public AbstractComponent getNextStep() {
		return nextStep;
	}
	//set诸如
	public void setNextStep(final AbstractComponent param) {
		this.nextStep = param;
	}
	/**
	 * 规则验证通用执行方法
	 * @param validateBean        待验证规则对象
	 * @throws Exception          验证中抛出运行时异常
	 */
	public void execute(Object validateBean) throws Exception {
		doExecute(validateBean);
		if(nextStep!=null){
			nextStep.execute(validateBean);
		}
	}
	/**
	 * 规则验证中规则执行处理逻辑方法
	 * @param validateBean        待验证规则对象 
	 * @throws Exception          验证中抛出运行时异常
	 */
	protected abstract void doExecute(Object validateBean) throws Exception;

}
