package com.orangehaswing.setting.test.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.orangehaswing.log.LogFactory;
import com.orangehaswing.log.dialect.console.ConsoleLogFactory;
import com.orangehaswing.setting.dialect.Props;

/**
 * Setting单元测试
 * @author Looly
 *
 */
public class PropsTest {
	
	@Before
	public void init(){
		LogFactory.setCurrentLogFactory(ConsoleLogFactory.class);
	}
	
	@Test
	public void propTest(){
		Props props = new Props("test.properties");
		String user = props.getProperty("user");
		Assert.assertEquals(user, "root");
		
		String driver = props.getStr("driver");
		Assert.assertEquals(driver, "com.mysql.jdbc.Driver");
	}
	
	@Test
	@Ignore
	public void propTestForAbsPAth(){
		Props props = new Props("d:/test.properties");
		String user = props.getProperty("user");
		Assert.assertEquals(user, "root");
		
		String driver = props.getStr("driver");
		Assert.assertEquals(driver, "com.mysql.jdbc.Driver");
	}
}
