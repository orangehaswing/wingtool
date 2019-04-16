package com.orangehaswing.aop.proxy;

import com.orangehaswing.aop.aspects.Aspect;
import com.orangehaswing.aop.interceptor.CglibInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * 基于Cglib的切面代理工厂
 * 
 * @author looly
 *
 */
public class CglibProxyFactory extends ProxyFactory{

	@Override
	@SuppressWarnings("unchecked")
	public <T> T proxy(T target, Aspect aspect) {
		final Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new CglibInterceptor(target, aspect));
		return (T) enhancer.create();
	}

}
