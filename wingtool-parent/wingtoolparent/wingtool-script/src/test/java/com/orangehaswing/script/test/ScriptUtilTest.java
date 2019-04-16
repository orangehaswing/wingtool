package com.orangehaswing.script.test;

import javax.script.CompiledScript;
import javax.script.ScriptException;

import org.junit.Test;

import com.orangehaswing.script.ScriptRuntimeException;
import com.orangehaswing.script.ScriptUtil;

/**
 * 脚本单元测试类
 * 
 * @author looly
 *
 */
public class ScriptUtilTest {

	@Test
	public void compileTest() {
		CompiledScript script = ScriptUtil.compile("print('Script test!');");
		try {
			script.eval();
		} catch (ScriptException e) {
			throw new ScriptRuntimeException(e);
		}
	}

	@Test
	public void evalTest() {
		ScriptUtil.eval("print('Script test!');");
	}
}
