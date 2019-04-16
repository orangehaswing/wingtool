package com.orangehaswing.core.test.util;

import com.orangehaswing.core.util.ClassLoaderUtil;
import org.junit.Assert;
import org.junit.Test;

public class ClassLoaderUtilTest {
	
	@Test
	public void loadClassTest() {
		String name = ClassLoaderUtil.loadClass("java.lang.Thread.State").getName();
		Assert.assertEquals("java.lang.Thread$State", name);
		
		name = ClassLoaderUtil.loadClass("java.lang.Thread$State").getName();
		Assert.assertEquals("java.lang.Thread$State", name);
	}
}
