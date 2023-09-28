package com.nemesis.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="V_DESTINOS_WF")
public class DtoDestinoWF implements Comparable
{
	private String destino;
	private String nombre;
	private String direccion;
	private String localidad;
	private String provincia;
	private String casillaCorreo;
	private String telefonoContacto;
	private String duracionViaje;
	private String diasAnticipacionTurno;
	private String idDescripcion;;
	

	@Id
	@Column(name ="destino",insertable = false, updatable = false)
	public String getDestino()
	{
		return destino;
	}

	public void setDestino(String destino)
	{
		this.destino = destino;
	}

	@Column(name ="nombre")
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	@Column(name ="direccion")
	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	
	
	@Column(name ="localidad")
	public String getLocalidad()
	{
		return localidad;
	}

	public void setLocalidad(String localidad)
	{
		this.localidad = localidad;
	}
	@Column(name ="provincia")
	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}
	@Column(name ="casilla_correo")
	public String getCasillaCorreo()
	{
		return casillaCorreo;
	}

	public void setCasillaCorreo(String casillaCorreo)
	{
		this.casillaCorreo = casillaCorreo;
	}
	@Column(name ="telefono_contacto")
	public String getTelefonoContacto()
	{
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto)
	{
		this.telefonoContacto = telefonoContacto;
	}
	@Column(name ="duracion_viaje_iv")
	public String getDuracionViaje()
	{
		return duracionViaje;
	}

	public void setDuracionViaje(String duracionViaje)
	{
		this.duracionViaje = duracionViaje;
	}
	@Column(name ="dias_anticipacion_turno")
	public String getDiasAnticipacionTurno()
	{
		return diasAnticipacionTurno;
	}

	public void setDiasAnticipacionTurno(String diasAnticipacionTurno)
	{
		this.diasAnticipacionTurno = diasAnticipacionTurno;
	}

	@Transient
	public String getIdDescripcion()
	{
		//return idDescipcion;
		return "["+this.getDestino()+"]"+this.getNombre();
	}

	public void setIdDescripcion(String idDescripcion)
	{
		this.idDescripcion = idDescripcion;
	}

	public int compareTo(Object o)
	{
		DtoDestinoWF otroDto = (DtoDestinoWF)o;
		// negativo si objeto1 < objeto2
	    //  cero     si objeto1 = objeto2
	    //  positivo si objeto1 > objeto2
		/*
		
		*/
		int i =(new Integer(
				(this.getDestino()).compareTo			
				(otroDto.getDestino())));
		return i;
	}
}
