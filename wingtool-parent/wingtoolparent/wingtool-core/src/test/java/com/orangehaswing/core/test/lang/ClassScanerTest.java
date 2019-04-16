package com.orangehaswing.core.test.lang;

import com.orangehaswing.core.lang.ClassScaner;
import com.orangehaswing.core.lang.Console;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

public class ClassScanerTest {
	
	@Test
	@Ignore
	public void scanTest() {
		ClassScaner scaner = new ClassScaner("com.orangehaswing.core.util.StrUtil", null);
		Set<Class<?>> set = scaner.scan();
		for (Class<?> clazz : set) {
			Console.log(clazz.getName());
		}
	}
}
