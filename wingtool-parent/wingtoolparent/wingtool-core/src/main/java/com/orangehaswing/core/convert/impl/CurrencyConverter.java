package com.orangehaswing.core.convert.impl;

import com.orangehaswing.core.convert.AbstractConverter;

import java.util.Currency;

/**
 * 货币{@link Currency} 转换器
 * 
 * @author Looly
 * @since 3.0.8
 */
public class CurrencyConverter extends AbstractConverter<Currency> {

	@Override
	protected Currency convertInternal(Object value) {
		return Currency.getInstance(convertToStr(value));
	}

}
