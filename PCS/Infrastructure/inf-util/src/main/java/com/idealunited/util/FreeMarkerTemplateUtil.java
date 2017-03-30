package com.idealunited.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Template;

public class FreeMarkerTemplateUtil {

	private static Log log = LogFactory.getLog(FreeMarkerTemplateUtil.class);

	public static String templateParser(String templateContent,
			Map<String, String> paraMap) {
		try {
			StringReader reader = new StringReader(templateContent);
			Template template;
			template = new Template("", reader, null);
			StringWriter out = new StringWriter();

			// 如果传入的content 为空，则直接返回模板内容
			if (paraMap != null) {
				template.process(paraMap, out);

				String result = out.toString();
				if (log.isDebugEnabled()) {
					log.debug("template content is:[" + result + "]");
				}
				return result;
			} else {
				return templateContent;
			}
		} catch (Exception e) {
			log.error(e);
			log.error("templateContent = " + templateContent + "\ncontent = "
					+ paraMap);
			e.printStackTrace();
		}
		return null;
	}
}
