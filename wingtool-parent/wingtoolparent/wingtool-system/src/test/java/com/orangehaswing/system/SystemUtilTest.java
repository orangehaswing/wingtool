package com.orangehaswing.system;

import com.orangehaswing.system.JavaInfo;
import com.orangehaswing.system.SystemUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SystemUtilTest {
	
	@Test
	@Ignore
	public void dumpTest() {
		SystemUtil.dumpSystemInfo();
	}
	
	@Test
	public void getCurrentPidTest() {
		long pid = SystemUtil.getCurrentPID();
		Assert.assertTrue(pid > 0);
	}
	
	@Test
	public void getJavaInfoTest() {
		JavaInfo javaInfo = SystemUtil.getJavaInfo();
		Assert.assertNotNull(javaInfo);
	}
}
