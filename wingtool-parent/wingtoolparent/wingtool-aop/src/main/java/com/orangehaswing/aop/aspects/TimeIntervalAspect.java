package com.orangehaswing.aop.aspects;

import java.lang.reflect.Method;

import com.orangehaswing.core.date.TimeInterval;
import com.orangehaswing.core.lang.Console;

/**
 * 通过日志打印方法的执行时间的切面
 * @author Looly
 *
 */
public class TimeIntervalAspect extends SimpleAspect{

	private TimeInterval interval = new TimeInterval();

	@Override
	public boolean before(Object target, Method method, Object[] args) {
		interval.start();
		return true;
	}
	
	@Override
	public boolean after(Object target, Method method, Object[] args) {
		Console.log("Method [{}.{}] execute spend [{}]ms", target.getClass().getName(), method.getName(), interval.intervalMs());
		return true;
	}
}
