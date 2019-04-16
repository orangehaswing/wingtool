package com.orangehaswing.extra.template.engine.freemarker;

import com.orangehaswing.core.io.FileUtil;
import com.orangehaswing.core.io.IORuntimeException;
import com.orangehaswing.core.util.ClassUtil;
import com.orangehaswing.extra.template.Template;
import com.orangehaswing.extra.template.TemplateConfig;
import com.orangehaswing.extra.template.TemplateEngine;
import com.orangehaswing.extra.template.TemplateException;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;

import java.io.IOException;

/**
 * Beetl模板引擎封装
 * 
 * @author looly
 */
public class FreemarkerEngine implements TemplateEngine {

	private Configuration cfg;

	// --------------------------------------------------------------------------------- Constructor start
	/**
	 * 默认构造
	 */
	public FreemarkerEngine() {
		this(new TemplateConfig());
	}

	/**
	 * 构造
	 * 
	 * @param config 模板配置
	 */
	public FreemarkerEngine(TemplateConfig config) {
		this(createCfg(config));
	}

	/**
	 * 构造
	 * 
	 * @param freemarkerCfg {@link Configuration}
	 */
	public FreemarkerEngine(Configuration freemarkerCfg) {
		this.cfg = freemarkerCfg;
	}
	// --------------------------------------------------------------------------------- Constructor end
	
	@Override
	public Template getTemplate(String resource) {
		try {
			return FreemarkerTemplate.wrap(this.cfg.getTemplate(resource));
		} catch(IOException e) {
			throw new IORuntimeException(e);
		}catch (Exception e) {
			throw new TemplateException(e);
		}
	}

	/**
	 * 创建配置项
	 * 
	 * @param config 模板配置
	 * @return {@link Configuration }
	 */
	private static Configuration createCfg(TemplateConfig config) {
		if (null == config) {
			config = new TemplateConfig();
		}

		final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		cfg.setLocalizedLookup(false);
		cfg.setDefaultEncoding(config.getCharset().toString());

		switch (config.getResourceMode()) {
		case CLASSPATH:
			cfg.setTemplateLoader(new ClassTemplateLoader(ClassUtil.getClassLoader(), config.getPath()));
			break;
		case FILE:
			try {
				cfg.setTemplateLoader(new FileTemplateLoader(FileUtil.file(config.getPath())));
			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
			break;
		case WEB_ROOT:
			 try {
				 cfg.setTemplateLoader(new FileTemplateLoader(FileUtil.file(FileUtil.getWebRoot(), config.getPath())));
			 } catch (IOException e) {
				 throw new IORuntimeException(e);
			 }
			break;
		case STRING:
			cfg.setTemplateLoader(new SimpleStringTemplateLoader());
			break;
		default:
			break;
		}
		
		return cfg;
	}
}
