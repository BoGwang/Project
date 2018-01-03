package com.koitt.board.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("corsFilter")
public class CORSFilter implements Filter {
	
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) 
			throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("Filtering on ...");
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", 
				"GET, POST, PUT, DELETE, OPTIONS");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Content-Type, Authorization, " +
				"Origin, Accept, Access-Control-Request-Method, " +
				"Access-Control-Request-Headers");
		chain.doFilter(request, resp);
	}

	@Override
	public void destroy() {
	}

}
