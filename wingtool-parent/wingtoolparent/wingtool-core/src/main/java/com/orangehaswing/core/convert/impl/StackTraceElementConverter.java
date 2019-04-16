package com.orangehaswing.core.convert.impl;

import com.orangehaswing.core.convert.AbstractConverter;
import com.orangehaswing.core.map.MapUtil;
import com.orangehaswing.core.util.ObjectUtil;

import java.util.Map;

/**
 * {@link StackTraceElement} 转换器<br>
 * 只支持Map方式转换
 * 
 * @author Looly
 * @since 3.0.8
 */
public class StackTraceElementConverter extends AbstractConverter<StackTraceElement> {

	@Override
	protected StackTraceElement convertInternal(Object value) {
		if (value instanceof Map) {
			final Map<?, ?> map = (Map<?, ?>) value;

			final String declaringClass = MapUtil.getStr(map, "className");
			final String methodName = MapUtil.getStr(map, "methodName");
			final String fileName = MapUtil.getStr(map, "fileName");
			final Integer lineNumber = MapUtil.getInt(map, "lineNumber");

			return new StackTraceElement(declaringClass, methodName, fileName, ObjectUtil.defaultIfNull(lineNumber, 0));
		}
		return null;
	}

}
