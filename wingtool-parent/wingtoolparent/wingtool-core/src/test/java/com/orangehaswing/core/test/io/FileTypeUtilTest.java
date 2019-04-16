package com.orangehaswing.core.test.io;

import com.orangehaswing.core.io.FileTypeUtil;
import com.orangehaswing.core.io.FileUtil;
import com.orangehaswing.core.lang.Console;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * 文件类型判断单元测试
 * @author Looly
 *
 */
public class FileTypeUtilTest {
	
	@Test
	public void fileTypeUtilTest() {
		File file = FileUtil.file("hutool.jpg");
		String type = FileTypeUtil.getType(file);
		Assert.assertEquals("jpg", type);
		
		FileTypeUtil.putFileType("ffd8ffe000104a464946", "new_jpg");
		String newType = FileTypeUtil.getType(file);
		Assert.assertEquals("new_jpg", newType);
	}
	
	@Test
	@Ignore
	public void emptyTest() {
		File file = FileUtil.file("d:/empty.txt");
		String type = FileTypeUtil.getType(file);
		Console.log(type);
	}
}
