package com.orangehaswing.extra.ftp;

import com.orangehaswing.core.exceptions.ExceptionUtil;
import com.orangehaswing.core.util.StrUtil;

/**
 * Ftp异常
 * 
 * @author xiaoleilu
 */
public class FtpException extends RuntimeException {
	private static final long serialVersionUID = -8490149159895201756L;

	public FtpException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public FtpException(String message) {
		super(message);
	}

	public FtpException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public FtpException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public FtpException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
