package com.orangehaswing.log.test;

import org.junit.Test;

import com.orangehaswing.log.Log;
import com.orangehaswing.log.LogFactory;
import com.orangehaswing.log.level.Level;

/**
 * 日志门面单元测试
 * @author Looly
 *
 */
public class LogTest {
	
	@Test
	public void logTest(){
		Log log = LogFactory.get();
		// 自动选择日志实现
		log.debug("This is {} log", Level.DEBUG);
		log.info("This is {} log", Level.INFO);
		log.warn("This is {} log", Level.WARN);
		
//		Exception e = new Exception("test Exception");
//		log.error(e, "This is {} log", Level.ERROR);
	}
}
