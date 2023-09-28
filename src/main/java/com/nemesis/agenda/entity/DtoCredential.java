package com.nemesis.agenda.entity;

public class DtoCredential 
{
	
	private String user;
	private String password;
	private Boolean paso1;
	private Boolean paso2;
	private String mensaje;
	private String token;
	private String tokenJWT;
	private DtoUsuario profile;
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public String getTokenJWT()
	{
		return tokenJWT;
	}
	public void setTokenJWT(String tokenJWT)
	{
		this.tokenJWT = tokenJWT;
	}
	public Boolean getPaso1()
	{
		return paso1;
	}
	public void setPaso1(Boolean paso1)
	{
		this.paso1 = paso1;
	}
	public Boolean getPaso2()
	{
		return paso2;
	}
	public void setPaso2(Boolean paso2)
	{
		this.paso2 = paso2;
	}
	public String getMensaje()
	{
		return mensaje;
	}
	public void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}
	public DtoUsuario getProfile()
	{
		return profile;
	}
	public void setProfile(DtoUsuario profile)
	{
		this.profile = profile;
	}


	
	
}
