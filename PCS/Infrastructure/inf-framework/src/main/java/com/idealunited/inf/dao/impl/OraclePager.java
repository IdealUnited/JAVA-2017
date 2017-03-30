package com.idealunited.inf.dao.impl;

import com.idealunited.inf.dao.IPageable;

public class OraclePager implements IPageable {

	public String getLimitString(String sql, int offset, int limit) {
		if (offset <= 0 && limit <= 0) {
			return sql;
		}
		StringBuffer sb = new StringBuffer(sql.length() + 80);
		sb.append("   select * from ( select row_.*, rownum rownum_ from ( ");
		sb.append(sql);
		if (offset > 0) {
			sb.append(" ) row_ where rownum <= ").append(offset + limit)
					.append(") where rownum_ > ").append(offset);
		} else {
			sb.append(" ) row_ where rownum <= ").append(limit).append(")");
		}

		return sb.toString();
	}

	public boolean supportsLimit() {
		return true;
	}
}
