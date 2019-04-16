package com.orangehaswing.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Assert;
import org.junit.Test;

import com.orangehaswing.core.io.resource.ResourceUtil;
import com.orangehaswing.crypto.BCUtil;
import com.orangehaswing.crypto.asymmetric.KeyType;
import com.orangehaswing.crypto.asymmetric.RSA;

public class BCUtilTest {
	
	@Test
	public void readPrivateKeyTest() {
		PrivateKey privateKey = BCUtil.readPrivateKey(ResourceUtil.getStream("test_private_key.pem"));
		Assert.assertNotNull(privateKey);
	}
	
	@Test
	public void readPublicKeyTest() {
		PublicKey publicKey = BCUtil.readPublicKey(ResourceUtil.getStream("test_public_key.csr"));
		Assert.assertNotNull(publicKey);
	}
	
	@Test
	public void validateKey() {
		PrivateKey privateKey = BCUtil.readPrivateKey(ResourceUtil.getStream("test_private_key.pem"));
		PublicKey publicKey = BCUtil.readPublicKey(ResourceUtil.getStream("test_public_key.csr"));
		
		RSA rsa = new RSA(privateKey, publicKey);
		String str = "你好，Hutool";//测试字符串
		
		String encryptStr = rsa.encryptBase64(str, KeyType.PublicKey);
		String decryptStr = rsa.decryptStr(encryptStr, KeyType.PrivateKey);
		Assert.assertEquals(str, decryptStr);
	}
}
