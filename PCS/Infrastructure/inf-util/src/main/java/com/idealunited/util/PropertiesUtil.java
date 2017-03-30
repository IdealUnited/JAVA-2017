/**
 * 
 */
package com.idealunited.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author chaoyue
 * 
 */
public class PropertiesUtil {

	public static String getValue(String fullFilePath, String key) {

		Properties p = new Properties();
		try {
			InputStream inputStream = new FileInputStream(
					new File(fullFilePath));
			p.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return p.getProperty(key);
	}
}
