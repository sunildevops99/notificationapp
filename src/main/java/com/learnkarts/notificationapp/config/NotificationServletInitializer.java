/**
 * 
 */
package com.learnkarts.notificationapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.learnkarts.notificationapp.filter.CORSFilter;

import javax.servlet.Filter;

/**
 * @author BGH19927
 *
 */
public class NotificationServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { NotificationWebConfig.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };

	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{ new CORSFilter()};
	}


}
