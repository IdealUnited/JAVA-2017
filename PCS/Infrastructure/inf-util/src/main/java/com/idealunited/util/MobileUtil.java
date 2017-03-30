package com.idealunited.util;

/*String ua = request.getHeader("User-Agent");
 下面就是一个基本方法了，可以写在一个util类中，我们是放在了StringUtil.java中
 定义移动端请求的所有可能类型
 方法参数为我们控制器中接收的user——Agent，*/

public class MobileUtil {

	private final static String[] agent = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
		"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
		"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
		"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
		"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
		"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
		"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
		"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
		"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
		"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
		"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
		"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
		"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
		"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
		"Googlebot-Mobile" };
	
	/**
	 * 判断User-Agent 是不是来自于手机
	 * 
	 * @param ua
	 * @return
	 */
	public static boolean checkAgentIsMobile(String ua) {

		boolean flag = false;

		if (!ua.contains("Windows NT")
				|| (ua.contains("Windows NT") && ua
						.contains("compatible; MSIE 9.0;"))
				|| !ua.contains("MicroMessenger")) {

			// 排除 苹果桌面系统
			if (!ua.contains("Windows NT") 
					&& !ua.contains("Macintosh")) {

				for (String item : agent) {

					if (ua.toLowerCase().contains(item)) {

						flag = true;

						break;

					}
				}
			}
		}
		return flag;
	}
}
