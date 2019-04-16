package com.orangehaswing.core.test.io.file;

import com.orangehaswing.core.io.FileUtil;
import com.orangehaswing.core.io.file.Tailer;
import com.orangehaswing.core.util.CharsetUtil;
import org.junit.Ignore;
import org.junit.Test;

public class TailerTest {
	
	@Test
	@Ignore
	public void tailTest() {
		FileUtil.tail(FileUtil.file("e:/tail.txt"), CharsetUtil.CHARSET_GBK);
	}
	
	@Test
	@Ignore
	public void tailWithLinesTest() {
		Tailer tailer = new Tailer(FileUtil.file("e:/tail.txt"), Tailer.CONSOLE_HANDLER, 2);
		tailer.start();
	}
}
