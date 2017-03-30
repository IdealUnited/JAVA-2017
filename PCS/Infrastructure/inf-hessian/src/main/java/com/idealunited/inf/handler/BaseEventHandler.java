package com.idealunited.inf.handler;

import com.idealunited.inf.excepiton.HessianInvokeException;
import com.idealunited.inf.params.HessianInvokeParam;
import com.idealunited.inf.params.HessianInvokeResponse;

/**
 * 
 * 服务处理类接口
 * 
 */
public interface BaseEventHandler<T extends HessianInvokeParam> {
	public HessianInvokeResponse handle(T req) throws HessianInvokeException;
}