package com.nemesis.agenda.service.REST;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nemesis.agenda.config.AppConfig;
import com.nemesis.agenda.config.PropertiesValue;

public class ServicioRestBase
{
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	PropertiesValue pv = ctx.getBean(PropertiesValue.class);
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
}
