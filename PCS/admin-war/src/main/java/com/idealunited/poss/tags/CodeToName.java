package com.idealunited.poss.tags;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeToName extends BodyTagSupport{
	
	protected final static transient Log log = LogFactory.getLog(CodeToName.class);
	private List<Map<String,String>> itemList;
	private String selected = "";
	
	
	public int doStartTag() {

			print(doFormatSub());
		
		return (BodyTagSupport.EVAL_BODY_BUFFERED);
	}
	
	
	protected String doFormatSub() {
		
		String result = "";
		if(itemList!=null){
			for (Map<String, String> map : itemList) {
				String text = map.get("text");
				String value = map.get("value");
				if(value.endsWith(selected))
					result =  text;
			}
		}
		return result;
	}
	
	
	
	
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Map<String, String>> itemList) {
		this.itemList = itemList;
	}


	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}


	private void print(String str) {
		JspWriter out = pageContext.getOut();
		try {
			out.print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
