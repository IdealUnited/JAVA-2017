/**
 *  File: ValidateServiceImpl.java
 *  Description:
 *  Copyright 2006-2011 pay Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-9-12   terry     Create
 *
 */
package com.idealunited.inf.service.impl;

import com.idealunited.inf.rule.RuleEngine;
import com.idealunited.inf.service.ValidateService;

/**
 * 
 */
public class ValidateServiceImpl implements ValidateService {

	private RuleEngine ruleEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.exp.fo.order.validate.ValidateService#validate(java.lang.Object)
	 */
	@Override
	public void validate(Object request) throws Exception {
		ruleEngine.processRequest(request);
	}

	public void setRuleEngine(RuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}

}
