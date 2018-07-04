/**
 * 
 */
package com.learnkarts.notificationapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author BGH19927
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.learnkarts.notificationapp")
public class NotificationWebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}
}
