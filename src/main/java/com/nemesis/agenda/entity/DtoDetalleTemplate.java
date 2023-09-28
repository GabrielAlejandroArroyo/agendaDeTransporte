package com.nemesis.agenda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PV_T_DETALLE_TEMPLATE")
public class DtoDetalleTemplate
{

	Integer idDetalleTemplate;
	DtoCabeceraTemplate cabeceraTemplate;
	String dia;
	DtoAlmacenWF almacen;
	DtoDestinoWF destino;
	Integer cantidad;
	Integer tiempoCancelacion;
	String tipoCarga;
	Date timeStamp;
	String horaCarga;

	@Id
	@Column(name = "id_detalle_template")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_detalle_template")
	public Integer getIdDetalleTemplate()
	{
		return idDetalleTemplate;
	}

	public void setIdDetalleTemplate(Integer idDetalleTemplate)
	{
		this.idDetalleTemplate = idDetalleTemplate;
	}

	@Column(name = "dia_semana", length = 1)
	public String getDia()
	{
		return dia;
	}

	public void setDia(String dia)
	{
		this.dia = dia;
	}

	@ManyToOne
	@JoinColumn(name = "id_cabecera_template")
	@JsonBackReference
	public DtoCabeceraTemplate getCabeceraTemplate()
	{
		return cabeceraTemplate;
	}

	public void setCabeceraTemplate(DtoCabeceraTemplate cabeceraTemplate)
	{
		this.cabeceraTemplate = cabeceraTemplate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_origen")
	public DtoAlmacenWF getAlmacen()
	{
		return almacen;
	}

	public void setAlmacen(DtoAlmacenWF almacen)
	{
		this.almacen = almacen;
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

	@Column(name = "cantidad")
	public Integer getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(Integer cantidad)
	{
		this.cantidad = cantidad;
	}

	@Column(name = "tiempo_cancelacion")
	public Integer getTiempoCancelacion()
	{
		return tiempoCancelacion;
	}

	public void setTiempoCancelacion(Integer tiempoCancelacion)
	{
		this.tiempoCancelacion = tiempoCancelacion;
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

	@Version
	public Date getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
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

}
