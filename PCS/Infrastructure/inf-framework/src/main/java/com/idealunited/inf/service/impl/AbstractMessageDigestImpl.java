package com.idealunited.inf.service.impl;

import java.security.MessageDigest;

import com.idealunited.inf.service.IMessageDigest;
import com.idealunited.util.security.ToolsUtil;

public abstract class AbstractMessageDigestImpl implements IMessageDigest {

	public abstract String getAlgname();

	public String genMessageDigest(byte[] src) throws Exception {
		MessageDigest md = MessageDigest.getInstance(getAlgname());
		return ToolsUtil.toHexString(md.digest(src));
	}

	public boolean validateMessageDigest(byte[] src, String dest)
			throws Exception {
		String mdes = genMessageDigest(src);
		return MessageDigest.isEqual(ToolsUtil.toByteArray(mdes),
				ToolsUtil.toByteArray(dest));
	}

}
