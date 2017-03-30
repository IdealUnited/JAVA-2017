package com.idealunited.inf.dao;

import java.util.Collections;
import java.util.List;

public class QueryResult<T> implements java.io.Serializable {

	private static final long serialVersionUID = 2698041908587411270L;
	protected List<T> result = null;

	public QueryResult() {

	}

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		if (result == null)
			return Collections.emptyList();
		return result;
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

}
