package com.idealunited.poss.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.idealunited.poss.utils.TokenHelper;

public class FormTokenTag extends BodyTagSupport {

	private static final long serialVersionUID = 4078042013255171672L;

	/**
	 * 产生一个新的Token并且保存该Token
	 * 如果用户有大量没有提交的token，有可能会导致正确的token被删除，具体实现参见TokenHelper
	 */
	public int doStartTag() throws JspException {
		String token= TokenHelper.newToken(formId, this.pageContext);
		try {
			if (valueOnly) {
				this.pageContext.getOut().write(token);
			}
			else {
				this.pageContext.getOut().write("<input type='hidden' name='");
				this.pageContext.getOut().write(TokenHelper.TOKEN_NAME);
				this.pageContext.getOut().write("' value='");
				this.pageContext.getOut().write(token);
				this.pageContext.getOut().write("' />");
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}
	
	/**
	 * 定义了formId意味着该FORM用户同时只能有一个待提交请求
	 */
	private String formId;

	/**
	 * 用于某些特殊情况下，只需要token值，例如拼凑Get URL。
	 */
	private boolean valueOnly= false;

	public boolean isValueOnly() {
		return valueOnly;
	}

	public void setValueOnly(boolean valueOnly) {
		this.valueOnly = valueOnly;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
}
