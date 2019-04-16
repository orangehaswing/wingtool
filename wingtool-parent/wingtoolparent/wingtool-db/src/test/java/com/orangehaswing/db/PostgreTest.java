package com.orangehaswing.db;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.db.Db;
import com.orangehaswing.db.Entity;
import com.orangehaswing.db.Page;
import com.orangehaswing.db.PageResult;

/**
 * PostgreSQL 单元测试
 * 
 * @author looly
 *
 */
public class PostgreTest {

	@Test
	@Ignore
	public void insertTest() throws SQLException {
		for (int id = 100; id < 200; id++) {
			Db.use("postgre").insert(Entity.create("user")//
					.set("id", id)//
					.set("name", "测试用户" + id)//
			);
		}
	}

	@Test
	@Ignore
	public void pageTest() throws SQLException {
		PageResult<Entity> result = Db.use("postgre").page(Entity.create("user"), new Page(2, 10));
		for (Entity entity : result) {
			Console.log(entity.get("id"));
		}
	}
}
