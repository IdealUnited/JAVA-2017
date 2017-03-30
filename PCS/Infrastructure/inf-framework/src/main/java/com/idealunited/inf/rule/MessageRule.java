/**
 *  File: MessageRule.java
 *  Description:
 *  Copyright 2010 -2010 pay Corporation. All rights reserved.
 *  2010-9-12      zliner      Changes
 *  
 *
 */
package com.idealunited.inf.rule;

/**
 * 验证规则之设置错误消息规则
 *
 */
public abstract class MessageRule extends AbstractRule {
	// 验证错误消息代码
	private String messageId;
	private String message;

	// set注入
	public void setMessageId(final String param) {
		this.messageId = param;
	}

	// get
	public String getMessageId() {
		return messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
