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
@Table (name="V_TRACTORES_WF")
public class DtoTractorWF implements Comparable
{
	private String idTractor;
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
	@Column(name ="id_tractor")
	public String getIdTractor()
	{
		return idTractor;
	}
	
	public void setIdTractor(String idTractor)
	{
		this.idTractor = idTractor;
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

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public String getPatente()
	{
		return patente;
	}

	public void setPatente(String patente)
	{
		this.patente = patente;
	}

	public Date getVencimientoSeguro()
	{
		return vencimientoSeguro;
	}

	public void setVencimientoSeguro(Date vencimientoSeguro)
	{
		this.vencimientoSeguro = vencimientoSeguro;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

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

		return "["+this.getIdTractor()+"]"+this.getPatente();
		//return idDescripcion;
	}

	public void setIdDescripcion(String idDescripcion)
	{
		this.idDescripcion = idDescripcion;
	}

	public int compareTo(Object o)
	{
		DtoTractorWF otroDto = (DtoTractorWF) o;
		// negativo si objeto1 < objeto2
		// cero si objeto1 = objeto2
		// positivo si objeto1 > objeto2
		/*
		
		*/
		return this.getCodigo().compareTo(otroDto.getCodigo());

	}

}
