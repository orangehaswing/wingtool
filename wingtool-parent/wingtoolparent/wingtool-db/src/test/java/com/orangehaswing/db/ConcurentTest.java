package com.orangehaswing.db;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.orangehaswing.core.collection.CollectionUtil;
import com.orangehaswing.core.lang.Console;
import com.orangehaswing.core.thread.ThreadUtil;
import com.orangehaswing.db.Db;
import com.orangehaswing.db.DbRuntimeException;
import com.orangehaswing.db.Entity;
import com.orangehaswing.db.handler.EntityListHandler;

/**
 * SqlRunner线程安全测试
 * 
 * @author looly
 *
 */
@Ignore
public class ConcurentTest {
	
	private Db db;
	
	@Before
	public void init() {
		db = Db.use("test");
	}
	
	@Test
	public void findTest() {
		for(int i = 0; i < 10000; i++) {
			ThreadUtil.execute(new Runnable() {
				@Override
				public void run() {
					List<Entity> find = null;
					try {
						find = db.find(CollectionUtil.newArrayList("name AS name2"), Entity.create("user"), new EntityListHandler());
					} catch (SQLException e) {
						throw new DbRuntimeException(e);
					}
					Console.log(find);
				}
			});
		}
		
		//主线程关闭会导致连接池销毁，sleep避免此情况引起的问题
		ThreadUtil.sleep(5000);
	}
}
