package com.orangehaswing.extra.tokenizer;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.orangehaswing.core.collection.CollUtil;
import com.orangehaswing.extra.tokenizer.engine.analysis.SmartcnEngine;
import com.orangehaswing.extra.tokenizer.engine.hanlp.HanLPEngine;
import com.orangehaswing.extra.tokenizer.engine.ikanalyzer.IKAnalyzerEngine;
import com.orangehaswing.extra.tokenizer.engine.jcseg.JcsegEngine;
import com.orangehaswing.extra.tokenizer.engine.jieba.JiebaEngine;
import com.orangehaswing.extra.tokenizer.engine.mmseg.MmsegEngine;
import com.orangehaswing.extra.tokenizer.engine.word.WordEngine;

/**
 * 模板引擎单元测试
 * 
 * @author looly
 *
 */
public class TokenizerUtilTest {
	
	String text = "这两个方法的区别在于返回值";

	@Test
	public void createEngineTest() {
		// 默认分词引擎，此处为Ansj
		TokenizerEngine engine = TokenizerUtil.createEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}
	
	@Test
	public void hanlpTest() {
		TokenizerEngine engine = new HanLPEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两 个 方法 的 区别 在于 返回 值", resultStr);
	}
	
	@Test
	public void ikAnalyzerTest() {
		TokenizerEngine engine = new IKAnalyzerEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这两个 方法 的 区别 在于 返回值", resultStr);
	}
	
	@Test
	public void jcsegTest() {
		TokenizerEngine engine = new JcsegEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}
	
	@Test
	public void jiebaTest() {
		TokenizerEngine engine = new JiebaEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两个 方法 的 区别 在于 返回值", resultStr);
	}
	
	@Test
	public void mmsegTest() {
		TokenizerEngine engine = new MmsegEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}
	
	@Test
	public void smartcnTest() {
		TokenizerEngine engine = new SmartcnEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两 个 方法 的 区别 在于 返回 值", resultStr);
	}
	
	@Test
	public void wordTest() {
		TokenizerEngine engine = new WordEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这两个 方法 的 区别 在于 返回值", resultStr);
	}
	
	private void checkResult(Result result) {
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两个 方法 的 区别 在于 返回 值", resultStr);
	}
}
