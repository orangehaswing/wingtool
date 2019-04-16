package com.orangehaswing.core.test.util;

import com.orangehaswing.core.collection.CollectionUtil;
import com.orangehaswing.core.util.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

public class RandomUtilTest {
	
	@Test
	public void randomEleSetTest(){
		Set<Integer> set = RandomUtil.randomEleSet(CollectionUtil.newArrayList(1, 2, 3, 4, 5, 6), 2);
		Assert.assertEquals(set.size(), 2);
	}
	
	@Test
	public void randomElesTest(){
		List<Integer> result = RandomUtil.randomEles(CollectionUtil.newArrayList(1, 2, 3, 4, 5, 6), 2);
		Assert.assertEquals(result.size(), 2);
	}
	
	@Test
	public void randomDoubleTest() {
		double randomDouble = RandomUtil.randomDouble(0, 1, 0, RoundingMode.HALF_UP);
		Assert.assertTrue(randomDouble <= 1);
	}
}
