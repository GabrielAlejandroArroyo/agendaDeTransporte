package com.nemesis.agenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.nemesis.agenda.config.AppConfig;
import com.nemesis.agenda.config.PropertiesValue;

@Configuration
public class ConexionBBDD
{

	public static Connection conectarBBDD() throws Exception
	{

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PropertiesValue pv = ctx.getBean(PropertiesValue.class);
		Connection connection = null;

		try
		{

			Class.forName(pv.getDriver());//"oracle.jdbc.driver.OracleDriver";
			String cadenaConexion = pv.getUrl();// "jdbc:oracle:thin:@128.1.1.134:1521:WFL";
			String usuario = pv.getUser();// "PORTALTRWF";
			String password = pv.getPass();// "LA20PTW98";
			// ;
			return connection = DriverManager.getConnection(cadenaConexion, usuario, password);

		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}

	}
}
