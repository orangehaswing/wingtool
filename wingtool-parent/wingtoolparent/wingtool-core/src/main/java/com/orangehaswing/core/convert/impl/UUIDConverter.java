package com.orangehaswing.core.convert.impl;

import com.orangehaswing.core.convert.AbstractConverter;

import java.util.UUID;

/**
 * UUID对象转换器转换器
 * 
 * @author Looly
 * @since 4.0.10
 *
 */
public class UUIDConverter extends AbstractConverter<UUID> {

	@Override
	protected UUID convertInternal(Object value) {
		return UUID.fromString(convertToStr(value));
	}

}
