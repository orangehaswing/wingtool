package com.orangehaswing.core.test.annotation;

import com.orangehaswing.core.annotation.AnnotationUtil;
import org.junit.Assert;
import org.junit.Test;

public class AnnotationUtilTest {
	
	@Test
	public void getAnnotationValueTest() {
		Object value = AnnotationUtil.getAnnotationValue(ClassWithAnnotation.class, AnnotationForTest.class);
		Assert.assertEquals("测试", value);
	}
	
	@AnnotationForTest("测试")
	class ClassWithAnnotation{
		
	}
}
