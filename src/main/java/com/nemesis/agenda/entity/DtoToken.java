package com.nemesis.agenda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table (name="PV_T_Token" , indexes = @Index(name = "PV_I_Token01", unique = false, columnList = "usuario"))
public class DtoToken 
{
	private Integer id;
	private String token;
	private String user;
	private Date fechaHoraVigencia;
	private Date timeStamp;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_token")
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "token_agenda",length = 6)
	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Column(name = "usuario", length = 20)
	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}
	@Column(name = "fecha_vigencia")
	public Date getFechaHoraVigencia()
	{
		return fechaHoraVigencia;
	}

	public void setFechaHoraVigencia(Date fechaHoraVigencia)
	{
		this.fechaHoraVigencia = fechaHoraVigencia;
	}
	@Version
	@Column(name = "time_stamp")
	public Date getTimeStamp()
	{
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}
	
	
}
