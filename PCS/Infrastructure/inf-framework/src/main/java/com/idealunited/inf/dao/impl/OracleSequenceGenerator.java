package com.idealunited.inf.dao.impl;

import javax.sql.DataSource;

public class OracleSequenceGenerator extends BaseSequenceGenerator {

	public OracleSequenceGenerator() {
	}

	public OracleSequenceGenerator(DataSource dataSource, String incrementerName) {
		super(dataSource, incrementerName);
	}

	@Override
	protected String getSequenceQuery() {
		return "select " + getSequenceName() + ".nextval from dual";
	}

}
