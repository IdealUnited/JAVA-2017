/**
 * 
 */
package com.idealunited.poss.tags;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.idealunited.util.NumberUtil;
import com.idealunited.util.StringUtil;

public class DateTag extends TagSupport {
	private static final long serialVersionUID = -2312310581852395045L;
	private String value;

	@Override
	public int doStartTag() throws JspException {

		if (StringUtil.isEmpty(value)) {
			return super.doStartTag();
		}

		try {
			String vv = "" + value;
			String s = "";
			if (NumberUtil.isNumber(vv)) {
				long time = Long.valueOf(vv);
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(time);
				SimpleDateFormat dateformat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				s = dateformat.format(c.getTime());
			} else {
				s = vv;
			}

			pageContext.getOut().write(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public void setValue(String value) {
		this.value = value;
	}
}
