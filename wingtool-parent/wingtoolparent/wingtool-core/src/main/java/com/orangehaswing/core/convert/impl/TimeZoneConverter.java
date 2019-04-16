package com.orangehaswing.core.convert.impl;

import com.orangehaswing.core.convert.AbstractConverter;

import java.util.TimeZone;

/**
 * TimeZone转换器
 * @author Looly
 *
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone>{

	@Override
	protected TimeZone convertInternal(Object value) {
		return TimeZone.getTimeZone(convertToStr(value));
	}

}
