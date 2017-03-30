package com.idealunited.inf.dao;

public interface ISequenceable {

	public int nextIntValue() throws Exception;

	public int nextIntValue(String seqName) throws Exception;

	public long nextLongValue() throws Exception;

	public long nextLongValue(String seqName) throws Exception;

	public String nextStringValue();

	public String nextStringValue(String seqName) throws Exception;
}
