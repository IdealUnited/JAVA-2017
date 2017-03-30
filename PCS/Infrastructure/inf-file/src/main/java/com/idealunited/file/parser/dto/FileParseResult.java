/**
 *  <p>File: FileParseResult.java</p>
 *  <p>Description:</p>
 *  <p>Copyright: © 2004-2013 pay.com . All rights reserved.版权所有</p>
 *	<p>Company: </p>
 *  @author zengli
 *  @version 1.0  
 */
package com.idealunited.file.parser.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 * 
 * @author zengli
 * @since 2011-6-1
 * @see
 */
public class FileParseResult {

	// 总记录数
	private int totalRecord;

	private BigDecimal totalDealAmount;
	private BigDecimal totalTransFee;
	private BigDecimal totalSettAmount;
	private List list = new ArrayList();

	private List<String> errorMsg = new ArrayList<String>();

	/**
	 * @return the errorMsg
	 */
	public List<String> getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void addItem(Object obj) {
		list.add(obj);
	}

	public int getTotalRecord() {
		return list.size();
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public BigDecimal getTotalDealAmount() {
		return totalDealAmount;
	}

	public void setTotalDealAmount(BigDecimal totalDealAmount) {
		this.totalDealAmount = totalDealAmount;
	}

	public BigDecimal getTotalTransFee() {
		return totalTransFee;
	}

	public void setTotalTransFee(BigDecimal totalTransFee) {
		this.totalTransFee = totalTransFee;
	}

	public BigDecimal getTotalSettAmount() {
		return totalSettAmount;
	}

	public void setTotalSettAmount(BigDecimal totalSettAmount) {
		this.totalSettAmount = totalSettAmount;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
