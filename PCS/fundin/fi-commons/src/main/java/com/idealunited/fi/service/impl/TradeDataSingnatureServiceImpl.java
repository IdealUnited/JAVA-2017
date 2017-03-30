/**
 * 
 */
package com.idealunited.fi.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.fi.exception.ExceptionCodeEnum;
import com.idealunited.fi.exception.ParamValidateException;
import com.idealunited.fi.helper.SecuritySubstance;
import com.idealunited.fi.service.TradeDataSingnatureService;
import com.idealunited.util.CharsetTypeEnum;
import com.idealunited.util.SignTypeEnum;

public class TradeDataSingnatureServiceImpl implements
		TradeDataSingnatureService {

	private final Log log = LogFactory
			.getLog(TradeDataSingnatureServiceImpl.class);

	@Override
	public boolean verifyBySignType(String src, String signMsg,
			String signType, String key, String charsetType) throws Exception {
		int charset = Integer.valueOf(charsetType).intValue();
		CharsetTypeEnum charsetin = CharsetTypeEnum.getByCode(charset);
		if (SignTypeEnum.getByCode(signType) == null) {
			log.error("@FI-加签类型不正确");
			throw new ParamValidateException("FI-加签类型不正确",
					ExceptionCodeEnum.ILLEGAL_PARAMETER);
		}
		if (charsetin == null) {
			log.error("@FI-字符集类型不正确");
			throw new ParamValidateException("FI-字符集类型不正确",
					ExceptionCodeEnum.ILLEGAL_PARAMETER);
		}

		if (null != signType && signType.equals(SignTypeEnum.RSA.getCode())) {// RSA
			try {
				return SecuritySubstance.verifySignatureByRSA(src, signMsg,
						charsetin, key);
			} catch (Exception e) {
				log.error("RSA验签：验签过程异常" + e);
				throw new Exception(e);
			}
		} else if (null != signType
				&& signType.equals(SignTypeEnum.MD5.getCode())) {
			try {
				return SecuritySubstance.verifySignatureByMD5(src, signMsg,
						charsetin, key);
			} catch (Exception e) {
				log.error("MD5验签：验签过程异常" , e);
				throw new Exception(e);
			}
		}
		return false;
	}

	@Override
	public String genSignMsgBySignType(String src, String signType,
			String charsetType, String key) throws Exception {
		String signMsg = "";
		int charset = Integer.valueOf(charsetType).intValue();
		CharsetTypeEnum charsetin = CharsetTypeEnum.getByCode(charset);
		if (SignTypeEnum.getByCode(signType) == null) {
			log.error("@FI-加签类型不正确");
			throw new ParamValidateException("FI-加签类型不正确",
					ExceptionCodeEnum.ILLEGAL_PARAMETER);
		}
		if (charsetin == null) {
			log.error("@FI-字符集类型不正确");
			throw new ParamValidateException("FI-字符集类型不正确",
					ExceptionCodeEnum.ILLEGAL_PARAMETER);
		}
		if (null != signType && signType.equals(SignTypeEnum.RSA.getCode())) {// RSA
			return SecuritySubstance.genSignByRSA(src, charsetin);
		} else if (null != signType
				&& signType.equals(SignTypeEnum.MD5.getCode())) {

			return SecuritySubstance.genSignByMD5(src, charsetin, key);
		}
		return signMsg;
	}

	@Override
	public String decryptData(String encryptedData, String signType,
			String charsetType, String key) throws Exception {
		int charset = Integer.valueOf(charsetType).intValue();
		CharsetTypeEnum charsetin = CharsetTypeEnum.getByCode(charset);
		String srcData = null;
		if (SignTypeEnum.getByCode(signType) == null) {
			log.error("@FI-加签类型不正确");
			throw new ParamValidateException("FI-加签类型不正确",
					ExceptionCodeEnum.ILLEGAL_PARAMETER);
		}
		if (charsetin == null) {
			log.error("@FI-字符集类型不正确");
			throw new ParamValidateException("FI-字符集类型不正确",
					ExceptionCodeEnum.ILLEGAL_PARAMETER);
		}
		if (null != signType && signType.equals(SignTypeEnum.RSA.getCode())) {// RSA
			try {
				srcData = SecuritySubstance.decryptData(encryptedData, key);
			} catch (Exception e) {
				log.error("RSA验签：验签过程异常" + e);
				throw new Exception(e);
			}
		} else {
			log.error("验签类型为：" + signType);
			throw new Exception();
		}
		return srcData;
	}
}
