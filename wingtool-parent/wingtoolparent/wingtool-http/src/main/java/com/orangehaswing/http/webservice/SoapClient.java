package com.orangehaswing.http.webservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import com.orangehaswing.core.map.MapUtil;
import com.orangehaswing.core.util.CharsetUtil;
import com.orangehaswing.core.util.ObjectUtil;
import com.orangehaswing.core.util.StrUtil;
import com.orangehaswing.core.util.XmlUtil;
import com.orangehaswing.http.HttpRequest;

/**
 * SOAP客户端
 * 
 * @author looly
 * @since 4.5.4
 */
public class SoapClient {

	/** XML消息体的Content-Type */
	private static final String TEXT_XML_CONTENT_TYPE = "text/xml;charset=";

	/** 请求的URL地址 */
	private String url;
	/** 编码 */
	private Charset charset = CharsetUtil.CHARSET_UTF_8;
	/** SOAP消息 */
	private SOAPMessage message;
	/** 消息方法节点 */
	private SOAPBodyElement methodEle;
	/** 应用于方法上的命名空间URI */
	private String namespaceURI;

	/**
	 * 创建SOAP客户端，默认使用soap1.1版本协议
	 * 
	 * @param url WS的URL地址
	 * @return {@link SoapClient}
	 */
	public static SoapClient create(String url) {
		return new SoapClient(url);
	}

	/**
	 * 创建SOAP客户端
	 * 
	 * @param url WS的URL地址
	 * @param protocol 协议，见{@link SoapProtocol}
	 * @return {@link SoapClient}
	 */
	public static SoapClient create(String url, SoapProtocol protocol) {
		return new SoapClient(url, protocol);
	}

	/**
	 * 创建SOAP客户端
	 * 
	 * @param url WS的URL地址
	 * @param protocol 协议，见{@link SoapProtocol}
	 * @param namespaceURI 方法上的命名空间URI
	 * @return {@link SoapClient}
	 * @since 4.5.6
	 */
	public static SoapClient create(String url, SoapProtocol protocol, String namespaceURI) {
		return new SoapClient(url, protocol, namespaceURI);
	}

	/**
	 * 构造，默认使用soap1.1版本协议
	 * 
	 * @param url WS的URL地址
	 */
	public SoapClient(String url) {
		this(url, SoapProtocol.SOAP_1_1);
	}

	/**
	 * 构造
	 * 
	 * @param url WS的URL地址
	 * @param protocol 协议版本，见{@link SoapProtocol}
	 */
	public SoapClient(String url, SoapProtocol protocol) {
		this(url, protocol, null);
	}

	/**
	 * 构造
	 * 
	 * @param url WS的URL地址
	 * @param protocol 协议版本，见{@link SoapProtocol}
	 * @param namespaceURI 方法上的命名空间URI
	 * @since 4.5.6
	 */
	public SoapClient(String url, SoapProtocol protocol, String namespaceURI) {
		this.url = url;
		this.namespaceURI = namespaceURI;
		init(protocol);
	}

	/**
	 * 初始化
	 * 
	 * @param protocol 协议版本枚举，见{@link SoapProtocol}
	 * @return this
	 */
	public SoapClient init(SoapProtocol protocol) {
		// 创建消息工厂
		MessageFactory factory;
		try {
			factory = MessageFactory.newInstance(protocol.getValue());
			// 根据消息工厂创建SoapMessage
			this.message = factory.createMessage();
		} catch (SOAPException e) {
			throw new SoapRuntimeException(e);
		}

		return this;
	}

	/**
	 * 设置编码
	 * 
	 * @param charset 编码
	 * @return this
	 */
	public SoapClient setCharset(Charset charset) {
		this.charset = charset;
		return this;
	}

	/**
	 * 设置Webservice请求地址
	 * 
	 * @param url Webservice请求地址
	 * @return this
	 */
	public SoapClient setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 设置头信息
	 * 
	 * @param name 头信息标签名
	 * @return this
	 */
	public SoapClient setHeader(QName name) {
		return setHeader(name, null, null, null, null);
	}

