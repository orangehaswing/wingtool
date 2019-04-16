package com.orangehaswing.db.dialect.impl;

import com.orangehaswing.db.Page;
import com.orangehaswing.db.dialect.DialectName;
import com.orangehaswing.db.sql.SqlBuilder;
import com.orangehaswing.db.sql.Wrapper;

/**
 * MySQL方言
 * @author loolly
 *
 */
public class MysqlDialect extends AnsiSqlDialect{
	
	public MysqlDialect() {
		wrapper = new Wrapper('`');
	}

	@Override
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		return find.append(" LIMIT ").append(page.getStartPosition()).append(", ").append(page.getPageSize());
	}
	
	@Override
	public DialectName dialectName() {
		return DialectName.MYSQL;
	}
}
