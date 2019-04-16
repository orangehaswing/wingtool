package com.orangehaswing.extra.template.engine.enjoy;

import org.beetl.core.GroupTemplate;

import com.jfinal.template.source.FileSourceFactory;

import com.orangehaswing.core.io.FileUtil;
import com.orangehaswing.core.lang.Assert;
import com.orangehaswing.core.util.ObjectUtil;
import com.orangehaswing.extra.template.Template;
import com.orangehaswing.extra.template.TemplateConfig;
import com.orangehaswing.extra.template.TemplateConfig.ResourceMode;
import com.orangehaswing.extra.template.TemplateEngine;

import static com.orangehaswing.extra.template.TemplateConfig.ResourceMode.*;

/**
 * Enjoy库的引擎包装
 * 
 * @author looly
 * @since 4.1.10
 */
public class EnjoyEngine implements TemplateEngine {

	private com.jfinal.template.Engine engine;
	private ResourceMode resourceMode;

	// --------------------------------------------------------------------------------- Constructor start
	/**
	 * 默认构造
	 */
	public EnjoyEngine() {
		this(new TemplateConfig());
	}

	/**
	 * 构造
	 * 
	 * @param config 模板配置
	 */
	public EnjoyEngine(TemplateConfig config) {
		this(createEngine(config));
		this.resourceMode = config.getResourceMode();
	}

	/**
	 * 构造
	 * 
	 * @param engine {@link com.jfinal.template.Engine}
	 */
	public EnjoyEngine(com.jfinal.template.Engine engine) {
		this.engine = engine;
	}
	// --------------------------------------------------------------------------------- Constructor end

	@Override
	public Template getTemplate(String resource) {
		if(ObjectUtil.equal(STRING, this.resourceMode)) {
			return EnjoyTemplate.wrap(this.engine.getTemplateByString(resource));
		}
		return EnjoyTemplate.wrap(this.engine.getTemplate(resource));
	}

	/**
	 * 创建引擎
	 * 
	 * @param config 模板配置
	 * @return {@link GroupTemplate}
	 */
	private static com.jfinal.template.Engine createEngine(TemplateConfig config) {
		Assert.notNull(config, "Template config is null !");
		final com.jfinal.template.Engine engine = com.jfinal.template.Engine.create("Hutool-Enjoy-Engine-" + config.toString());
		engine.setEncoding(config.getCharsetStr());

		switch (config.getResourceMode()) {
		case STRING:
			// 默认字符串类型资源:
			break;
		case CLASSPATH:
			engine.setToClassPathSourceFactory();
			engine.setBaseTemplatePath(config.getPath());
			break;
		case FILE:
			engine.setSourceFactory(new FileSourceFactory());
			engine.setBaseTemplatePath(config.getPath());
			break;
		case WEB_ROOT:
			engine.setSourceFactory(new FileSourceFactory());
			engine.setBaseTemplatePath(FileUtil.getAbsolutePath(FileUtil.getWebRoot()));
			break;
		default:
			break;
		}

		return engine;
	}
}
