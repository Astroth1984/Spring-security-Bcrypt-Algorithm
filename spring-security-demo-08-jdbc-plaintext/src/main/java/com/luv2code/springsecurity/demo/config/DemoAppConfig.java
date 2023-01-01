package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	// Set up variable to hold properties
	@Autowired
	private Environment env;
	
	// Set up logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Define a bean for the View Resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// Define a Bean for Security Datasource
	@Bean
	public DataSource securityDataSourec() {
		
		// Create connection 
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// Set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// Log connection props
		// Test if we r reading from the properties file
		logger.info(">>> jdbc.url: " + env.getProperty("jdbc.driver"));
		logger.info(">>> jdbc.user: " + env.getProperty("jdbc.user"));
		
		// Set Database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		//  Set connection pool props
		securityDataSource.setInitialPoolSize(
				getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(
				getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(
				getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(
				getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}
	
	// Helper Method
	// Read Envirenment Properties and convert to int
	private int getIntProperty(String propName) {
		/**
		 * String propVal = env.getProperty(propName);
		 * int propValInt = Integer.parseInt(propVal);
		 * return propValInt;
		**/
		
		return Integer.parseInt(env.getProperty(propName));
	}

}
