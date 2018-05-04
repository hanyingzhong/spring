package com.soecode.lyf.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncoding implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//设置字符编码       
		request.setCharacterEncoding("UTF-8");       
		//将控制传到下一个过滤器，如果没有过滤器则传到被调用者     
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
