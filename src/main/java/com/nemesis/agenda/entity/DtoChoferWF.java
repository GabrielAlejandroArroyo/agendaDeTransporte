package com.nemesis.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "V_CHOFERES_WF")
public class DtoChoferWF implements Comparable
{
	private String idChofer;
	private Integer codigo;
	private String nombre;
	private String apellido;
	private String dni;
	private String licencia;
	private String telefonoContacto;
	private String autorizado;
	private DtoTransportistaWF transportista;
	private DtoAlmacenWF almacen;
	private String idDescripcion;

	@Id
	@Column(name ="id_chofer")
	public String getIdChofer()
	{
		return idChofer;
	}

	public void setIdChofer(String idChofer)
	{
		this.idChofer = idChofer;
	}

	@Column(name ="codigo",insertable = false, updatable = false)
	public Integer getCodigo()
	{
		return codigo;
	}

	public void setCodigo(Integer codigo)
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
	@Column(name ="apellido",insertable = false, updatable = false)
	public String getApellido()
	{
		return apellido;
	}

	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	@Column(name ="dni",insertable = false, updatable = false)
	public String getDni()
	{
		return dni;
	}

	public void setDni(String dni)
	{
		this.dni = dni;
	}
	@Column(name ="licencia",insertable = false, updatable = false)
	public String getLicencia()
	{
		return licencia;
	}

	public void setLicencia(String licencia)
	{
		this.licencia = licencia;
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
	@Column(name ="autorizado",insertable = false, updatable = false)
	public String getAutorizado()
	{
		return autorizado;
	}

	public void setAutorizado(String autorizado)
	{
		this.autorizado = autorizado;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="transportista")
	public DtoTransportistaWF getTransportista()
	{
		return transportista;
	}

	public void setTransportista(DtoTransportistaWF transportista)
	{
		this.transportista = transportista;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="almacen")
	public DtoAlmacenWF getAlmacen()
	{
		return almacen;
	}

	public void setAlmacen(DtoAlmacenWF almacen)
	{
		this.almacen = almacen;
	}

	@Transient
	public String getIdDescripcion()
	{
		//return idDescripcion;
		return "["+this.getIdChofer()+"]"+this.getNombre()+"-"+this.getApellido();
	}

	public void setIdDescripcion(String idDescripcion)
	{
		this.idDescripcion = idDescripcion;
	}

	
	public int compareTo(Object o)
	{
		DtoChoferWF otroDto = (DtoChoferWF) o;
		// negativo si objeto1 < objeto2
		// cero si objeto1 = objeto2
		// positivo si objeto1 > objeto2
		/*
		
		*/
		return this.getCodigo().compareTo(otroDto.getCodigo());

	}

}
