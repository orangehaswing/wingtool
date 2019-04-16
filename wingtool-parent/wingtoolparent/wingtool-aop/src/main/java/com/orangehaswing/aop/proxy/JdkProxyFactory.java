package com.orangehaswing.aop.proxy;

import com.orangehaswing.aop.ProxyUtil;
import com.orangehaswing.aop.aspects.Aspect;
import com.orangehaswing.aop.interceptor.JdkInterceptor;

/**
 * JDK实现的切面代理
 * 
 * @author looly
 *
 */
public class JdkProxyFactory extends ProxyFactory{

	@Override
	@SuppressWarnings("unchecked")
	public <T> T proxy(T target, Aspect aspect) {
		return (T) ProxyUtil.newProxyInstance(target.getClass().getClassLoader(), new JdkInterceptor(target, aspect), target.getClass().getInterfaces());
	}
}
