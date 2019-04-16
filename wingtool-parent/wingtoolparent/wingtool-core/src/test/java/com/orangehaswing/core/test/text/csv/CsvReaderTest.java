package com.orangehaswing.core.test.text.csv;

import com.orangehaswing.core.io.resource.ResourceUtil;
import com.orangehaswing.core.text.csv.CsvData;
import com.orangehaswing.core.text.csv.CsvReader;
import com.orangehaswing.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

public class CsvReaderTest {
	
	@Test
	public void readTest() {
		CsvReader reader = new CsvReader();
		CsvData data = reader.read(ResourceUtil.getReader("test.csv", CharsetUtil.CHARSET_UTF_8));
		Assert.assertEquals("关注\"对象\"", data.getRow(0).get(2));
	}
}
