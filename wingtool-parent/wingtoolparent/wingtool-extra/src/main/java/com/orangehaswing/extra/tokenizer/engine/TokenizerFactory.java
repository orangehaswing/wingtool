package com.orangehaswing.extra.tokenizer.engine;

import com.orangehaswing.core.util.StrUtil;
import com.orangehaswing.extra.tokenizer.TokenizerEngine;
import com.orangehaswing.extra.tokenizer.TokenizerException;
import com.orangehaswing.extra.tokenizer.engine.analysis.SmartcnEngine;
import com.orangehaswing.extra.tokenizer.engine.ansj.AnsjEngine;
import com.orangehaswing.extra.tokenizer.engine.hanlp.HanLPEngine;
import com.orangehaswing.extra.tokenizer.engine.ikanalyzer.IKAnalyzerEngine;
import com.orangehaswing.extra.tokenizer.engine.jcseg.JcsegEngine;
import com.orangehaswing.extra.tokenizer.engine.jieba.JiebaEngine;
import com.orangehaswing.extra.tokenizer.engine.mmseg.MmsegEngine;
import com.orangehaswing.extra.tokenizer.engine.word.WordEngine;
import com.orangehaswing.log.StaticLog;

/**
 * 简单分词引擎工厂，用于根据用户引入的分词引擎jar，自动创建对应的引擎
 * 
 * @author looly
 *
 */
public class TokenizerFactory {
	/**
	 * 根据用户引入的分词引擎jar，自动创建对应的分词引擎对象
	 * 
	 * @return {@link TokenizerEngine}
	 */
	public static TokenizerEngine create() {
		final TokenizerEngine engine = doCreate();
		StaticLog.debug("Use [{}] Tokenizer Engine As Default.", StrUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine"));
		return engine;
	}

	/**
	 * 根据用户引入的分词引擎jar，自动创建对应的分词引擎对象
	 * 
	 * @return {@link TokenizerEngine}
	 */
	private static TokenizerEngine doCreate() {
		try {
			return new AnsjEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new HanLPEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new IKAnalyzerEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new JcsegEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new JiebaEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new MmsegEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new WordEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new SmartcnEngine();
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		throw new TokenizerException("No tokenizer found ! Please add some tokenizer jar to your project !");
	}
}
