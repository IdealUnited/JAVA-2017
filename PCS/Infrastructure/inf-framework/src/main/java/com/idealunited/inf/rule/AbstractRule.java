/**
 *  File: AbstractRule.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;


/**
 * @author zliner
 *
 */
public abstract class AbstractRule extends AbstractComponent {
	//验证规则通过后的执行组件
	private AbstractComponent positiveOutcomeStep;
	//验证规则未通过后的执行组件
	private AbstractComponent negativeOutcomeStep;
	//set注入
	public void setNegativeOutcomeStep(final AbstractComponent param) {
		this.negativeOutcomeStep = param;
	}
	//set注入
	public void setPositiveOutcomeStep(final AbstractComponent param) {
		this.positiveOutcomeStep = param;
	}

	/**
	 * 规则验证通用执行方法
	 * @param validateBean        待验证规则对象
	 * @throws Exception          验证中抛出运行时异常
	 */
	public void execute(Object validateBean) throws Exception {
		boolean outcome = makeDecision(validateBean);
		if (outcome) {
			positiveOutcomeStep.execute(validateBean);
		} else {
			negativeOutcomeStep.execute(validateBean);
		}
	}
	/**
	 * 验证规则执行验证方法
	 * @param validateBean        待验证规则对象
	 * @return boolean            true表示验证规则通过，false表示该验证规则未通过
	 * @throws Exception          验证中抛出运行时异常
	 */
	protected abstract boolean makeDecision(Object validateBean) throws Exception;

}
