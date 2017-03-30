/**
 * 
 */
package com.idealunited.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author pony
 * 
 */
public class XMLUtil {

	private Node rootNode;

	private Document doc;

	private String xmlStr;

	public XMLUtil(String xmlStr) {
		this.xmlStr = xmlStr;
		initWithXmlStr();
	}

	/**
	 * @return Returns the doc.
	 */
	public Document getDoc() {
		return doc;
	}

	public void initWithXmlStr() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder build = factory.newDocumentBuilder();
			doc = build.parse(new ByteArrayInputStream(this.xmlStr
					.getBytes("UTF-8")));
			rootNode = doc;
			// doc = doc.getFirstChild();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String inputstream2xml(InputStream io) {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(io);
			return xml2Str(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String xml2Str(Document document) {

		try {
			DOMSource source = new DOMSource(document);
			StringWriter writer = new StringWriter();
			Result result = new StreamResult(writer);
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS,
					"yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			return (writer.getBuffer().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public static Document str2Xml(String xmlStr)
			throws ParserConfigurationException, SAXException, IOException {

		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is);
		return doc;

	}

	public String getProperty(String key) {
		int index = key.lastIndexOf('.');
		String propertyName = key.substring(index + 1);
		Node node = getNode(key, false);
		if (node == null) {
			return "";
		}
		NamedNodeMap attrs = node.getAttributes();
		Node attr = attrs.getNamedItem(propertyName);
		if (attr == null) {
			return "";
		}
		return attr.getNodeValue();
	}

	public String getValue(String key) {
		Node node = getNode(key, true);
		if (node == null) {
			return "";
		}
		Node temp = node.getFirstChild();
		if (null == temp) {
			return "";
		} else {
			return temp.getNodeValue();
		}
	}

	public Node getNode(String key, boolean isGetValue) {
		StringTokenizer st = new StringTokenizer(key, ".");
		Node currentNode = rootNode;
		Node preNode = null;
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			if (null == currentNode) {
				return null;
			}
			if (!isGetValue && i == count - 1) {
				return currentNode;
			}
			preNode = currentNode;
			String nodeName = (String) st.nextElement();
			NodeList nodes = currentNode.getChildNodes();
			int len = nodes.getLength();
			for (int j = 0; j < len; j++) {
				Node node = nodes.item(j);
				if (nodeName.equalsIgnoreCase(node.getNodeName())) {
					currentNode = node;
					break;
				}
			}
			if (currentNode == preNode) {
				return null;
			}
		}

		return currentNode;
	}

	public void setNodeValue(String key, String value) {
		Node node = getNode(key, true);
		Assert.notNull(node, "node must be not null");
		Assert.notNull(value, "node's value must be not null");
		if (node.hasChildNodes())
			node.getFirstChild().setNodeValue(value);
		else
			node.appendChild(doc.createTextNode(value));

	}

	public static String replace(String xml, Map value) {
		int len = xml.length();
		StringBuffer buf = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			char c = xml.charAt(i);
			if (c == '$') {
				i++;
				StringBuffer key = new StringBuffer();
				char temp = xml.charAt(i);
				while (temp != '}') {
					if (temp != '{') {
						key.append(temp);
					}
					i++;
					temp = xml.charAt(i);
				}
				String variable = (String) value.get(key.toString());
				if (null == variable) {
					buf.append("");
				} else {
					buf.append(variable);
				}
			} else {
				buf.append(c);
			}
		}
		return buf.toString();
	}

