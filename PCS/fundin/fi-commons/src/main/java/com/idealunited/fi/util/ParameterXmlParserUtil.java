/**
 *  File: ParameterXmlParserUtil.java
 *  Description:
 *  Copyright Corporation. All rights reserved.
 *  Date      Author      Changes
 *  Dec 15, 2011   ch-ma     Create
 *
 */
package com.idealunited.fi.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * payement api parser.
 */
@SuppressWarnings("unchecked")
public class ParameterXmlParserUtil {

	private static Log logger = LogFactory.getLog(ParameterXmlParserUtil.class);

	public static String getNodeText(String parameterXml, String nodeName) {

		if (null == parameterXml) {
			return parameterXml;
		}

		StringBuilder startNode = new StringBuilder("<");
		startNode.append(nodeName);
		startNode.append(">");

		StringBuilder endNode = new StringBuilder("</");
		endNode.append(nodeName);
		endNode.append(">");

		int start = parameterXml.indexOf(startNode.toString());

		int end = parameterXml.indexOf(endNode.toString());

		String xml = null;
		if (-1 != start && -1 != end) {
			xml = parameterXml.substring(start, end + endNode.length());
		}
		
		xml = xml.replaceAll("\\s*", "");

		return xml;
	}

	/**
	 * parser a XML form root node is PayPlatRequestParameter.
	 * 
	 * @param <T>
	 * @param targetClass
	 * @param transferMap
	 *            , if node & property need transfer,you can set elements in
	 *            <code>transferMap</code>.
	 * @param parameterXml
	 * @return
	 */
	public static <T> T parser(Class<T> targetClass,
			Map<String, String> transferMap, String parameterXml) {

		// parameterXml = xmlFormat(parameterXml);

		logger.info("xml data:" + parameterXml);

		if (null == targetClass) {
			return null;
		}

		Document document;
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new ByteArrayInputStream(parameterXml
					.getBytes()));
			return parser(targetClass, transferMap, document.getRootElement());
		} catch (Exception e) {
			logger.error("xml parser error:", e);
		}
		return null;
	}

	/**
	 * 
	 * @param <T>
	 * @param targetClass
	 * @param transferMap
	 * @param parameterXml
	 * @param rootNode
	 * @return
	 */
	public static <T> T parser(Class<T> targetClass,
			Map<String, String> transferMap, String parameterXml,
			String rootNode) {

		// logger.info("xml data:" + parameterXml);

		if (null == targetClass) {
			return null;
		}

		Document document;
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new ByteArrayInputStream(parameterXml
					.getBytes()));
			Element element = document.getRootElement().element(rootNode);
			return parser(targetClass, transferMap, element);
		} catch (Exception e) {
			logger.error("xml parser error:", e);
		}
		return null;
	}

	/**
	 * 
	 * @param <T>
	 * @param targetClass
	 * @param transferMap
	 * @param parameterXml
	 * @param rootNode
	 * @return
	 */
	public static <T> List<T> parserList(Class<T> targetClass,
			Map<String, String> transferMap, String parameterXml,
			String rootNode) {

		// logger.info("xml data:" + parameterXml);

		if (null == targetClass) {
			return null;
		}

		Document document;
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new ByteArrayInputStream(parameterXml
					.getBytes()));
			Element rootEle = document.getRootElement().element(rootNode);
			List<Element> elements = rootEle.elements();
			List<T> targets = null;
			if (null != elements && !elements.isEmpty()) {
				targets = new ArrayList<T>();
				for (Element element : elements) {
					targets.add(parser(targetClass, transferMap, element));
				}
			}
			return targets;
		} catch (Exception e) {
			logger.error("xml parser error:", e);
		}
		return null;
	}

	private static <T> T parser(Class<T> targetClass,
			Map<String, String> transferMap, Element rootElement) {
		try {
			List<Element> para = rootElement.elements();
			Iterator<Element> it = para.iterator();
			T targetObject = targetClass.newInstance();
			BeanWrapper sourceBw = new BeanWrapperImpl(targetObject);
			while (it.hasNext()) {
				Element element = (Element) it.next();
				String property = element.getName();
				if (null != transferMap && !transferMap.isEmpty()) {
					if (null != transferMap.get(property)) {
						property = transferMap.get(property);
					}
				}
				if (isExistsProperty(sourceBw, property)) {
					sourceBw.setPropertyValue(property, element.getText());
				}
			}
			return targetObject;
		} catch (Exception e) {
			logger.error("xml parser error:", e);
		}
		return null;
	}

	private static boolean isExistsProperty(BeanWrapper sourceBw,
			String property) {
		try {
			PropertyDescriptor propertyDescriptor = sourceBw
					.getPropertyDescriptor(property);
			if (null != propertyDescriptor) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static String xmlFormat(String xml) {

		String outStr = xml;
		try {
			SAXReader reader = new SAXReader();

			StringReader in = new StringReader(xml);
			Document doc = reader.read(in);
			OutputFormat formater = OutputFormat.createPrettyPrint();
			formater.setEncoding("utf-8");
			StringWriter out = new StringWriter();
			XMLWriter writer = new XMLWriter(out, formater);
			writer.write(doc);
			outStr = out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outStr;
	}

	public static void main(String[] args) {

		String xml = generateXml("100001", "BX22333");

		String result = getNodeText(xml, "REQUEST_BODY");

		System.out.print(result);

		// parser(Pay2AcctRequest.class, null, xml.toString());
	}

	public static String generateXml(String merchantCode, String bizNo) {

		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<PayPlatRequestParameter>");
		xml.append("<REQUEST_HEADER>");
		xml.append("<MERCHANT_CODE>" + merchantCode + "</MERCHANT_CODE>");
		xml.append("<BIZ_NO>" + bizNo + "</BIZ_NO>");
 
		xml.append("<TOTAL_AMOUNT>" + 2000 + "</TOTAL_AMOUNT>");
		xml.append("<TOTAL_COUNT>" + 2 + "</TOTAL_COUNT>");
 
		xml.append("<REQUEST_TIME>" + "20111223111450" + "</REQUEST_TIME>");
		xml.append("<VERSION>" + "V1.0" + "</VERSION>");
		xml.append("<SIGNVALUE>" + "V1.0" + "</SIGNVALUE>");
		xml.append("</REQUEST_HEADER>");

		 
		xml.append("</PayPlatRequestParameter>");

		return ParameterXmlParserUtil.xmlFormat(xml.toString());
	}
}
