package com.nemesis.agenda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesValue
{

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String pass;
	@Value("${spring.datasource.driver.class}")
	private String driver;
	@Value("${agendaTransporte.version}")
	private String vesion;
	@Value("${agendaTransporte.expirationJWT}")
	private String expirationJWT;
	@Value ("${agendaTransporte.expirationToken}")
	private String expirationToken;
	@Value ("${agendaTransporte.testToken}")
	private String testToken;
	@Value ("${agendaTransporte.testNroToken}")
	private String testNroToken;
	

	public String getUrl()
	{
		return url;
	}


	public String getUser()
	{
		return user;
	}


	public String getPass()
	{
		return pass;
	}


	public String getDriver()
	{
		return driver;
	}


	public String getVesion()
	{
		return vesion;
	}


	public Integer getExpirationJWT()
	{
		return new Integer(expirationJWT);
	}


	public String getExpirationToken()
	{
		return expirationToken;
	}	


	public String getTestToken()
	{
		return testToken;
	}


	public String getTestNroToken()
	{
		return testNroToken;
	}	
	
}