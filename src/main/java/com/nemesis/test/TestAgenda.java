package com.nemesis.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nemesis.agenda.config.AppConfig;
import com.nemesis.agenda.config.PropertiesValue;

public class TestAgenda
{

	public static void main(String[] args)
	{
		try
		{
			 ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		        PropertiesValue pv = ctx.getBean(PropertiesValue.class);
		        System.out.println(pv.getUrl());
		        ((AnnotationConfigApplicationContext)ctx).close();
			
			
			//DaoDestinoWF dao = new DaoDestinoWF();
			//DtoDestinoWF dto = dao.findDestinoWFById("099");
			//System.out.println(dto.getNombre());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
