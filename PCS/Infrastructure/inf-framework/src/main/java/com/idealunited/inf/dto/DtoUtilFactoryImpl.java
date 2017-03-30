package com.idealunited.inf.dto;

import java.util.HashMap;
import java.util.Map;

import com.idealunited.inf.dto.DtoUtil;
import com.idealunited.inf.dto.DtoUtilFactory;

/**
 * 
 *  */
public final class DtoUtilFactoryImpl implements DtoUtilFactory {
	
	private static DtoUtilFactory instance;
	private static Map<Class, DtoUtil> model2util = new HashMap<Class, DtoUtil>();
	private static Map<Class, DtoUtil> dto2util = new HashMap<Class, DtoUtil>();

	public static DtoUtilFactory getInstance() {
		return instance;
	}

	/**
	 * Default constructor.
	 * 
	 */
	public DtoUtilFactoryImpl() {
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * .domain.dto.DtoUtil)
	 */
	public DtoUtilFactory addDtoUtil(DtoUtil util) {
		Class model = util.getModelClass();
		Class dto = util.getDtoClass();
		model2util.put(model, util);
		dto2util.put(dto, util);
		return this;
	}

	/*
	 * (non-Javadoc)
	 */
	public DtoUtil getDtoUtil(Class clazz) {
		DtoUtil result = model2util.get(clazz);
		if (null == result) {
			result = dto2util.get(clazz);
		}
		return result;
	}
}