	/**
	 * 设置头信息
	 * 
	 * @param name 头信息标签名
	 * @param actorURI 中间的消息接收者
	 * @param roleUri Role的URI
	 * @param mustUnderstand 标题项对于要对其进行处理的接收者来说是强制的还是可选的
	 * @param relay relay属性
	 * @return this
	 */
	public SoapClient setHeader(QName name, String actorURI, String roleUri, Boolean mustUnderstand, Boolean relay) {
		SOAPHeader header;
		SOAPHeaderElement ele;
		try {
			header = this.message.getSOAPHeader();
			ele = header.addHeaderElement(name);
			if (StrUtil.isNotBlank(roleUri)) {
				ele.setRole(roleUri);
			}
			if (null != relay) {
				ele.setRelay(relay);
			}
		} catch (SOAPException e) {
			throw new SoapRuntimeException(e);
		}

		if (StrUtil.isNotBlank(actorURI)) {
			ele.setActor(actorURI);
		}
		if (null != mustUnderstand) {
			ele.setMustUnderstand(mustUnderstand);
		}

		return this;
	}

	/**
	 * 设置请求方法
	 * 
	 * @param name 方法名及其命名空间
	 * @param params 参数
	 * @param useMethodPrefix 是否使用方法的命名空间前缀
	 * @return this
	 */
	public SoapClient setMethod(Name name, Map<String, Object> params, boolean useMethodPrefix) {
		return setMethod(new QName(name.getURI(), name.getLocalName(), name.getPrefix()), params, useMethodPrefix);
	}

	/**
	 * 设置请求方法
	 * 
	 * @param name 方法名及其命名空间
	 * @param params 参数
	 * @param useMethodPrefix 是否使用方法的命名空间前缀
	 * @return this
	 */
	public SoapClient setMethod(QName name, Map<String, Object> params, boolean useMethodPrefix) {
		setMethod(name);
		final String prefix = name.getPrefix();
		final SOAPBodyElement methodEle = this.methodEle;
		for (Entry<String, Object> entry : MapUtil.wrap(params)) {
			setParam(methodEle, entry.getKey(), entry.getValue(), prefix);
		}

		return this;
	}

	/**
	 * 设置请求方法<br>
	 * 方法名自动识别前缀，前缀和方法名使用“:”分隔<br>
	 * 当识别到前缀后，自动添加xmlns属性，关联到默认的namespaceURI
	 * 
	 * @param methodName 方法名
	 * @return this
	 */
	public SoapClient setMethod(String methodName) {
		return setMethod(methodName, ObjectUtil.defaultIfNull(this.namespaceURI, XMLConstants.NULL_NS_URI));
	}

	/**
	 * 设置请求方法<br>
	 * 方法名自动识别前缀，前缀和方法名使用“:”分隔<br>
	 * 当识别到前缀后，自动添加xmlns属性，关联到传入的namespaceURI
	 * 
	 * @param methodName 方法名（可有前缀也可无）
	 * @param namespaceURI 命名空间URI
	 * @return this
	 */
	public SoapClient setMethod(String methodName, String namespaceURI) {
		final List<String> methodNameList = StrUtil.split(methodName, ':');
		final QName qName;
		if (2 == methodNameList.size()) {
			qName = new QName(namespaceURI, methodNameList.get(1), methodNameList.get(0));
		} else {
			qName = new QName(namespaceURI, methodName);
		}
		return setMethod(qName);
	}

	/**
	 * 设置请求方法
	 * 
	 * @param name 方法名及其命名空间
	 * @return this
	 */
	public SoapClient setMethod(QName name) {
		try {
			this.methodEle = this.message.getSOAPBody().addBodyElement(name);
		} catch (SOAPException e) {
			throw new SoapRuntimeException(e);
		}

		return this;
	}

	/**
	 * 设置方法参数，使用方法的前缀
	 * 
	 * @param name 参数名
	 * @param value 参数值，可以是字符串或Map或{@link SOAPElement}
	 * @return this
	 */
	public SoapClient setParam(String name, Object value) {
		return setParam(name, value, true);
	}

	/**
	 * 设置方法参数
	 * 
	 * @param name 参数名
	 * @param value 参数值，可以是字符串或Map或{@link SOAPElement}
	 * @param useMethodPrefix 是否使用方法的命名空间前缀
	 * @return this
	 */
	public SoapClient setParam(String name, Object value, boolean useMethodPrefix) {
		setParam(this.methodEle, name, value, useMethodPrefix ? this.methodEle.getPrefix() : null);
		return this;
	}

