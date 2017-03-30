package com.idealunited.inf.comm;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.idealunited.inf.dao.Page;

public class PageTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private Page pageBean;
	private String methodName; // 调用script方法名
	private String sytleName; // 样式

	public int doStartTag() {
		
		if ("sytle1".equals(sytleName))
			printSytleStr1();
		else
			getPageHtmlStr();

		return (BodyTagSupport.EVAL_BODY_BUFFERED);
	}

	private void printSytleStr1() {
		StringBuffer buffer = new StringBuffer();
		

		buffer.append("<table class=\"p_table ui-widget-content\">");
		buffer.append("<tr>");
		buffer.append("<td class=\"p_top\"></td>");
		buffer.append("<td style=\"width: 24px;\"><span" + (pageBean.getPageNo() == 1 ? " class=\"p_first\"" : " class=\"ui-icon ui-icon-seek-first finger\" onclick=\"" + methodName + "(1)\"") + "></span></td>");
		buffer.append("<td style=\"width: 24px;\"><span" + (pageBean.getPageNo() == 1 ? " class=\"p_previous\"" : " class=\"ui-icon ui-icon-seek-prev finger\" onclick=\"" + methodName + "(" + (pageBean.getPageNo() - 1) + ")" + "\"") + "></span></td>");
		buffer.append("<td style=\"width: 10px;\"><hr width=\"0\" size=\"16\" /></td>");
		buffer.append("<td class=\"p_input\">第 <input type=\"text\" value=\"" + pageBean.getPageNo() 
				+ "\" onkeydown=\"javascript:var e = event || window.event; if (e.keyCode == 13) { if (this.value > " 
				+ pageBean.getTotalPage() + " || this.value < 1) { alert('没有该页的索引!'); return; }" + methodName 
				+ "(this.value)}\" />&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;共 " + pageBean.getTotalPage() + "&nbsp;&nbsp;页</td>");
		buffer.append("<td style=\"width: 10px;\"><hr width=\"0\" size=\"16\" /></td>");
		buffer.append("<td style=\"width: 24px;\"><span " + (pageBean.getPageNo() == pageBean.getTotalPage() || pageBean.getTotalRecord() == 0 ? " class=\"p_nonext\"" : " class=\"ui-icon ui-icon-seek-next finger\" onclick=\"" + methodName + "(" + (pageBean.getPageNo() + 1) + ")" + "\"") + "></span></td>");
		buffer.append("<td style=\"width: 24px;\"><span" + (pageBean.getPageNo() == pageBean.getTotalPage() || pageBean.getTotalRecord() == 0 ? " class=\"p_last\"" : " class=\"ui-icon ui-icon-seek-end finger\" onclick=\"" + methodName + "(" + pageBean.getTotalPage() + ")\"") + "></span></td>");
		buffer.append("<td></td>");
		buffer.append("<td class=\"p_footer\">显示 " + ((pageBean.getPageNo() - 1) * pageBean.getPageSize() + 1) 
						+ " - " + (pageBean.getPageNo() == pageBean.getTotalPage() ? pageBean.getTotalRecord() : pageBean.getPageNo() * pageBean.getPageSize()) 
						+ "&nbsp;&nbsp;条记录&nbsp;&nbsp;&nbsp;共&nbsp;&nbsp;" + pageBean.getTotalRecord() + "&nbsp;&nbsp;条记录</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		print(buffer.toString());
	}

	private void getPageHtmlStr(){
		
		StringBuffer strHtml = new StringBuffer();
		int prevPage = pageBean != null ? pageBean.getPageNo() - 1 : 0; //上一页
		int nextPage = pageBean != null ? pageBean.getPageNo() + 1 : 0; //下一页
		int pageNo = pageBean != null ? pageBean.getPageNo() : 1; //当前页
		int totalPages = pageBean != null ? pageBean.getTotalPage() : 1; //总页数
		int totalCount = pageBean != null ? pageBean.getTotalRecord() : 0; //总记录数
		int pageSize = null != pageBean? pageBean.getPageSize():20; //页面数据条数
		int showTimes = Calendar.SECOND;
		int endPage = 0;
		
		strHtml.append("<script type=\"text/javascript\">");
		
		strHtml.append("function formatInputPage(e){"); 
		strHtml.append("var ie = navigator.appName==\"Microsoft Internet Explorer\"?true:false;");	
		strHtml.append("if(!ie) var key = e.which;");	
		strHtml.append("else var key = event.keyCode;");	
		strHtml.append("if (key == 8 || (key >= 48 && key <= 57)) return true;");	
		strHtml.append("return false;} ");
		
		strHtml.append("function checkPages(page){");
		strHtml.append("if (page < 1) page = 1;");	
		strHtml.append("if (page > "+ totalPages +") page = "+ totalPages +";");
		strHtml.append("return page;} ");
		
		strHtml.append("function setPageSize(obj,pageNo,totalCount){");
		strHtml.append("var pageSize = 20;");
		strHtml.append("if(\"\" != obj.value){");
		strHtml.append("pageSize = obj.value;}");
		strHtml.append(methodName + "(pageNo,totalCount,pageSize);");
		strHtml.append("}");
		
		strHtml.append("</script>");
		
		strHtml.append("<div id=\"pages_" + showTimes + "\" class=\"pages\"></div>");
		strHtml.append("<div class=\"" + sytleName +"\">");
		strHtml.append("<span>总计：" + totalCount + "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		strHtml.append("<span>当前页/总页数：" + pageNo + "/" + totalPages + "</span>");
		
		if (prevPage < 1) {
			strHtml.append("<span title=\"第一页\" class=\"disabled\">&laquo;</span>");
			strHtml.append("<span title=\"上一页\" class=\"disabled\">&#8249;</span>");
		} else {
			strHtml.append("<span title=\"第一页\"><a href=\"javascript:" + methodName + "(1," + totalCount + "," + pageSize + ");\">&laquo;</a></span>");
			strHtml.append("<span title=\"上一页\"><a href=\"javascript:" + methodName + "(" + prevPage + "," + totalCount + "," + pageSize + ");\">&#8249;</a></span>");
		}
		if (pageNo != 1) strHtml.append("<span title=\"Page 1\"><a href=\"javascript:" + methodName + "(1," + totalCount + "," + pageSize + ");\">1</a></span>");
		if (pageNo >= 5) strHtml.append("<span>...</span>");
		if (totalPages > pageNo + 2) {
			endPage = pageNo + 2;
		} else {
			endPage = totalPages;
		}
		for (int i = pageNo - 2; i <= endPage; i++) {
			if (i > 0) {
				if (i == pageNo) {
					strHtml.append("<span title=\"Page " + i + "\" class=\"current\">" + i + "</span>");
				} else {
					if (i != 1 && i != totalPages) {
						strHtml.append("<span title=\"Page " + i + "\"><a href=\"javascript:" + methodName + "(" + i + "," + totalCount + "," + pageSize + ");\">" + i + "</a></span>");
					}
				}
			}
		}
		if (pageNo + 3 < totalPages) strHtml.append("<span>...</span>");
		if (pageNo != totalPages) strHtml.append("<span title=\"Page " + totalPages + "\"><a href=\"javascript:" + methodName + "(" + totalPages + "," + totalCount + "," + pageSize + ");\">" + totalPages + "</a></span>");
		if (nextPage > totalPages) {
			strHtml.append("<span title=\"下一页\" class=\"disabled\">&#8250;</span>");
			strHtml.append("<span title=\"最后一页\" class=\"disabled\">&raquo;</span>");
		} else {
			strHtml.append("<span title=\"下一页\"><a href=\"javascript:" + methodName + "(" + nextPage + "," + totalCount + "," + pageSize + ");\">&#8250;</a></span>");
			strHtml.append("<span title=\"最后一页\"><a href=\"javascript:" + methodName + "(" + totalPages + "," + totalCount + "," + pageSize + ");\">&raquo;</a></span>");
		}
		
		//模式5 (输入框)
		strHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>");
		if (totalPages < 1) {
			strHtml.append("<input type=\"text\" name=\"toPage\" value=\"\" class=\"itext\" disabled=\"disabled\">");
			strHtml.append("<input type=\"button\" name=\"go\" value=\"GO\" class=\"ibutton\" disabled=\"disabled\"></option>");
		} else {
			strHtml.append("<input type=\"text\" id=\"pageInput" + showTimes + "\" value=\"" + pageNo + "\" class=\"itext\" title=\"请输入页码\" onkeypress=\"checkPages(this);return formatInputPage(event);\">");
			strHtml.append("<input type=\"button\" name=\"go\" value=\"GO\" class=\"ibutton\" onclick=\"" + methodName + "(checkPages(document.getElementById('pageInput" + showTimes + "').value)," + totalCount + "," + pageSize + ");\"></input>");
			strHtml.append("&nbsp;&nbsp;&nbsp;输入条数:<input type=\"text\" name=\"setPageSize" + showTimes + "\"  onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" value=\"" + pageSize + 
							"\"  class=\"itext\" title=\"请输入条数(1~1000)\" maxlength=\"4\" onkeydown=\"javascript:if(13 == event.keyCode){setPageSize(this," + pageNo + "," + totalCount + ");}\"/>");
		}
		strHtml.append("</span>");
		
		strHtml.append("</div><br />");
		print(strHtml.toString());
		
	}

	private void print(String str) {
		JspWriter out = pageContext.getOut();
		try {
			out.print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 设置属性 */
	public Page getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page pageBean) {
		this.pageBean = pageBean;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getSytleName() {
		return sytleName;
	}

	public void setSytleName(String sytleName) {
		this.sytleName = sytleName;
	}
}
