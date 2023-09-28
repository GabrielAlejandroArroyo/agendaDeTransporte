package com.nemesis.agenda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table (name="V_EQUIPOS_WF")
public class DtoEquipoWF implements Comparable
{
	private String idEquipo;
	private Integer codigo;
	private String descripcion;
	private String patente;
	private Date vencimientoSeguro;
	private String tipo;
	private String autorizado;
	private DtoTransportistaWF transportista;
	private DtoAlmacenWF almacen;
	private String idDescripcion;
	
	@Id
	@Column(name ="id_equipo")
	public String getIdEquipo()
	{
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo)
	{
		this.idEquipo = idEquipo;
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
	@Column(name ="descripcion")
	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	@Column(name ="patente")
	public String getPatente()
	{
		return patente;
	}

	public void setPatente(String patente)
	{
		this.patente = patente;
	}
	@Column(name ="vencimiento_seguro")
	public Date getVencimientoSeguro()
	{
		return vencimientoSeguro;
	}

	public void setVencimientoSeguro(Date vencimientoSeguro)
	{
		this.vencimientoSeguro = vencimientoSeguro;
	}
	@Column(name ="tipo")
	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	@Column(name ="autorizado")
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="almacen")
	public DtoAlmacenWF getAlmacen()
	{
		return almacen;
	}
	
	public void setTransportista(DtoTransportistaWF transportista)
	{
		this.transportista = transportista;
	}

	public void setAlmacen(DtoAlmacenWF almacen)
	{
		this.almacen = almacen;
	}
	@Transient
	public String getIdDescripcion()
	{
		return "["+this.getIdEquipo()+"]"+this.getPatente();
		//return idDescripcion;
	}

	public void setIdDescripcion(String idDescripcion)
	{
		this.idDescripcion = idDescripcion;
	}

	public int compareTo(Object o)
	{
		DtoEquipoWF otroDto = (DtoEquipoWF) o;
		// negativo si objeto1 < objeto2
		// cero si objeto1 = objeto2
		// positivo si objeto1 > objeto2
		/*
		
		*/
		return this.getCodigo().compareTo(otroDto.getCodigo());

	}

}
