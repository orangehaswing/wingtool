package com.orangehaswing.db;

import java.sql.SQLException;
import java.util.List;

import com.orangehaswing.db.Db;
import com.orangehaswing.db.Entity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.core.lang.func.VoidFunc1;
import com.orangehaswing.db.sql.Condition;

/**
 * Db对象单元测试
 * @author looly
 *
 */
public class DbTest {
	
	@Test
	public void findTest() throws SQLException {
		Db.use();
		
		List<Entity> find = Db.use().find(Entity.create("user").set("age", 18));
		Assert.assertEquals("王五", find.get(0).get("name"));
	}
	
	@Test
	public void findByTest() throws SQLException {
		Db.use();
		
		List<Entity> find = Db.use().findBy("user", 
				Condition.parse("age", "> 18"), 
				Condition.parse("age", "< 100")
		);
		for (Entity entity : find) {
			Console.log(entity);
		}
		Assert.assertEquals("unitTestUser", find.get(0).get("name"));
	}
	
	@Test
	@Ignore
	public void txTest() throws SQLException {
		Db.use().tx(new VoidFunc1<Db>() {
			
			@Override
			public void call(Db db) throws SQLException {
				db.insert(Entity.create("user").set("name", "unitTestUser2"));
				db.update(Entity.create().set("age", 79), Entity.create("user").set("name", "unitTestUser2"));
				db.del("user", "name", "unitTestUser2");
			}
		});
	}
}
