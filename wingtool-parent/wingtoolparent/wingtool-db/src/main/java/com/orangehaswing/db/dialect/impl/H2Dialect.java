package com.orangehaswing.db.dialect.impl;

import com.orangehaswing.db.Page;
import com.orangehaswing.db.dialect.DialectName;
import com.orangehaswing.db.sql.SqlBuilder;
import com.orangehaswing.db.sql.Wrapper;

/**
 * H2数据库方言
 * 
 * @author loolly
 *
 */
public class H2Dialect extends AnsiSqlDialect {
	public H2Dialect() {
		wrapper = new Wrapper('"', '"');
	}

	@Override
	public DialectName dialectName() {
		return DialectName.H2;
	}

	@Override
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		// limit A , B 表示：A就是查询的起点位置，B就是你需要多少行。
		return find.append(" limit ").append(page.getStartPosition()).append(" , ").append(page.getPageSize());
	}
}
