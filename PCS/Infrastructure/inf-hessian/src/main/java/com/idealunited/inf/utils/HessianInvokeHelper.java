/**
 * 
 */
package com.idealunited.inf.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.inf.excepiton.HessianInvokeException;
import com.idealunited.inf.params.HessianInvokeParam;
import com.idealunited.util.JSonUtil;
import com.idealunited.util.StringUtil;
import com.idealunited.util.ZipUtil;

/**
 * @author new
 * 
 */
public class HessianInvokeHelper {

	private final static int compressLimit = 1024;

	private static final Logger logger = LoggerFactory
			.getLogger(HessianInvokeHelper.class);

	/**
	 * 构建异常响应信息
	 * 
	 * @param serCode
	 *            服务代码
	 * @param sysTraceNo系统跟踪号
	 * @param originNo
	 *            源系统编号
	 * @param targetNo
	 *            目标系统编号
	 * @param versionNo
	 *            接口版本号
	 * @param responseCode
	 *            响应代码
	 * @param responseDesc
	 *            响应描述
	 * @return
	 */
	public static String buildExceptionResponse(String serCode,
			String sysTraceNo, String originNo, String targetNo,
			String versionNo, String responseCode, String responseDesc) {

		Map<String, String> result = new HashMap<String, String>(2);
		result.put("responseCode", responseCode);
		result.put("responseDesc", responseDesc);
		HessianInvokeParam param = new HessianInvokeParam();
		param.setSerCode(serCode);
		param.setSysTraceNo(sysTraceNo);
		param.setOriginNo(originNo);
		param.setTargetNo(targetNo);
		param.setVersionNo(versionNo);
		param.setDataMsg(JSonUtil.toJSonString(result));
		return param.toString();
	}

