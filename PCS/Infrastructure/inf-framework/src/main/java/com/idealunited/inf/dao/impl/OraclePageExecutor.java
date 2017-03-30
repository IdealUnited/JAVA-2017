package com.idealunited.inf.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.idealunited.inf.dao.IPageable;

public class OraclePageExecutor extends SqlExecutor {
	private Log logger = LogFactory.getLog(OraclePageExecutor.class);

	private IPageable dialect;

	private boolean enableLimit = true;
	private boolean enableHint = false;

	@Override
	public void executeQuery(StatementScope request, Connection conn,
			String sql, Object[] parameters, int skipResults, int maxResults,
			RowHandlerCallback callback) throws SQLException {
		// 获取带结果集缓存提示的SQL语句
		// String cacheHintSql = modifySqlWithResultCache(sql);

		if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)
				&& supportsLimit()) {
			sql = dialect.getLimitString(sql, skipResults, maxResults);
			skipResults = NO_SKIPPED_RESULTS;
			maxResults = NO_MAXIMUM_RESULTS;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("执行查询语句\n" + sql);
		}
		super.executeQuery(request, conn, sql, parameters, skipResults,
				maxResults, callback);
	}

	public int executeUpdate(StatementScope statementScope, Connection conn,
			String sql, Object[] parameters) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("执行更新语句\n" + sql);
		}
		return super.executeUpdate(statementScope, conn, sql, parameters);
	}

	public void executeQueryProcedure(StatementScope statementScope,
			Connection conn, String sql, Object[] parameters, int skipResults,
			int maxResults, RowHandlerCallback callback) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("执行存储过程\n" + sql);
		}
		super.executeQueryProcedure(statementScope, conn, sql, parameters,
				skipResults, maxResults, callback);
	}

	private String modifySqlWithResultCache(String sql) {
		if (this.isEnableHint() == false) {
			return sql;
		}
		sql = sql.toLowerCase();
		return StringUtils.replace(sql, "select", "select /*+ result_cache*/",
				1);
	}

	public boolean supportsLimit() {
		if (enableLimit && dialect != null) {
			return dialect.supportsLimit();
		}
		return false;
	}

	public void setDialect(IPageable dialect) {
		this.dialect = dialect;
	}

	public boolean isEnableHint() {
		return enableHint;
	}

	public void setEnableHint(boolean enableHint) {
		this.enableHint = enableHint;
	}

	public boolean isEnableLimit() {
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.enableLimit = enableLimit;
	}

}
