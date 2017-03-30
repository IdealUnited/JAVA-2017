 /** @Description 
 * @project 	poss-base
 * @file 		BaseObject.java 
 * Copyright © 2006-2010 framework Corporation. All rights reserved
 * @version     1.0
 * Date				Author			Changes
 * 2010-7-27		Henry.Zeng			Create 
*/
package com.idealunited.inf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>基于反射的通用的equals,hashCode,toString</p>
 * @author Henry.Zeng
 * @since 2010-7-27
 * @see 
 */
public class BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public boolean equals(final Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
