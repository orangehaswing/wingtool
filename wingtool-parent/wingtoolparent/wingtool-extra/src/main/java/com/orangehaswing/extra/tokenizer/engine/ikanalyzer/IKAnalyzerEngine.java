package com.orangehaswing.extra.tokenizer.engine.ikanalyzer;

import org.wltea.analyzer.core.IKSegmenter;

import com.orangehaswing.core.util.StrUtil;
import com.orangehaswing.extra.tokenizer.TokenizerEngine;
import com.orangehaswing.extra.tokenizer.Result;

/**
 * IKAnalyzer分词引擎实现<br>
 * 项目地址：https://github.com/yozhao/IKAnalyzer
 * 
 * @author looly
 *
 */
public class IKAnalyzerEngine implements TokenizerEngine {

	private IKSegmenter seg;

	/**
	 * 构造
	 * 
	 */
	public IKAnalyzerEngine() {
		this(new IKSegmenter(null, true));
	}

	/**
	 * 构造
	 * 
	 * @param seg {@link IKSegmenter}
	 */
	public IKAnalyzerEngine(IKSegmenter seg) {
		this.seg = seg;
	}

	@Override
	public Result parse(CharSequence text) {
		this.seg.reset(StrUtil.getReader(text));
		return new IKAnalyzerResult(this.seg);
	}

}
