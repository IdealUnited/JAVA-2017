package com.idealunited.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Utilities.
 */
public class StringUtil {
	private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private StringUtil() {
		// util class, prevent from new instance
	}

	public static boolean isNull(Object object) {
		if (object instanceof String) {
			return StringUtil.isEmpty(object.toString());
		}
		return object == null;
	}

	/**
	 * Checks if string is null or empty.
	 * 
	 * @param value
	 *            The string to be checked
	 * @return True if string is null or empty, otherwise false.
	 */
	public static boolean isEmpty(final String value) {
		return value == null || value.trim().length() == 0
				|| "null".endsWith(value);
	}
	
	/**
	 * Converts <code>null</code> to empty string, otherwise returns it
	 * directly.
	 * 
	 * @param string
	 *            The nullable string
	 * @return empty string if passed in string is null, or original string
	 *         without any change
	 */
	public static String null2String(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public static String null2String(String str) {
		return str == null ? "" : str;
	}

	/**
	 * Converts string from GB2312 encoding ISO8859-1 (Latin-1) encoding.
	 * 
	 * @param gbString
	 *            The string of GB1212 encoding
	 * @return New string of ISO8859-1 encoding
	 */
	public static String iso2Gb(String gbString) {
		if (gbString == null)
			return null;
		String outString = "";
		try {
			byte[] temp = null;
			temp = gbString.getBytes("ISO8859-1");
			outString = new String(temp, "GB2312");
		} catch (java.io.UnsupportedEncodingException e) {
			// ignore it as no way to convert between these two encodings
		}
		return outString;
	}

	/**
	 * Converts string from ISO8859-1 encoding to UTF-8 encoding.
	 * 
	 * @param isoString
	 *            String of ISO8859-1 encoding
	 * @return New converted string of UTF-8 encoding
	 */
	public static String iso2Utf(String isoString) {
		if (isoString == null)
			return null;
		String outString = "";
		try {
			byte[] temp = null;
			temp = isoString.getBytes("ISO8859-1");
			outString = new String(temp, "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {

		}
		return outString;
	}

	/**
	 * Converts string from platform default encoding to GB2312.
	 * 
	 * @param inString
	 *            String in platform default encoding
	 * @return New string in GB2312 encoding
	 */
	public static String str2Gb(String inString) {
		if (inString == null)
			return null;
		String outString = "";
		try {
			byte[] temp = null;
			temp = inString.getBytes();
			outString = new String(temp, "GB2312");
		} catch (java.io.UnsupportedEncodingException e) {

		}
		return outString;
	}

	/**
	 * Insert "0" in front of <em>dealCode</em> if its length is less than 3.
	 * 
	 * @param dealCode
	 *            The dealCode to be filled with "0" at the beginning
	 * @return New string with "0" filled
	 */
	public static String fillZero(String dealCode) {
		String zero = "";
		if (dealCode.length() < 3) {
			for (int i = 0; i < (3 - dealCode.length()); i++) {
				zero += "0";
			}
		}
		return (zero + dealCode);
	}

	public static String fillZero(String value, int len) {
		if (StringUtil.isNull(value) || len <= 0) {
			throw new IllegalArgumentException("参数不正确");
		}
		String zero = "";
		int paramLen = value.trim().length();
		if (paramLen < len) {
			for (int i = 0; i < len - paramLen; i++) {
				zero += "0";
			}
		}
		return (zero + value);
	}
	/**
	 * 填充指定字节数的字符
	 * @param value
	 * @param mark 单字节字符
	 * @param flag true:左补;false 右补
	 * @param len 字节数
	 * @param charset 字符集
	 * @return
	 * @throws UnsupportedEncodingException  
	 */
	public static String fillZero(String value ,String mark ,boolean flag
			,int len ,String charset) {
		StringBuffer sb = new StringBuffer();
		String str = value;
		try{
			if (StringUtil.isNull(value)) {
				for (int i = 0; i < len; i++) {
					sb.append(mark);
				}
				str = sb.toString();
			}else{
				//int paramLen = value.trim();
				byte[] bt = value.trim().getBytes(charset);
				int paramLen = bt.length;
				if (paramLen < len) {
					for (int i = 0; i < len - paramLen; i++) {
						sb.append(mark);
					}
					if(flag){
						str = sb.toString() + value;
					}else{
						str = value + sb.toString();
					}
				}
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return str;
	}
	/**
	 * 填充指定个数的字符
	 * @param value
	 * @param mark
	 * @param flag true:左补;false 右补
	 * @param len
	 * @return
	 */
	public static String fillZero(String value,String mark,boolean flag,int len){
		StringBuffer sb = new StringBuffer();
		if(!StringUtil.isEmpty(value)){
			value = value.trim();
		}
		String str = value;
		if (StringUtil.isNull(value)) {
			for (int i = 0; i < len; i++) {
				sb.append(mark);
			}
			str = sb.toString();
		}else{
			int paramLen = value.trim().length();
			if (paramLen < len) {
				for (int i = 0; i < len - paramLen; i++) {
					sb.append(mark);
				}
				if(flag){
					str = sb.toString() + value;
				}else{
					str = value + sb.toString();
				}
			}
		}
		return str;
	}
	public static String convertAmount(String amount) {
		String str = String.valueOf(Double.parseDouble(amount));
		int pos = str.indexOf(".");
		int len = str.length();
		if (len - pos < 3) {
			return str.substring(0, pos + 2) + "0";
		} else {
			return str.substring(0, pos + 3);
		}
	}

	/**
	 * to 10Decrypt
	 */
	public static String to10(String opStr) {
		String zm = "#123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int lenOfOp = opStr.length();
		long result = 0;
		String indexOfOp;
		int js;
		for (int i = 0; i < lenOfOp; i++) {
			indexOfOp = opStr.substring(i, i + 1);
			js = zm.indexOf(indexOfOp);
			result = result * 36 + js;
		}
		// 补充到12位
		String jg = String.valueOf(result);
		int bc = 12 - jg.length();
		String jgq = "";
		for (int j = 0; j < bc; j++) {
			jgq += "0";
		}
		return jgq + jg;
	}

	/**
	 * to 36Encrypt
	 */
	public static String to36(String originalStr) {

		long oVal = Long.parseLong(originalStr);
		long shang;
		int yushu;
		String result = "";
		String zm = "#123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 1; i < 9; i++) {
			shang = oVal / 36;
			yushu = (int) (oVal % 36);
			result = zm.substring(yushu, yushu + 1) + result;
			oVal = shang;
		}

		return result;

	}
	
	/**
	 * 线下充值号为8位，其中只允许英文和数字，并且英文字母均为大写。
	 * 剔除B,I,O,Q,S,Z这几个容易混淆的字母，总共可以由1-9和剩下20个大写字母组成。
	 * @param originalStr
	 * @return
	 */
	public static String to36shortly(String originalStr) {
		long oVal = Long.parseLong(originalStr);
		long shang;
		int yushu;
		String result = "";
		String zm = "123456789ACDEFGHJKLMNPRTUVWXY";
		for (int i = 1; i < 9; i++) {
			shang = oVal / 36;
			yushu = (int) (oVal % 29);
			result = zm.substring(yushu, yushu + 1) + result;
			oVal = shang;
		}

		return result;
	}

	/**
	 * Encrypt deal Id : 2 bits year,10 bits sequence
	 */
	public static String encDealId(String dealid) {
		if (dealid.length() != 23)
			return "notval";
		String ny = dealid.substring(5, 7);
		String sq = dealid.substring(11, 21);

		return to36(ny + sq);
	}

	/**
	 * Decrypt deal Id : 12 bits
	 */
	public static String decDealId(String opStr) {
		return to10(opStr);
	}

	/**
	 * 数字转换为大写字母
	 * 
	 * @param money
	 *            format example: 100.00
	 * @return
	 */
	public static String[] numToWords(String money) {
		int j = money.length();
		String[] str = new String[j];
		for (int i = 0; i < j; i++) {
			switch (money.charAt(i)) {
			case '0':
				str[i] = "零";
				continue;
			case '1':
				str[i] = "壹";
				continue;
			case '2':
				str[i] = "贰";
				continue;
			case '3':
				str[i] = "叁";
				continue;
			case '4':
				str[i] = "肆";
				continue;
			case '5':
				str[i] = "伍";
				continue;
			case '6':
				str[i] = "陆";
				continue;
			case '7':
				str[i] = "柒";
				continue;
			case '8':
				str[i] = "捌";
				continue;
			case '9':
				str[i] = "玖";
				continue;
			case '.':
				str[i] = "点";
				continue;
			}
		}
		return str;
	}

	/**
	 * 把人民币转换成大写的标准格式
	 * 
	 * @param str
	 * @return
	 */
	public static String money2BigFormat(String money) {
		String[] bigNumber = numToWords(money);
		int len = bigNumber.length;
		if (len > 11)
			return "数额过高";
		StringBuffer sb = new StringBuffer();
		if (len >= 7) {
			if (len == 11) {
				sb.append(bigNumber[0] + "仟");
				sb.append(bigNumber[1] + "佰" + bigNumber[2] + "拾"
						+ bigNumber[3] + "万");
			}
			if (len == 10) {
				sb.append(bigNumber[0] + "佰" + bigNumber[1] + "拾"
						+ bigNumber[2] + "万");
			}
			if (len == 9) {
				sb.append(bigNumber[0] + "拾" + bigNumber[1] + "万");
			}
			if (len == 8) {
				sb.append(bigNumber[0] + "万");
			}
			sb.append(bigNumber[len - 7] + "仟" + bigNumber[len - 6] + "佰"
					+ bigNumber[len - 5] + "拾");
		}
		if (len == 6) {
			sb.append(bigNumber[0] + "佰" + bigNumber[1] + "拾");
		}
		if (len == 5) {
			sb.append(bigNumber[0] + "拾");
		}
		sb.append(bigNumber[len - 4] + "元" + bigNumber[len - 2] + "角"
				+ bigNumber[len - 1] + "分整");
		return sb.toString();
	}

	/**
	 * 货币格式化
	 * 
	 * @param currency
	 * @return
	 */
	public static String formatCurrecy(String currency) {
		if ((null == currency) || "".equals(currency)
				|| "null".equals(currency)) {
			return "";
		}

		NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);

		try {
			return usFormat.format(Double.parseDouble(currency));
		} catch (Exception e) {
			return "";
		}
	}

	public static String formatCurrecy(String currency, String currencyCode) {
		try {
			if ((null == currency) || "".equals(currency)
					|| "null".equals(currency)) {
				return "";
			}

			if (currencyCode.equalsIgnoreCase("1")) {
				NumberFormat usFormat = NumberFormat
						.getCurrencyInstance(Locale.CHINA);
				return usFormat.format(Double.parseDouble(currency));
			} else {
				return currency + "点";
			}
		} catch (Exception e) {
			return "";
		}
	}

	// Useful split and replace methods

	/**
	 * Splits the provided text into a list, using whitespace as the separator.
	 * The separator is not included in the returned String array.
	 * 
	 * @param str
	 *            the string to parse
	 * @return an array of parsed Strings
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * @param text
	 *            String
	 * @param separator
	 *            String
	 * @return String[]
	 */
	public static String[] split(String text, String separator) {
		return split(text, separator, -1);
	}

	/**
	 * Splits the provided text into a list, based on a given separator. The
	 * separator is not included in the returned String array. The maximum
	 * number of splits to perfom can be controlled. A null separator will cause
	 * parsing to be on whitespace.
	 * <p>
	 * <p>
	 * This is useful for quickly splitting a string directly into an array of
	 * tokens, instead of an enumeration of tokens (as
	 * <code>StringTokenizer</code> does).
	 * 
	 * @param str
	 *            The string to parse.
	 * @param separator
	 *            Characters used as the delimiters. If <code>null</code>,
	 *            splits on whitespace.
	 * @param max
	 *            The maximum number of elements to include in the list. A zero
	 *            or negative value implies no limit.
	 * @return an array of parsed Strings
	 */
	public static String[] split(String str, String separator, int max) {
		StringTokenizer tok = null;
		if (separator == null) {
			// Null separator means we're using StringTokenizer's default
			// delimiter, which comprises all whitespace characters.
			tok = new StringTokenizer(str);
		} else {
			tok = new StringTokenizer(str, separator);
		}

		int listSize = tok.countTokens();
		if (max > 0 && listSize > max) {
			listSize = max;
		}

		String[] list = new String[listSize];
		int i = 0;
		int lastTokenBegin = 0;
		int lastTokenEnd = 0;
		while (tok.hasMoreTokens()) {
			if (max > 0 && i == listSize - 1) {
				// In the situation where we hit the max yet have
				// tokens left over in our input, the last list
				// element gets all remaining text.
				String endToken = tok.nextToken();
				lastTokenBegin = str.indexOf(endToken, lastTokenEnd);
				list[i] = str.substring(lastTokenBegin);
				break;
			}
			list[i] = tok.nextToken();
			lastTokenBegin = str.indexOf(list[i], lastTokenEnd);
			lastTokenEnd = lastTokenBegin + list[i].length();
			i++;
		}
		return list;
	}

	/**
	 * Replace all occurances of a string within another string.
	 * 
	 * @param text
	 *            text to search and replace in
	 * @param repl
	 *            String to search for
	 * @param with
	 *            String to replace with
	 * @return the text with any replacements processed
	 * @see #replace(String text, String repl, String with, int max)
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 * Replace a string with another string inside a larger string, for the
	 * first <code>max</code> values of the search string. A <code>null</code>
	 * reference is passed to this method is a no-op.
	 * 
	 * @param text
	 *            text to search and replace in
	 * @param repl
	 *            String to search for
	 * @param with
	 *            String to replace with
	 * @param max
	 *            maximum number of values to replace, or <code>-1</code> if
	 *            no maximum
	 * @return the text with any replacements processed
	 * @throws NullPointerException
	 *             if repl is null
	 */
	private static String replace(String text, String repl, String with, int max) {
		if (text == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = text.indexOf(repl, start);
		while (end != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
			end = text.indexOf(repl, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 用Map中的变量名-变量值替换源字符串中的变量名.
	 * 
	 * @param src
	 *            字符串
	 * @param value
	 *            变量名-变量值
	 * @return String <br>
	 *         <br>
	 *         Example: <br>
	 *         String src = "Hello ${username}, this is ${target} speaking.";
	 *         <br>
	 *         Map map = new HashMap(); <br>
	 *         map.put("username", "petter"); <br>
	 *         map.put("target", "tom"); <br>
	 *         src = StringUtil.replaceVariable(str, map); <br>
	 *         #The src equals: <br>
	 *         "Hello petter, this is tom speaking."
	 */
	public static String replaceVariable(final String src, final Map value) {
		int len = src.length();
		StringBuffer buf = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			char c = src.charAt(i);
			if (c == '$') {
				i++;
				StringBuffer key = new StringBuffer();
				char temp = src.charAt(i);
				while (temp != '}') {
					if (temp != '{') {
						key.append(temp);
					}
					i++;
					temp = src.charAt(i);
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

	/**
	 * 用Map中的变量名-变量值替换源字符串中的变量名,这个方法返回字符串是从char[] 构造的UTF字符串,不需要指定任何字符集都不可能乱码
	 * 以前那个方法是把UTF字符串又转换成GBK的byte,所以要重新指定字符集为GBK是为了和其它的GBK字符同时输出,所以要把UTF字符串转换成GBK的字符串以便同时显示.
	 */
	public static String utfToGBK(byte[] srcByte) throws Exception {
		StringBuffer str = new StringBuffer();
		int len = srcByte.length;
		int char1, char2, char3;
		int count = 0;
		while (count < len) {
			char1 = (int) srcByte[count] & 0xff;
			switch (char1 >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				count++;
				str.append((char) char1);
				break;
			case 12:
			case 13:
				count += 2;
				if (count > len) {
					throw new Exception();
				}
				char2 = (int) srcByte[count - 1];
				if ((char2 & 0xC0) != 0x80) {
					throw new Exception();
				}
				str.append((char) (((char1 & 0x1F) << 6) | (char2 & 0x3F)));
				break;
			case 14:

				/* 1110 xxxx 10xx xxxx 10xx xxxx */
				count += 3;
				if (count > len) {
					throw new Exception();
				}
				char2 = (int) srcByte[count - 2];
				char3 = (int) srcByte[count - 1];
				if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
					throw new Exception();
				}
				str.append((char) (((char1 & 0x0F) << 12)
						| ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0)));
				break;
			default:
				throw new Exception();
			}
		}
		return str.toString();
	}

	/**
	 * 可以直接以UTF-8显示字付串,如果要存储可以直接把转换后的UTF的byte写到文件或数据库.
	 * 
	 * @param s
	 *            :原始数据
	 * @param charset
	 *            :解码字符集格式
	 * @return
	 */
	public static byte[] getUTFBytes(String s, String charset) {
		try {
			int pos = 0;
			if (charset != null) {
				s = new String(s.getBytes(), charset);
			}
			char[] cc = s.toCharArray();
			byte[] buf = new byte[cc.length * 3];

			for (int i = 0; i < cc.length; i++) {
				char c = cc[i];
				if (c <= 0x007F && c != 0) {
					buf[pos++] = (byte) c;
				} else if (c > 0x07FF) {
					buf[pos + 2] = (byte) (0x80 | ((c >> 0) & 0x3F));
					buf[pos + 1] = (byte) (0x80 | ((c >> 6) & 0x3F));
					buf[pos + 0] = (byte) (0xE0 | ((c >> 12) & 0x0F));
					pos += 3;
				} else {
					buf[pos + 1] = (byte) (0x80 | ((c >> 0) & 0x3F));
					buf[pos + 0] = (byte) (0xC0 | ((c >> 6) & 0x1F));
					pos += 2;
				}
			}

			// buf是按照最大长度3算的，所以要截取剩余的空间
			byte[] tmp = new byte[pos];
			for (int i = 0; i < pos; i++)
				tmp[i] = buf[i];
			return tmp;

		} catch (Exception ex) {
			return null;
		}
	}

	public static int utfLength(String value) {
		// return str.replaceFirst("[^\\x00-\\xff]/g","aaa").length();
		int utflen = 0;
		char[] val = value.toCharArray();

		for (int i = 0; i < value.length(); i++) {
			int c = val[i];
			if ((c >= 0x0001) && (c <= 0x007F)) {
				utflen++;
			} else if (c > 0x07FF) {
				utflen += 3;
			} else {
				utflen += 2;
			}
		}
		return utflen;
	}

	public static String couponIdGenerator(String src) {
		String srcTemp = null;
		String dst = null;
		int iSrc = 0;

		if (src == null) {
			srcTemp = "        ";
		} else if (src.equals("")) {
			srcTemp = "        ";
		} else {
			srcTemp = src;
		}

		for (int i = 0; i < srcTemp.length(); i++) {
			iSrc = iSrc + (250 - 1 - i) * (int) (srcTemp.charAt(i));
		}

		dst = iSrc + "";
		return dst;
	}

	public static String getAliasName(String name) {
		StringBuilder sb = new StringBuilder("SonicJMSRASubcontext/Sonic_");
		StringTokenizer st = new StringTokenizer(name, ".");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			sb.append(first2Upper(token));
		}
		return sb.toString();
	}

	public static String first2Upper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Transform the specified byte into a Hex String form.
	 */
	public static final String bytesToHexStr(byte[] bcd) {
		StringBuffer s = new StringBuffer(bcd.length * 2);

		for (int i = 0; i < bcd.length; i++) {
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}

		return s.toString();
	}

	/**
	 * Transform the specified Hex String into a byte array.
	 */
	public static final byte[] hexStrToBytes(String s) {
		byte[] bytes;

		bytes = new byte[s.length() / 2];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, (2 * i) + 2),
					16);
		}

		return bytes;
	}
	
	/**
	 * 以下三个方法是建设银行外卡的加密函数需要的方法.
	 * @param b
	 * @return
	 */
	public static String hexString(byte[] b) {
        StringBuffer d = new StringBuffer(b.length * 2);
        for (int i=0; i<b.length; i++) {
            char hi = Character.forDigit ((b[i] >> 4) & 0x0F, 16);
            char lo = Character.forDigit (b[i] & 0x0F, 16);
            d.append(Character.toUpperCase(hi));
            d.append(Character.toUpperCase(lo));
        }
        return d.toString();
    }
    

    /**
     * @param   b       source byte array
     * @param   offset  starting offset
     * @param   len     number of bytes in destination (processes len*2)
     * @return  byte[len]
     */
    public static byte[] hex2byte (byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i=0; i<len*2; i++) {
            int shift = i%2 == 1 ? 0 : 4;
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;
        }
        return d;
    }
    /**
     * @param s source string (with Hex representation)
     * @return byte array
     */
    public static byte[] hex2byte (String s) {
        return hex2byte (s.getBytes(), 0, s.length() >> 1);
    }
	/**
	 * 
	 * @param Object[]
	 *            例如： 
	 *            传入:
	 *            		Object[]参数:   String[] result={"TYHR0001","TYHR0002"}
	 *                  delim参数:  ","
	 *            返回： "'TYHR0001','TYHR0002'"
	 *                  
	 * @param  Object[]
	 * @return String
	 */

	public static String arrayToDelimitedString(Object[] arr, String delim) {

		if (arr == null || arr.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append('\'');
			sb.append(arr[i]);
			sb.append('\'');
		}
		return sb.toString();
	}

	/**
	 *     e.g:  String[] result={"TYHR0001","TYHR0002"}; split=","; 
	 *  return:  str="TYHR0001,TYHR0002";
	 * @param Object[]
	 * @param split
	 * @return String
	 */
	public static String arrayToStr(Object[] arr, char split) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(split);
			}
			sb.append(arr[i]);

		}
		return sb.toString();
	}
	
	/**
	 * 
	 * getUTF8Length : 获取字符串的UTL-8编码长度. <br/>
	 *
	 * @author chris
	 * @param value
	 * @return
	 * @since JDK 1.6
	 */
	public static int getUTF8Length(String value){
		int length = 0;
		if(isEmpty(value)){
			return length;
		}
		
		try {
			length = value.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("编码出错",e);
		}	
		return length;
	}
	/**
	 * 根据源字符串截取字节(默认字节编码为GBK)
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 */
	public static String subStringByByte(String source,int start,int end){
		return subStringByByte(source,start,end,"GBK");
	}
	/**
	 * 根据源字符串截取字节
	 * @param source
	 * @param start
	 * @param end
	 * @param chartset
	 * @return
	 */
	public static String subStringByByte (String source,int start,int end,String chartset){
		byte[] bt;
		String result = null;
		try {
			if(isEmpty(source)){
				result = source;
			}else{
				bt = source.getBytes(chartset);
				result = byteToString(start, end, chartset, bt);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 字节流转字符串
	 * @param start 开始位置
	 * @param end	结束位置
	 * @param chartset 字符集
	 * @param bt	字节流
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String byteToString(int start, int end, String chartset,
			byte[] bt) throws UnsupportedEncodingException {
		String result = new String(Arrays.copyOfRange(bt,start, end),chartset);
		return result;
	}
	
    public static final char UNDERLINE='_';
    /**
     * 驼峰转下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * 带下划线转驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
               if (++i<len){
                   sb.append(Character.toUpperCase(param.charAt(i)));
               }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * 带下划线转驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }
    
    /**
     * Trim string
     * @param str
     * @return
     */
    public static String trim(String str){
    	return (isEmpty(str)) ? "" : str.trim();
    }
    
    /**
     * 遍历数组，将元素使用 <code>link</code> 参数连接成字符串
     * @param array
     * @param link
     * @return String
     */
    public static String link(String[] array, String link){
    	String str = "";
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				if (!trim(array[i]).equals("")) {
					if (i == 0) {
						str = trim(array[i]);
					} else {
						str = str + link + trim(array[i]);
					}
				}
			}
		} 
		return str;
    }
    
    /**
     * 遍历List，将元素使用 <code>link</code> 参数连接成字符串
     * @param array
     * @param link
     * @return
     */
    public static String link(List<String> list, String link){
    	if(list == null || list.isEmpty()){
    		return "";
    	}
    	String[] array = list.toArray(new String[list.size()]);
    	return link(array, link);
    }
    
    /**
     * 判断一个字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNum(String str){
    	if(isEmpty(str)){
    		return false;
    	}else{
    		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    	}
	}
    
	public static void main(String[] args){
		System.out.println(System.currentTimeMillis());
		System.out.println(fillZero("1", "0", true, 10));
		System.out.println(underlineToCamel("trans_type_status"));
	}

}
