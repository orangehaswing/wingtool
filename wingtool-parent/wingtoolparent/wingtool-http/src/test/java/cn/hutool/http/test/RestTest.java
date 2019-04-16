package com.orangehaswing.http.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.http.HttpRequest;
import com.orangehaswing.http.HttpUtil;
import com.orangehaswing.json.JSONUtil;

/**
 * Rest类型请求单元测试
 * 
 * @author looly
 *
 */
public class RestTest {

	@Test
	public void contentTypeTest() {
		HttpRequest request = HttpRequest.post("http://localhost:8090/rest/restTest/")//
				.body(JSONUtil.createObj().put("aaa", "aaaValue").put("键2", "值2"));
		Assert.assertEquals("application/json;charset=UTF-8", request.header("Content-Type"));
	}

	@Test
	@Ignore
	public void postTest() {
		HttpRequest request = HttpRequest.post("http://localhost:8090/rest/restTest/")//
				.body(JSONUtil.createObj().put("aaa", "aaaValue").put("键2", "值2"));
		Console.log(request.execute().body());
	}

	@Test
	@Ignore
	public void postTest2() {
		String result = HttpUtil.post("http://localhost:8090/rest/restTest/", JSONUtil.createObj()//
				.put("aaa", "aaaValue").put("键2", "值2").toString());
		Console.log(result);
	}

	@Test
	@Ignore
	public void postTest3() {
		HttpRequest request = HttpRequest.post("http://211.162.39.204:8181/jeesite-simple/a/open/bizGwbnService/test")//
				.body(JSONUtil.createObj().put("aaa", "aaaValue").put("键2", "值2"));
		Console.log(request.execute().body());
	}
}
