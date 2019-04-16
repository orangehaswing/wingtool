package com.orangehaswing.core.convert.impl;

import com.orangehaswing.core.convert.AbstractConverter;
import com.orangehaswing.core.util.BooleanUtil;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link AtomicBoolean}转换器
 * 
 * @author Looly
 * @since 3.0.8
 */
public class AtomicBooleanConverter extends AbstractConverter<AtomicBoolean> {

	@Override
	protected AtomicBoolean convertInternal(Object value) {
		if (boolean.class == value.getClass()) {
			return new AtomicBoolean((boolean) value);
		}
		if (value instanceof Boolean) {
			return new AtomicBoolean((Boolean) value);
		}
		final String valueStr = convertToStr(value);
		return new AtomicBoolean(BooleanUtil.toBoolean(valueStr));
	}

}
