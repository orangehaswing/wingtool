package com.orangehaswing.core.test.text.csv;

import com.orangehaswing.core.io.FileUtil;
import com.orangehaswing.core.lang.Assert;
import com.orangehaswing.core.text.csv.*;
import com.orangehaswing.core.util.CharsetUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class CsvUtilTest {
	
	@Test
	public void readTest() {
		CsvReader reader = CsvUtil.getReader();
		//从文件中读取CSV数据
		CsvData data = reader.read(FileUtil.file("test.csv"));
		List<CsvRow> rows = data.getRows();
		for (CsvRow csvRow : rows) {
			Assert.notEmpty(csvRow.getRawList());
		}
	}
	
	@Test
	@Ignore
	public void writeTest() {
		CsvWriter writer = CsvUtil.getWriter("e:/testWrite.csv", CharsetUtil.CHARSET_UTF_8);
		writer.write(
				new String[] {"a1", "b1", "c1"}, 
				new String[] {"a2", "b2", "c2"}, 
				new String[] {"a3", "b3", "c3"}
		);
	}
	
}
