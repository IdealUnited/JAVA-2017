package com.idealunited.poss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idealunited.poss.session.RequestLocal;

public class InitFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		RequestLocal.setRequestLocal(request);
		RequestLocal.setResponseLocal(response);
		filterChain.doFilter(new XSSHttpServletRequestWrapper(
				(HttpServletRequest) servletRequest), servletResponse);
		// filterChain.doFilter(servletRequest, servletResponse);
		return;

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
