/**
 * ====================================================
 * Created on [2006-8-8 下午04:00:59] by Administrator
/**
 *  File: Page.java
 *  Description:
 *  Copyright © 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-7-15   terry_ma     Create
 *
 */
package com.idealunited.inf.dao;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class Page<T> extends QueryResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8007944048337473098L;

	// 公共变量
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	public static final int DEFUALT_PAGESIZE = 50;

	protected int totalCount = 0;

	// 记录总数
	private Integer totalRecord = new Integer(0);

	// 页码
	private Integer pageNo = new Integer(1);
	// 页码
	private Integer targetPage = new Integer(1);

	// 默认每页显示记录数
	private Integer pageSize = new Integer(DEFUALT_PAGESIZE);

	// 分页参数
	protected String orderBy = null;
	protected String order = ASC;
	protected boolean autoCount = true;

	/**
	 * @return Returns the page_size.
	 */
	public int getPageSize() {
		return pageSize.intValue();
	}

	/**
	 * @param pageSize
	 *            The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = new Integer(pageSize);
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return Returns the totalPage.
	 */
	public int getTotalPage() {
		if (totalRecord.intValue() <= 0) {
			return 0;
		} else {
			return ((totalRecord.intValue() - 1) / pageSize.intValue()) + 1;
		}
	}

	/**
	 * @return Returns the totalRecord.
	 */
	public int getTotalRecord() {
		return totalRecord.intValue();
	}

	/**
	 * @param totalRecord
	 *            The totalRecord to set.
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = new Integer(totalRecord);
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize);
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPage());
	}

	/**
	 * 取得下页的页号,序号从1开始.
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号,序号从1开始.
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	/**
	 * 取得当前记录索引.
	 */
	public int currentRecordIndex() {
		return pageSize * (pageNo - 1);
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔,仅在Criterion查询时有效.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段.多个排序字段时用','分隔.仅在Criterion查询时有效.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 是否已设置排序字段,仅在Criterion查询时有效.
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(orderBy);
	}

	// 构造函数

	public Page() {
		super();
	}

	public Page(final int pageSize) {
		setPageSize(pageSize);
	}

	public Page(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		this.autoCount = autoCount;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(String pageNo) {
		if (StringUtils.isEmpty(pageNo) || "undefined".equalsIgnoreCase(pageNo)
				|| Integer.parseInt(pageNo) < 1)
			this.pageNo = 1;
		else
			this.pageNo = Integer.parseInt(pageNo);
	}

	/**
	 * 获得排序方向,默认为asc,仅在Criterion查询时有效.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向,仅在Criterion查询时有效.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		// 检查order字符串的合法值
		String[] orders = StringUtils.split(order, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equalsIgnoreCase(DESC, orderStr)
					&& !StringUtils.equalsIgnoreCase(ASC, orderStr))
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
		}

		this.order = order.toLowerCase();
	}

	/**
	 * 取得分页参数的组合字符串. 将多个分页参数组合成一个字符串方便在页面上的传递,格式为pageNo|orderBy|order.
	 */
	public String getPageRequest() {
		return getPageNo() + "|" + StringUtils.defaultString(getOrderBy())
				+ "|" + getOrder();
	}

	/**
	 * 设置分页参数的组合字符串. 将多个分页参数组合成一个字符串方便在页面上的传递,格式为pageNo|orderBy|order.
	 */
	public void setPageRequest(final String pageRequest) {

		if (StringUtils.isBlank(pageRequest))
			return;

		String[] params = StringUtils.splitPreserveAllTokens(pageRequest, '|');

		if (StringUtils.isNumeric(params[0])) {
			setPageNo(Integer.valueOf(params[0]));
		}

		if (StringUtils.isNotBlank(params[1])) {
			setOrderBy(params[1]);
		}

		if (StringUtils.isNotBlank(params[2])) {
			setOrder(params[2]);
		}
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数,默认为false,仅在Criterion查询时有效.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数,仅在Criterion查询时有效.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数,默认值为-1.
	 */
	public int getTotalPages() {
		if (totalCount <= 0)
			return 1;

		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 取得反转的排序方向. 如果有以','分隔的多个排序方向,逐一进行反转.
	 */
	public String getInverseOrder() {
		String[] orders = StringUtils.split(order, ',');

		for (int i = 0; i < orders.length; i++) {
			if (DESC.equals(orders[i])) {
				orders[i] = ASC;
			} else {
				orders[i] = DESC;
			}
		}
		return StringUtils.join(orders);
	}

	/**
	 * @return Returns the targetPage.
	 */
	public int getTargetPage() {
		return targetPage.intValue();
	}

	/**
	 * @param targetPage
	 *            The targetPage to set.
	 */
	public void setTargetPage(int targetPage) {
		this.targetPage = new Integer(targetPage);
	}

	/**
	 * 取得总记录数,默认值为-1.
	 */
	public int getTotalCount() {
		if (totalCount < 0)
			return 0;
		else
			return totalCount;
	}

	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
		this.totalRecord = totalCount;
	}

	public void setTotalCount(final String totalCount) {
		if (StringUtils.isEmpty(totalCount)
				|| "undefined".equalsIgnoreCase(totalCount))
			this.totalCount = 0;
		else
			this.totalCount = Integer.parseInt(totalCount);
	}

	@Override
	public String toString() {
		return "Page [totalCount=" + totalCount + ", totalRecord="
				+ totalRecord + ", pageNo=" + pageNo + ", targetPage="
				+ targetPage + ", pageSize=" + pageSize + ", orderBy="
				+ orderBy + ", order=" + order + ", autoCount=" + autoCount
				+ "]";
	}
}
