package com.orangehaswing.core.test.util;

import com.orangehaswing.core.clone.CloneSupport;
import com.orangehaswing.core.collection.CollUtil;
import com.orangehaswing.core.util.ObjectUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ObjectUtilTest {

	@Test
	public void cloneTest() {
		Obj obj = new Obj();
		Obj obj2 = ObjectUtil.clone(obj);
		Assert.assertEquals("OK", obj2.doSomeThing());
	}

	static class Obj extends CloneSupport<Obj> {
		public String doSomeThing() {
			return "OK";
		}
	}

	@Test
	public void toStringTest() {
		ArrayList<String> strings = CollUtil.newArrayList("1", "2");
		String result = ObjectUtil.toString(strings);
		Assert.assertEquals("[1, 2]", result);
	}
}
