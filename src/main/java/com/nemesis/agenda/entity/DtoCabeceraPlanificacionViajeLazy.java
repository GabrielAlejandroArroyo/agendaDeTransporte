package com.nemesis.agenda.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PV_T_CABECERA_PLANIFICACION")
public class DtoCabeceraPlanificacionViajeLazy
{

	Integer idCabeceraPlanificacion;
	String numeroPlanificacion;

	String almacen;
	//String descripcionAlmacen;
	
	DtoDestinoWF destino;
	String fechaCarga;
	String horaCarga;
	String tipoCarga;
	DtoEstadoPlanificacion estado;

	DtoTransportistaWF transportista;
	Date timestamp;

	@Id
	@Column(name = "id_cabecera_planificacion")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_cabecera_planificacion")
	public Integer getIdCabeceraPlanificacion()
	{
		return idCabeceraPlanificacion;
	}

	public void setIdCabeceraPlanificacion(Integer idCabeceraPlanificacion)
	{
		this.idCabeceraPlanificacion = idCabeceraPlanificacion;
	}

	@Column(name = "numero_planificacion", length = 20)
	public String getNumeroPlanificacion()
	{
		return numeroPlanificacion;
	}

	public void setNumeroPlanificacion(String numeroPlanificacion)
	{
		this.numeroPlanificacion = numeroPlanificacion;
	}

	// @Column (name ="id_origen", length = 20)
	// public String getIdOrigen()
	// {
	// return idOrigen;
	// }
	// public void setIdOrigen(String idOrigen)
	// {
	// this.idOrigen = idOrigen;
	// }
	@Column(name = "id_origen")
	public String getAlmacen()
	{
		return almacen;
	}

	public void setAlmacen(String almacen)
	{
		this.almacen = almacen;
	}

	
	@Column(name = "fecha_carga", length = 8)
	public String getFechaCarga()
	{
		return fechaCarga;
	}

	public void setFechaCarga(String fechaCarga)
	{
		this.fechaCarga = fechaCarga;
	}

	@Column(name = "hora_carga", length = 6)
	public String getHoraCarga()
	{
		return horaCarga;
	}

	public void setHoraCarga(String horaCarga)
	{
		this.horaCarga = horaCarga;
	}

	@Column(name = "tipo_carga", length = 3)
	public String getTipoCarga()
	{
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga)
	{
		this.tipoCarga = tipoCarga;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado", nullable = false)
	public DtoEstadoPlanificacion getEstado()
	{
		return estado;
	}

	public void setEstado(DtoEstadoPlanificacion estado)
	{
		this.estado = estado;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_destino")
	public DtoDestinoWF getDestino()
	{
		return destino;
	}

	public void setDestino(DtoDestinoWF destino)
	{
		this.destino = destino;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transportista")
	public DtoTransportistaWF getTransportista()
	{
			return transportista;

	}

	public void setTransportista(DtoTransportistaWF transportista)
	{
		this.transportista = transportista;
	}

	@Version
	@Column(name = "TIME_STAMP")
	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}
	@Transient
	public String getDescripcionAlmacen()
	{
		if (getAlmacen().equals("001"))
		{
			return ("Secos");
		}
		else
		{
			return ("Perecederos");
		}
		//return descripcionAlmacen;
	}

	

}
