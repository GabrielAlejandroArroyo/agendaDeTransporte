package com.nemesis.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="V_TRANSPORTISTAS_WF")
public class DtoTransportistaWF implements Comparable
{
	private String codigo;
	private String nombre;
	private String direccion;
	private String provincia;
	private String cuit; 
	private String telefonoEmpresa;
	private String casillaCorreo;
	private String telefonoContacto;
	private String localidad;
	private String codigoNombre;
	
	@Id
	@Column(name ="codigo",insertable = false, updatable = false)
	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	@Column(name ="nombre",insertable = false, updatable = false)
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	@Column(name ="direccion",insertable = false, updatable = false)
	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	
	@Column(name ="provincia",insertable = false, updatable = false)
	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}
	@Column(name ="cuit",insertable = false, updatable = false)
	public String getCuit()
	{
		return cuit;
	}

	public void setCuit(String cuit)
	{
		this.cuit = cuit;
	}
	@Column(name ="telefono_empresa",insertable = false, updatable = false)
	public String getTelefonoEmpresa()
	{
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa)
	{
		this.telefonoEmpresa = telefonoEmpresa;
	}
	@Column(name ="casilla_correo",insertable = false, updatable = false)
	public String getCasillaCorreo()
	{
		return casillaCorreo;
	}

	public void setCasillaCorreo(String casillaCorreo)
	{
		this.casillaCorreo = casillaCorreo;
	}
	@Column(name ="telefono_contacto",insertable = false, updatable = false)
	public String getTelefonoContacto()
	{
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto)
	{
		this.telefonoContacto = telefonoContacto;
	}

	@Column(name ="localidad",insertable = false, updatable = false)
	public String getLocalidad()
	{
		return localidad;
	}

	public void setLocalidad(String localidad)
	{
		this.localidad = localidad;
	}

	@Transient
	public String getCodigoNombre()
	{
		return "["+this.getCodigo()+"]"+this.getNombre();
		//return codigoNombre;
	}

	public void setCodigoNombre(String codigoNombre)
	{
		this.codigoNombre = codigoNombre;
	}

	public int compareTo(Object o)
	{
		DtoTransportistaWF otroDto = (DtoTransportistaWF)o;
		// negativo si objeto1 < objeto2
	    //  cero     si objeto1 = objeto2
	    //  positivo si objeto1 > objeto2
		/*
		
		*/
		int i =(new Integer(
				(this.getCodigo()).compareTo			
				(otroDto.getCodigo())));
		return i;
	}
}
