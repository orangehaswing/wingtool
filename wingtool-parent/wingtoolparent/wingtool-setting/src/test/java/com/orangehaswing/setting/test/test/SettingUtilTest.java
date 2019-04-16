package com.orangehaswing.setting.test.test;

import org.junit.Assert;
import org.junit.Test;

import com.orangehaswing.setting.SettingUtil;

public class SettingUtilTest {
	
	@Test
	public void getTest() {
		String driver = SettingUtil.get("test").get("demo", "driver");
		Assert.assertEquals("com.mysql.jdbc.Driver", driver);
	}
}
