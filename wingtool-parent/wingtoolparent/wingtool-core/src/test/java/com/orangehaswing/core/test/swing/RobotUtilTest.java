package com.orangehaswing.core.test.swing;

import com.orangehaswing.core.io.FileUtil;
import com.orangehaswing.core.swing.RobotUtil;
import org.junit.Ignore;
import org.junit.Test;

public class RobotUtilTest {

	@Test
	@Ignore
	public void captureScreenTest() {
		RobotUtil.captureScreen(FileUtil.file("e:/screen.jpg"));
	}
}
