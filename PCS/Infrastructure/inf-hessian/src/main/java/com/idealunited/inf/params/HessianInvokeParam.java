/**
 * 
 */
package com.idealunited.inf.params;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author new
 * 
 */
public class HessianInvokeParam implements Serializable {

	private static final long serialVersionUID = -7733769238086624282L;

	private static final String SPLITOR = "[分隔符]";

	/**
	 * 服务代码
	 */
	private String serCode;
	/**
	 * 系统跟踪号
	 */
	private String sysTraceNo;
	/**
	 * 源系统编号
	 */
	private String originNo;
	/**
	 * 目的系统编号
	 */
	private String targetNo;
	/**
	 * 接口版本号
	 */
	private String versionNo;
	/**
	 * 消息正文长度
	 */
	private int dataLength;
	/**
	 * 消息正文是否压缩 0 非压缩 1 压缩
	 */
	private int msgCompress;
	/**
	 * 消息正文内容
	 */
	private String dataMsg;

	public HessianInvokeParam() {

	}

	public HessianInvokeParam(String serCode, String sysTraceNo,
			String originNo, String targetNo, String versionNo, int dataLength,
			int msgCompress, String dataMsg) {
		this.serCode = serCode;
		this.sysTraceNo = sysTraceNo;
		this.originNo = originNo;
		this.targetNo = targetNo;
		this.versionNo = versionNo;
		this.dataLength = dataLength;
		this.msgCompress = msgCompress;
		this.dataMsg = dataMsg;
	}

	public String getSerCode() {
		return serCode;
	}

	public void setSerCode(String serCode) {
		this.serCode = serCode;
	}

	public String getSysTraceNo() {
		return sysTraceNo;
	}

	public void setSysTraceNo(String sysTraceNo) {
		this.sysTraceNo = sysTraceNo;
	}

	public String getOriginNo() {
		return originNo;
	}

	public void setOriginNo(String originNo) {
		this.originNo = originNo;
	}

	public String getTargetNo() {
		return targetNo;
	}

	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public int getDataLength() {

		return dataLength;

	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getMsgCompress() {
		return msgCompress;
	}

	public void setMsgCompress(int msgCompress) {
		this.msgCompress = msgCompress;
	}

	public String getDataMsg() {
		return dataMsg;
	}

	public void setDataMsg(String dataMsg) {
		this.dataMsg = dataMsg;
		try {
			this.dataLength = dataMsg.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {

		StringBuffer sbf = new StringBuffer();
		sbf.append(serCode).append(SPLITOR);
		sbf.append(sysTraceNo).append(SPLITOR);
		sbf.append(originNo).append(SPLITOR);
		sbf.append(targetNo).append(SPLITOR);
		sbf.append(versionNo).append(SPLITOR);
		sbf.append(dataLength).append(SPLITOR);
		sbf.append(msgCompress).append(SPLITOR);
		sbf.append(dataMsg);
		return sbf.toString();
	}

	public void parse(String responseStr) {
		if (responseStr == null || responseStr.length() == 0) {
			throw new RuntimeException("无效请求参数");
		}

		String[] params = responseStr.split("\\[分隔符\\]");
		if (params.length != 8) {
			throw new RuntimeException("无效请求参数");
		}

		this.serCode = params[0];
		this.sysTraceNo = params[1];
		this.originNo = params[2];
		this.targetNo = params[3];
		this.versionNo = params[4];
		this.dataLength = Integer.parseInt(params[5]);
		this.msgCompress = Integer.parseInt(params[6]);
		this.dataMsg = params[7];
	}

	public static void main(String[] args) {
		HessianInvokeParam param = new HessianInvokeParam();
		param.setSerCode("403001");
		param.setSysTraceNo("1");
		param.setOriginNo("404");
		param.setTargetNo("403");
		param.setVersionNo("1.0.0");
		param.setMsgCompress(0);
		param.setDataMsg("测试");

		String tmp = param.toString();

		System.out.println(tmp);

		param.parse(tmp);

		System.out.println(param.getDataMsg());

	}

}