	/**
	 * 批量设置参数，使用方法的前缀
	 * 
	 * @param params 参数列表
	 * @return this
	 * @since 4.5.6
	 */
	public SoapClient setParams(Map<String, Object> params) {
		return setParams(params, true);
	}

	/**
	 * 批量设置参数
	 * 
	 * @param params 参数列表
	 * @param useMethodPrefix 是否使用方法的命名空间前缀
	 * @return this
	 * @since 4.5.6
	 */
	public SoapClient setParams(Map<String, Object> params, boolean useMethodPrefix) {
		for (Entry<String, Object> entry : MapUtil.wrap(params)) {
			setParam(entry.getKey(), entry.getValue(), useMethodPrefix);
		}
		return this;
	}

	/**
	 * 获取方法节点<br>
	 * 用于创建子节点等操作
	 * 
	 * @return {@link SOAPBodyElement}
	 * @since 4.5.6
	 */
	public SOAPBodyElement getMethodEle() {
		return this.methodEle;
	}

	/**
	 * 获取SOAP消息对象 {@link SOAPMessage}
	 * 
	 * @return {@link SOAPMessage}
	 * @since 4.5.6
	 */
	public SOAPMessage getMessage() {
		return this.message;
	}

	/**
	 * 获取SOAP请求消息
	 * 
	 * @param pretty 是否格式化
	 * @return 消息字符串
	 */
	public String getMsgStr(boolean pretty) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		write(out);
		return pretty ? XmlUtil.format(out.toString()) : out.toString();
	}
	
	/**
	 * 将SOAP消息的XML内容输出到流
	 * 
	 * @param out 输出流
	 * @return this
	 * @since 4.5.6
	 */
	public SoapClient write(OutputStream out) {
		try {
			this.message.writeTo(out);
		} catch (SOAPException | IOException e) {
			throw new SoapRuntimeException(e);
		}
		return this;
	}

	/**
	 * 执行Webservice请求，既发送SOAP内容
	 * 
	 * @return 返回结果
	 */
	public String send() {
		return send(false);
	}

	/**
	 * 执行Webservice请求，既发送SOAP内容
	 * 
	 * @param pretty 是否格式化
	 * @return 返回结果
	 */
	public String send(boolean pretty) {
		String res = HttpRequest.post(this.url)//
				.setFollowRedirects(true).contentType(getXmlContentType())//
				.body(getMsgStr(false))//
				.execute()//
				.body();

		return pretty ? XmlUtil.format(res) : res;
	}

	// -------------------------------------------------------------------------------------------------------- Private method start
	/**
	 * 获取请求的Content-Type，附加编码信息
	 * 
	 * @return 请求的Content-Type
	 */
	private String getXmlContentType() {
		return TEXT_XML_CONTENT_TYPE.concat(this.charset.toString());
	}

	/**
	 * 设置方法参数
	 * 
	 * @param ele 方法节点
	 * @param name 参数名
	 * @param value 参数值
	 * @param prefix 命名空间前缀
	 * @return {@link SOAPElement}子节点
	 */
	@SuppressWarnings("rawtypes")
	private static SOAPElement setParam(SOAPElement ele, String name, Object value, String prefix) {
		final SOAPElement childEle;
		try {
			if (StrUtil.isNotBlank(prefix)) {
				childEle = ele.addChildElement(name, prefix);
			} else {
				childEle = ele.addChildElement(name);
			}
		} catch (SOAPException e) {
			throw new SoapRuntimeException(e);
		}

		if (value instanceof CharSequence) {
			// 单个值
			childEle.setValue(value.toString());
		} else if (value instanceof SOAPElement) {
			// 单个子节点
			try {
				ele.addChildElement((SOAPElement) value);
			} catch (SOAPException e) {
				throw new SoapRuntimeException(e);
			}
		} else if (value instanceof Map) {
			// 多个字节点
			Entry entry;
			for (Object obj : ((Map) value).entrySet()) {
				entry = (Entry) obj;
				setParam(childEle, entry.getKey().toString(), entry.getValue(), prefix);
			}
		}
		return childEle;
	}
	// -------------------------------------------------------------------------------------------------------- Private method end
}
