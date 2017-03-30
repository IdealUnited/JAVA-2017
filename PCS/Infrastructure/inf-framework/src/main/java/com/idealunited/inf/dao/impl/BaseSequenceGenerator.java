package com.idealunited.inf.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import com.idealunited.inf.dao.ISequenceable;

public abstract class BaseSequenceGenerator implements ISequenceable {

	private DataSource dataSource;

	private String sequenceName;

	protected int paddingLength = 0;

	public BaseSequenceGenerator() {
	}

	public BaseSequenceGenerator(DataSource dataSource, String sequenceName) {
		Assert.notNull(dataSource, "DataSource must not be null");
		Assert.notNull(sequenceName, "Incrementer name must not be null");
		this.dataSource = dataSource;
		this.sequenceName = sequenceName;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getSequenceName() {
		return this.sequenceName;
	}

	public void setPaddingLength(int paddingLength) {
		this.paddingLength = paddingLength;
	}

	public int getPaddingLength() {
		return this.paddingLength;
	}

	public int nextIntValue() throws Exception {
		return (int) getNextKey(null);
	}

	public int nextIntValue(String seqName) throws Exception {
		return (int) getNextKey(seqName);
	}

	public long nextLongValue() throws Exception {
		return getNextKey(null);
	}

	public long nextLongValue(String seqName) throws Exception {
		return getNextKey(seqName);
	}

	public String nextStringValue() {
		return nextStringValue(null);
	}

	public String nextStringValue(String seqName) {
		String s = null;
		try{
			s = Long.toString(getNextKey(seqName));
		}catch(Exception e){
			e.printStackTrace();
		}
		int len = s.length();
		if (len < this.paddingLength) {
			StringBuffer buf = new StringBuffer(this.paddingLength);
			for (int i = 0; i < this.paddingLength - len; i++) {
				buf.append('0');
			}
			buf.append(s);
			s = buf.toString();
		}
		return s;
	}

	public long getNextKey(String seqName) throws Exception {
		Connection con = DataSourceUtils.getConnection(getDataSource());
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
			String sql = (seqName == null) ? getSequenceQuery() : seqName;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getLong(1);
			} else {
				throw new Exception("当前序列不存在");
			}
		} catch (SQLException ex) {
			throw new Exception("无法获取下一流水值", ex);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(stmt);
			DataSourceUtils.releaseConnection(con, getDataSource());
		}
	}

	protected abstract String getSequenceQuery();
}
