package com.orangehaswing.core.test.date;

import com.orangehaswing.core.date.DateField;
import org.junit.Assert;
import org.junit.Test;

public class DateFieldTest {
	
	@Test
	public void ofTest() {
		DateField field = DateField.of(11);
		Assert.assertEquals(DateField.HOUR_OF_DAY, field);
		field = DateField.of(12);
		Assert.assertEquals(DateField.MINUTE, field);
	}
}
