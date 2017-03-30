/**
 * 
 */
package com.idealunited.inf.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.idealunited.inf.model.Model;

public abstract class AbstractDtoUtil implements DtoUtil {

	public AbstractDtoUtil() {
		super();
	}

	/*
	 * (non-Javadoc)
	 */
	public Dto convert2Dto(Model model) {
		if (null == model) {
			return null;
		}
		try {
			Dto dto = (Dto) getDtoClass().newInstance();
			BeanUtils.copyProperties(model, dto);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 */
	public List convert2Models(List dtos) {
		if (null == dtos || dtos.size() == 0) {
			return new ArrayList();
		}
		List<Model> result = new ArrayList<Model>();
		Iterator it = dtos.iterator();
		while (it.hasNext()) {
			Model model = convert2Model((Dto) it.next());
			result.add(model);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 */
	public List convert2Models(Set dtos) {
		List temp = new ArrayList();
		temp.addAll(dtos);
		return convert2Models(temp);
	}

	/*
	 * (non-Javadoc)
	 */
	public List convert2Dtos(List models) {
		if (null == models || models.size() == 0) {
			return new ArrayList();
		}
		List<Dto> result = new ArrayList<Dto>();
		Iterator it = models.iterator();
		while (it.hasNext()) {
			Dto dto = convert2Dto((Model) it.next());
			result.add(dto);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 */
	public List convert2Dtos(Set models) {
		List temp = new ArrayList();
		temp.addAll(models);
		return convert2Dtos(temp);
	}

	public Class getDtoClass() {
		String dtoClass = getDtoClassName();
		try {
			return Class.forName(dtoClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("invalid class name " + dtoClass, e);
		}
	}

	public Class getModelClass() {
		String modelClass = getModelClassName();
		try {
			return Class.forName(modelClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("invalid class name " + modelClass, e);
		}
	}

	protected String getModelClassName() {
		throw new UnsupportedOperationException();
	}

	protected String getDtoClassName() {
		throw new UnsupportedOperationException();
	}
}
