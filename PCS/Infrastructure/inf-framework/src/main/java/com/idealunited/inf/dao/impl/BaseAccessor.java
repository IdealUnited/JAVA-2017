package com.idealunited.inf.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.IPageable;
import com.idealunited.inf.dao.ISequenceable;

public abstract class BaseAccessor<E> extends BaseDAOImpl<E> implements
		ISequenceable, IPageable {

	protected Log logger = LogFactory.getLog(getClass());
	private IPageable pageGenerator;
	private ISequenceable sequenceGenerator;

	@Override
	public int nextIntValue() throws Exception {
		return sequenceGenerator.nextIntValue();
	}

	@Override
	public int nextIntValue(String seqName) throws Exception {
		return sequenceGenerator.nextIntValue(seqName);
	}

	@Override
	public long nextLongValue(String seqName) throws Exception {
		return sequenceGenerator.nextLongValue(seqName);
	}

	@Override
	public long nextLongValue() throws Exception {
		return sequenceGenerator.nextLongValue();
	}

	@Override
	public String nextStringValue() {
		return sequenceGenerator.nextStringValue();
	}

	@Override
	public String nextStringValue(String seqName) throws Exception {
		return sequenceGenerator.nextStringValue(seqName);
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return pageGenerator.getLimitString(sql, offset, limit);
	}

	@Override
	public boolean supportsLimit() {
		return pageGenerator.supportsLimit();
	}

	public IPageable getPageGenerator() {
		return pageGenerator;
	}

	public void setPageGenerator(IPageable pageGenerator) {
		this.pageGenerator = pageGenerator;
	}

	public ISequenceable getSequenceGenerator() {
		return sequenceGenerator;
	}

	public void setSequenceGenerator(ISequenceable sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}
}
