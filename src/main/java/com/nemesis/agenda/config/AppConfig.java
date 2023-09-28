package com.nemesis.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages = "com.nemesis.agenda.config")
public class AppConfig
{


	/*@Bean
	public PropertiesValue propertiesValue2()
	{
		return new PropertiesValue();
	}*/

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
}