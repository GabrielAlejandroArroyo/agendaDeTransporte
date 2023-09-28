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
@Table (name="GW_Mensajes", indexes = @Index(name = "GW_I_Mensajes01", unique = false, columnList = "estado"))
public class DtoMensajeria 
{
	Integer idMensaje;
	String idDestinatario;
	String mensaje;
	Date fechaHoraCreacion;
	Date fechaHoraEnvio;
	String estado;
	Date timeStamp;
	
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
	@Column (name = "idMensaje")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_mensajeria")
	public Integer getIdMensaje()
	{
		return idMensaje;
	}
	public void setIdMensaje(Integer idMensaje)
	{
		this.idMensaje = idMensaje;
	}
	@Column (name = "idDestinatario", length = 50)
	public String getIdDestinatario()
	{
		return idDestinatario;
	}
	public void setIdDestinatario(String idDestinatario)
	{
		this.idDestinatario = idDestinatario;
	}
	@Column (name = "mensaje", length = 254)
	public String getMensaje()
	{
		return mensaje;
	}
	public void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}
	@Column (name = "fechaCreacion")
	public Date getFechaHoraCreacion()
	{
		return fechaHoraCreacion;
	}
	public void setFechaHoraCreacion(Date fechaHoraCreacion)
	{
		this.fechaHoraCreacion = fechaHoraCreacion;
	}
	@Column (name = "fechaEnvio")
	public Date getFechaHoraEnvio()
	{
		return fechaHoraEnvio;
	}
	public void setFechaHoraEnvio(Date fechaHoraEnvio)
	{
		this.fechaHoraEnvio = fechaHoraEnvio;
	}
	@Column (name = "estado")
	public String getEstado()
	{
		return estado;
	}
	public void setEstado(String estado)
	{
		this.estado = estado;
	}
	
}
