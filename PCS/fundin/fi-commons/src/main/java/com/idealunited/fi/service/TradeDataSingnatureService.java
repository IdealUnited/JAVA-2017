/**
 * 
 */
package com.idealunited.fi.service;

/**
 * @author huhb
 *
 */
public interface TradeDataSingnatureService {

	/**
	 * 
	 * @param src
	 * @param signMsg
	 * @param signType
	 * @param partnerID
	 * @param charsetType
	 * @return
	 * @throws Exception
	 */
	boolean verifyBySignType(String src, String signMsg, String signType,
			String partnerID, String charsetType) throws Exception;

	/**
	 * 
	 * @param src
	 * @param signType
	 * @param charsetType
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	String genSignMsgBySignType(String src, String signType,
			String charsetType, String publicKey) throws Exception;

	/**
	 * 解密字符串
	 * 
	 * @param encryptedData
	 * @param signType
	 * @param charsetType
	 * @param partnerID
	 * @return
	 * @throws Exception
	 */
	String decryptData(String encryptedData, String signType,
			String charsetType, String partnerID) throws Exception;

}
