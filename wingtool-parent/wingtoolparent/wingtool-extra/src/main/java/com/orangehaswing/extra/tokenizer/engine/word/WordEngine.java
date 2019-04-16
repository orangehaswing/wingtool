package com.orangehaswing.extra.tokenizer.engine.word;

import org.apdplat.word.segmentation.Segmentation;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.SegmentationFactory;

import com.orangehaswing.core.util.StrUtil;
import com.orangehaswing.extra.tokenizer.Result;
import com.orangehaswing.extra.tokenizer.TokenizerEngine;

/**
 * Word分词引擎实现<br>
 * 项目地址：https://github.com/ysc/word
 * 
 * @author looly
 *
 */
public class WordEngine implements TokenizerEngine {
	
	private Segmentation segmentation;

	/**
	 * 构造
	 */
	public WordEngine() {
		this(SegmentationAlgorithm.BidirectionalMaximumMatching);
	}
	
	/**
	 * 构造
	 * 
	 * @param algorithm {@link SegmentationAlgorithm}分词算法枚举
	 */
	public WordEngine(SegmentationAlgorithm algorithm) {
		this(SegmentationFactory.getSegmentation(algorithm));
	}
	
	/**
	 * 构造
	 * 
	 * @param segmentation {@link Segmentation}分词实现
	 */
	public WordEngine(Segmentation segmentation) {
		this.segmentation = segmentation;
	}
	
	@Override
	public Result parse(CharSequence text) {
		return new WordResult(this.segmentation.seg(StrUtil.str(text)));
	}

}
