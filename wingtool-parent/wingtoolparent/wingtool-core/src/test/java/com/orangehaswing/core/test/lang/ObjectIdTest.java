package com.orangehaswing.core.test.lang;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.core.lang.ObjectId;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;

/**
 * ObjectId单元测试
 * 
 * @author looly
 *
 */
public class ObjectIdTest {
	
	@Test
	public void distinctTest() {
		//生成10000个id测试是否重复
		HashSet<String> set = new HashSet<>();
		for(int i = 0; i < 10000; i++) {
			set.add(ObjectId.next());
		}
		
		Assert.assertEquals(10000, set.size());
	}
	
	@Test
	@Ignore
	public void nextTest() {
		Console.log(ObjectId.next());
	}
}
