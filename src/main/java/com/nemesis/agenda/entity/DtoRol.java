package com.nemesis.agenda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


@Entity
@Table (name="PV_T_ROL")
public class DtoRol 
{
	private Integer idRol;
	private String descripcion;
	private Date timeStamp;
	private String idDescripcion;
	
	@Version
	public Date getTimeStamp()
	{
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}
	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_rol")
	public Integer getIdRol()
	{
		return idRol;
	}
	public void setIdRol(Integer idRol)
	{
		this.idRol = idRol;
	}
	@Column(name ="descripcion",length = 50)
	public String getDescripcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	@Transient
	public String getIdDescripcion()
	{
		//return idDescripcion;
		return "["+this.getIdRol().toString()+"]"+this.getDescripcion();
	}
	public void setIdDescripcion(String idDescripcion)
	{
		this.idDescripcion = idDescripcion;
	}
	

	
	
}
