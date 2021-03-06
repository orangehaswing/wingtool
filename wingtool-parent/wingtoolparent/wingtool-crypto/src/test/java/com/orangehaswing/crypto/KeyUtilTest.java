package com.orangehaswing.crypto;

import java.security.KeyPair;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.orangehaswing.crypto.CryptoException;
import com.orangehaswing.crypto.GlobalBouncyCastleProvider;
import com.orangehaswing.crypto.KeyUtil;

public class KeyUtilTest {
	
	/**
	 * 测试关闭BouncyCastle支持时是否会正常抛出异常，即关闭是否有效
	 */
	@Test(expected=CryptoException.class)
	@Ignore
	public void generateKeyPairTest() {
		GlobalBouncyCastleProvider.setUseBouncyCastle(false);
		KeyPair pair = KeyUtil.generateKeyPair("SM2");
		Assert.assertNotNull(pair);
	}
}