	public static String bean2xml(Object bin, String rootNode) {
		String xml = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		BeanWrapper bean = new BeanWrapperImpl(bin);
		PropertyDescriptor[] properties = bean.getPropertyDescriptors();
		Document document;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			document.setXmlStandalone(true);
			Element root = document.createElement(rootNode);
			for (PropertyDescriptor propertyDescriptor : properties) {

				Class type = propertyDescriptor.getPropertyType();
				String key = propertyDescriptor.getDisplayName();
				Object value = bean.getPropertyValue(key);
				if (!"class".equals(key)) {
					Element element = document.createElement(key);

					if (List.class.equals(type)) {
						List list = (List) value;
						if (null != list && !list.isEmpty()) {
							for (int i = 0; i < list.size(); i++) {
								Element iElement = document
										.createElement("detail");
								BeanWrapper itemBean = new BeanWrapperImpl(
										list.get(i));
								PropertyDescriptor[] itemProperties = itemBean
										.getPropertyDescriptors();
								for (PropertyDescriptor propertyDescriptor1 : itemProperties) {
									String iteKey = propertyDescriptor1
											.getDisplayName();
									Object itemValue = itemBean
											.getPropertyValue(iteKey);
									if (!"class".equals(iteKey)) {
										Element itemElement = document
												.createElement(iteKey);
										itemElement
												.setTextContent(null == itemValue ? ""
														: itemValue.toString());
										iElement.appendChild(itemElement);
									}

								}
								element.appendChild(iElement);
							}
						}
					} else if (null != value
							&& !StringUtil.isEmpty(String.valueOf(value))) {
						element.setTextContent(String.valueOf(value));
					}

					root.appendChild(element);
				}

			}
			document.appendChild(root);
			xml = xml2Str(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	public static String bean2xml(Object bin) {

		return bean2xml(bin, "response");
	}

	public static <T> T xml2bean(String xml, Class<T> clz) {

		try {
			T targetObject = clz.newInstance();
			Document doc = str2Xml(xml);
			Element root = doc.getDocumentElement();

			BeanWrapper bean = new BeanWrapperImpl(targetObject);
			PropertyDescriptor[] properties = bean.getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				String key = properties[i].getDisplayName();
				
				if("class".equals(key)){
					continue;
				}

				NodeList nodeList = root.getElementsByTagName(key);
				Object value = "";
				if (null != nodeList) {
					Node node = nodeList.item(0);
					Element e = (Element) node;
					value = e.getTextContent();
				}
				if (null != value && bean.isWritableProperty(key)) {

					Class propertyType = bean.getPropertyType(key);
					if (propertyType.equals(String.class)) {

						if (!StringUtil.isEmpty(String.valueOf(value))) {
							bean.setPropertyValue(key, value);
						}
					} else if (propertyType.equals(Date.class)) {
						if (value instanceof Long) {
							bean.setPropertyValue(key,
									DateUtil.parse(((Long) value).longValue()));
						} else {
							bean.setPropertyValue(key, value);
						}
					} else {
						bean.setPropertyValue(key, value);
					}

				}
			}
			return targetObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * xml转json
	 * @param xml
	 * @return
	 */
	public static String xml2JSON(String xml){
        return new XMLSerializer().read(xml).toString();
    }
    /**
     * json转xml
     * @param json
     * @param rootName
     * @param charset
     * @return
     */
	public static String jsonToXml(String json,String rootName,String charset) {
		try {
			XMLSerializer serializer = new XMLSerializer();
			JSON jsonObject = JSONSerializer.toJSON(json);
			serializer.setRootName(rootName);
			serializer.setTypeHintsEnabled(false);
			String pattern = "(\r\n|\r|\n|\n\r)";
			String result = serializer.write(jsonObject);
			result = result.replace("UTF-8", charset);
			result = result.replaceAll(pattern, "");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map< String, Object > xml2map( String xmlStr ) throws DocumentException
	{
		org.dom4j.Document doc = DocumentHelper.parseText( xmlStr );
		return ( Map< String, Object > ) xml2map( doc.getRootElement( ) );
	}

	public static Map< String, Object > xml2map( org.dom4j.Element root )
	{
		Map< String, Object > map = new HashMap< String, Object >( );
		List< org.dom4j.Element > elements = root.elements( );
		if ( elements.size( ) == 0 )
		{
			map.put( root.getName( ), root.getText( ) );
			if ( !root.isRootElement( ) )
			{
				return map;
			}
		}
		else if ( elements.size( ) == 1 )
		{
			org.dom4j.Element element = elements.get( 0 );
			if ( element.isTextOnly( ) )
			{
				map.put( element.getName( ), element.getTextTrim( ) );
			}
			else
			{
				map.put( element.getName( ), xml2map( element ) );
			}
		}
		else if ( elements.size( ) > 1 )
		{
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map< String, org.dom4j.Element > tempMap = new HashMap< String, org.dom4j.Element >( );
			for ( org.dom4j.Element ele : elements )
			{
				tempMap.put( ele.getName( ), ele );
			}
			Set< String > keySet = tempMap.keySet( );
			for ( String string : keySet )
			{
				org.dom4j.Namespace namespace = tempMap.get( string ).getNamespace( );
				List< org.dom4j.Element > elements2 = root.elements( new org.dom4j.QName(
						string, namespace ) );
				// 如果同名的数目大于1则表示要构建list
				if ( elements2.size( ) > 1 )
				{
					List< Object > list = new ArrayList< Object >( );
					for ( org.dom4j.Element ele : elements2 )
					{
						list.add( xml2map( ele ) );
					}
					map.put( string, list );
				}
				else
				{
					// 同名的数量不大于1则直接递归去
					org.dom4j.Element element = elements2.get( 0 );
					if ( element.isTextOnly( ) )
					{
						map.putAll( xml2map( element ) );
					}
					else
					{
						map.put( element.getName( ), xml2map( element ) );
					}
				}
			}
		}

		return map;
	}

	public static void main(String[] args) throws Exception {
		//String a = new String("2323");
		//System.out.println(bean2xml(a, "result"));
		HashMap map = new HashMap();
		HashMap map1 = new HashMap();
		map1.put("name", "第十");
		map1.put("age", "22");
		map1.put("sex", "男");
		map.put("a", "a要");
		map.put("b", "在b");
		map.put("c", "c地");
		map.put("d", "d在");
		map.put("e", "e大");
		map.put("person", map1);
		String xml = jsonToXml(JSonUtil.toJSonString(map),"Result","GBK");
		System.out.println(xml);
		//System.out.println(xml.getBytes().length);
		String responseStr = "A001010101002010805000080150000000000221S001       02201008091710282010080981026055    000000:交易受理成功                                                                                       00000000000000000000000000000<?xml version=\"1.0\" encoding=\"GBK\" ?><Result><Desc>hostNo[58],port[29999]autoTaskServerFlag[S],db4oServerFlag[S],cacheMemServerFlag[C]吕男要在地,currentServerTime:2014-03-26 08:48:11;sysStartTime:2014-03-25 14:51:47</Desc></Result>";
		byte[] bte = responseStr.getBytes("GBK");
		System.out.println(bte.length);
		System.out.println((responseStr.getBytes()).length);
		//System.out.println(StringUtil.subStr(responseStr,222));
		System.out.println(StringUtil.subStringByByte(responseStr, 222, bte.length,"GBK"));
		System.out.println("<?xml version=\"1.0\" encoding=\"GBK\" ?><Result><Desc>hostNo[58],port[29999]autoTaskServerFlag[S],db4oServerFlag[S],cacheMemServerFlag[C],currentServerTime:2014-03-26 08:48:11;sysStartTime:2014-03-25 14:51:47</Desc></Result>".getBytes("GBK").length);
		String xmlStr = StringUtil.subStringByByte(responseStr, 222, bte.length,"GBK");
		System.out.println(xml2map(xmlStr));
		xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?><Result><BECIF>640000039796</BECIF><SEQ_NO>FBS320140901S001</SEQ_NO><NUM>1</NUM><SALE_CCY>RMB</SALE_CCY><BUY_CCY>USD</BUY_CCY><BUY_SELL_FLAG>B</BUY_SELL_FLAG><TOTAL_AMT>20</TOTAL_AMT><HOResultSetFBS003R><TRAN_SEQ_NO>20140901TSN001</TRAN_SEQ_NO><TRAN_AMT>20.00</TRAN_AMT><CLIENT_NO>D001</CLIENT_NO><TYPE>D</TYPE><TRAN_CODE>121010</TRAN_CODE><PAYER_GLOBAL_ID>410520189623547011</PAYER_GLOBAL_ID><PAYER_NAME>个人客户1</PAYER_NAME><TRAN_TYPE>18</TRAN_TYPE><COUNTRY_CODE>CHN</COUNTRY_CODE></HOResultSetFBS003R><HOResultSetFBS003R><TRAN_SEQ_NO>20140901TSN001</TRAN_SEQ_NO><TRAN_AMT>20.00</TRAN_AMT><CLIENT_NO>D001</CLIENT_NO><TYPE>D</TYPE><TRAN_CODE>121010</TRAN_CODE><PAYER_GLOBAL_ID>410520189623547011</PAYER_GLOBAL_ID><PAYER_NAME>个人客户1</PAYER_NAME><TRAN_TYPE>18</TRAN_TYPE><COUNTRY_CODE>CHN</COUNTRY_CODE></HOResultSetFBS003R></Result>";
		System.out.println(xml2map(xmlStr));
		//System.out.println(responseStr.substring(221));
		//System.out.println(xml2JSON(jsonToXml(JSonUtil.toJSonString(map),"Result")));
	}
}
