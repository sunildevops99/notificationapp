package com.learnkarts.notificationapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author BGH19927
 *
 */
public class CORSFilter extends OncePerRequestFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);


	public CORSFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse respose, FilterChain filterchain)
			throws ServletException, IOException {
		LOGGER.info("Adding CORS Headers to Response ........................");
		respose.setHeader("Access-Control-Allow-Origin", "*");
		respose.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		respose.setHeader("Access-Control-Allow-Headers", "*");
		respose.setHeader("Access-Control-Max-Age", "3600");
		filterchain.doFilter(request, respose);
	}

}
