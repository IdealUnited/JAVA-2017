package com.idealunited.inf.comm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;

import com.idealunited.inf.dao.Page;
import com.idealunited.util.NumUtil;
import com.idealunited.util.ObjectUtil;

public class PageUtils {

	// public static Page getPage(HttpServletRequest request) {
	// Page page = new Page();
	// String size = request.getParameter("pageSize");
	// String pageNo = request.getParameter("pageNo");
	// if (NumUtil.isNum(size)) {
	// page.setPageSize(Integer.parseInt(size));
	// }
	//
	// if (NumUtil.isNum(pageNo)) {
	// page.setTargetPage(Integer.parseInt(pageNo));
	// }
	//
	// // page.setTotalCount(request.getParameter("totalCount"));
	// return page;
	// }

	public static <T> Page<T> getPage(HttpServletRequest request) {
		Page<T> page = new Page<T>();
		String size = request.getParameter("pageSize");
		// log.debug("pageSize=============" + pageSize);
		if (NumUtil.isNum(size)) {
			page.setPageSize(Integer.parseInt(size));
		}
		String pageNo = request.getParameter("pageNo");
		if (NumUtil.isNum(pageNo)) {
			page.setPageNo(Integer.parseInt(pageNo));
		}
		return page;
	}

	public static <T> Page<T> getJqueryPage(HttpServletRequest request) {
		Page<T> page = new Page<T>();
		String size = request.getParameter("rows");
		// log.debug("pageSize=============" + pageSize);
		if (NumUtil.isNum(size)) {
			page.setPageSize(Integer.parseInt(size));
		}
		String pageNo = request.getParameter("page");
		if (NumUtil.isNum(pageNo)) {
			page.setPageNo(Integer.parseInt(pageNo));
		}
		return page;
	}

	public static <T> Page<T> getPage(Page<T> page) {

		return page;
	}

	public static void setServicePage(Page<?> pageService, Page<?> page) {
		pageService.setPageSize(page.getPageSize());
		pageService.setTotalCount(page.getTotalCount());
		pageService.setPageNo(page.getPageNo());
		pageService.setTotalRecord(page.getTotalCount());
	}

	public static <T> List<?> changePageList(final List<T> resultList,
			Object targetClass, String[] ignoreProperties) {
		List<Object> t_list = new ArrayList<Object>();
		if (targetClass == null)
			return null;
		try {
			List<T> s_list = resultList;
			for (T source : s_list) {
				Object targetObject = ObjectUtil.instanceByClass(targetClass
						.getClass().getName());
				BeanUtils
						.copyProperties(source, targetObject, ignoreProperties);
				t_list.add(targetObject);
			}
			return t_list;
		} catch (Exception e) {
			//log.error("from model to dto error : " + e.getMessage(), e);
			return t_list;
		}

	}
}