	/**
	 * 处理响应参数，如果dataMsg被压缩，则解压
	 * 
	 * @param param
	 * @return
	 */
	public static void processResponse(HessianInvokeParam param) {

		if (param.getMsgCompress() == 1) {
			try {
				param.setDataMsg(ZipUtil.uncompress(param.getDataMsg()));
				param.setMsgCompress(0);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 处理请求参数，如果大于1024 byte，压缩后返回
	 * 
	 * @param srcDataMsg
	 *            源串
	 * @return
	 */
	public static HessianInvokeParam processRequest(String srcDataMsg) {
		HessianInvokeParam param = new HessianInvokeParam();
		param.setDataMsg(srcDataMsg);

		if (param.getDataLength() > compressLimit) {

			try {
				param.setDataMsg(ZipUtil.compress(srcDataMsg));
				param.setMsgCompress(1);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return param;
	}

	/**
	 * 构建响应信息
	 * 
	 * @param serCode
	 *            服务代码
	 * @param sysTraceNo系统跟踪号
	 * @param originNo
	 *            源系统编号
	 * @param targetNo
	 *            目标系统编号
	 * @param versionNo
	 *            接口版本号
	 * @param dataMsg
	 *            响应报文
	 * @return
	 */
	public static String buildResponse(String serCode, String sysTraceNo,
			String originNo, String targetNo, String versionNo, String dataMsg) {
		HessianInvokeParam param = new HessianInvokeParam();
		param.setSerCode(serCode);
		param.setSysTraceNo(sysTraceNo);
		param.setOriginNo(originNo);
		param.setTargetNo(targetNo);
		param.setVersionNo(versionNo);
		param.setDataMsg(dataMsg);

		if (param.getDataLength() > compressLimit) {

			try {
				param.setDataMsg(ZipUtil.compress(param.getDataMsg()));
				param.setMsgCompress(1);
			} catch (IOException e) {
				logger.error("报文解压失败", e);
				Map<String, String> result = new HashMap<String, String>(2);
				result.put("responseCode",
						ResponseCodeEnum.COMPRESS_FAILURE.getCode());
				result.put("responseDesc",
						ResponseCodeEnum.COMPRESS_FAILURE.getDesc());
				param.setDataMsg(JSonUtil.toJSonString(result));
			}
		}
		return param.toString();
	}

	/**
	 * 验证请求参数是否有效
	 * 
	 * @param serCode
	 * @param sysTraceNo
	 * @param originNo
	 * @param targetNo
	 * @param versionNo
	 * @param dataLength
	 * @param msgCompress
	 * @param dataMsg
	 * @throws HessianInvokeException
	 */
	public static void validateReqParam(String serCode, String sysTraceNo,
			String originNo, String targetNo, String versionNo, int dataLength,
			int msgCompress, String dataMsg) throws HessianInvokeException {
		if (StringUtil.isEmpty(serCode) || StringUtil.isEmpty(sysTraceNo)
				|| StringUtil.isEmpty(originNo) || StringUtil.isEmpty(targetNo)
				|| StringUtil.isEmpty(versionNo) || dataLength == 0
				|| (msgCompress != 0 && msgCompress != 1)
				|| StringUtil.isEmpty(dataMsg)) {
			throw new HessianInvokeException(
					ResponseCodeEnum.INVALID_PARAM.getCode(),
					ResponseCodeEnum.INVALID_PARAM.getDesc());
		}
	}

	/**
	 * 验证目标系统是否正确
	 * 
	 * @param targetNo
	 *            请求目标系统编号
	 * @param currentSystemCode
	 *            当前系统编号
	 * @throws HessianInvokeException
	 */
	public static void validateTargetNo(String targetNo,
			String currentSystemCode) throws HessianInvokeException {
		if (StringUtil.isEmpty(targetNo)
				|| StringUtil.isEmpty(currentSystemCode)) {
			throw new HessianInvokeException(
					ResponseCodeEnum.VALID_TARGETSYSCODE_FAILURE.getCode(),
					ResponseCodeEnum.VALID_TARGETSYSCODE_FAILURE.getDesc());
		} else if (!targetNo.equals(currentSystemCode)) {
			throw new HessianInvokeException(
					ResponseCodeEnum.VALID_TARGETSYSCODE_FAILURE.getCode(),
					ResponseCodeEnum.VALID_TARGETSYSCODE_FAILURE.getDesc());
		}
	}

	/**
	 * 验证请求消息正文内容长度
	 * 
	 * @param dataLength
	 *            请求消息正文内容长度
	 * @param dataMsg
	 *            请求消息正文
	 * @throws HessianInvokeException
	 */
	public static void validateDataMsgSize(int dataLength, String dataMsg)
			throws HessianInvokeException {
		try {
			if (StringUtil.isEmpty(dataMsg)
					|| dataMsg.getBytes("UTF-8").length != dataLength) {
				throw new HessianInvokeException(
						ResponseCodeEnum.VALID_DATAMSGSIZE_FAILURE.getCode(),
						ResponseCodeEnum.VALID_DATAMSGSIZE_FAILURE.getDesc());
			}
		} catch (UnsupportedEncodingException e) {
			throw new HessianInvokeException(
					ResponseCodeEnum.MSG_PARSING_FAILURE.getCode(), "报文编码处理失败",
					e);
		}
	}
	
	/**
	 * 被调系统针对请求参数做预处理
	 * 
	 * @param serCode
	 * @param sysTraceNo
	 * @param originNo
	 * @param targetNo
	 * @param versionNo
	 * @param dataLength
	 * @param msgCompress
	 * @param dataMsg
	 * @param sysCode
	 * @throws HessianInvokeException 
	 */
	public static void preHandleReqParam(String serCode, String sysTraceNo,
			String originNo, String targetNo, String versionNo, int dataLength,
			int msgCompress, String dataMsg, String sysCode) throws HessianInvokeException {
		// 验证参数有效
		validateReqParam(serCode, sysTraceNo, originNo, targetNo, versionNo,
				dataLength, msgCompress, dataMsg);
		// 验证目的系统
		validateTargetNo(targetNo, sysCode);
		// 验证请求消息正文内容长度
		validateDataMsgSize(dataLength, dataMsg);
	}

}
