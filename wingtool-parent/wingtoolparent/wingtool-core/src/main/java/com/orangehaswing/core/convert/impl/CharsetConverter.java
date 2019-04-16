package com.orangehaswing.core.convert.impl;

import com.orangehaswing.core.convert.AbstractConverter;
import com.orangehaswing.core.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 编码对象转换器
 * @author Looly
 *
 */
public class CharsetConverter extends AbstractConverter<Charset>{

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
