package com.orangehaswing.core.test.util;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.core.util.RuntimeUtil;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 命令行单元测试
 * @author looly
 *
 */
public class RuntimeUtilTest {

	@Test
	@Ignore
	public void execTest() {
		String str = RuntimeUtil.execForStr("ipconfig");
		Console.log(str);
	}

	@Test
	@Ignore
	public void execCmdTest() {
		String str = RuntimeUtil.execForStr("cmd /c dir");
		Console.log(str);
	}
}
