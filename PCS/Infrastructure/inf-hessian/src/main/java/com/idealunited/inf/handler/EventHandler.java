package com.idealunited.inf.handler;

import org.springframework.stereotype.Service;

import com.idealunited.inf.excepiton.HessianInvokeException;

/**
 * 事件处理接口
 * 
 * @author chenjun @
 */

@Service
public interface EventHandler{

	/**
	 * 处理器总线
	 * 
	 * @param dataMsg
	 *            请求处理参数(JSON字符串)
	 * @return
	 */
	String handle(String dataMsg) throws HessianInvokeException;
}
