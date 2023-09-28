package com.nemesis.agenda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PV_T_DETALLE_PLANIFICACION", indexes = @Index(name = "PV_I_DETALLE_PLANIFICACION01", unique = false, columnList = "id_cabecera_planificacion"))
public class DtoDetallePlanificacionViaje
{
	Integer idDetallePlanificacion;
	DtoCabeceraPlanificacionViaje cabeceraPlanificacion;
	DtoEstadoPlanificacion estado;// estado String
	Date fechaHoraCreacion;// Date-Time
	DtoEquipoWF equipo;// id_equipo Integer
	DtoTractorWF tractor;// id_tractor Integer
	DtoChoferWF chofer;// id_chofer Integer
	DtoUsuario usuario;
	DtoTransportistaWF transportista;
	DtoAlmacenWF alamcen;
	String idMuelle;
	String notificacion;
	String observaciones;
	Date timestamp;

	@Id
	@Column(name = "id_detalle_planificacion")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_detalle_planificacion")
	public Integer getIdDetallePlanificacion()
	{
		return idDetallePlanificacion;
	}

	public void setIdDetallePlanificacion(Integer idDetallePlanificacion)
	{
		this.idDetallePlanificacion = idDetallePlanificacion;
	}

	@Column(name = "fecha_hora_creacion")
	public Date getFechaHoraCreacion()
	{
		return fechaHoraCreacion;
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

	public void setFechaHoraCreacion(Date fechaHoraCreacion)
	{
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	@Version
	@Column(name="TIME_STAMP")
	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	@Column(name = "id_muelle", length = 3)
	public String getIdMuelle()
	{
		return idMuelle;
	}

	public void setIdMuelle(String idMuelle)
	{
		this.idMuelle = idMuelle;
	}

	@Column(name = "notificacion", length = 1)
	public String getNotificacion()
	{
		return notificacion;
	}

	public void setNotificacion(String notificacion)
	{
		this.notificacion = notificacion;
	}

	@Column(name = "observaciones", length = 200)
	public String getObservaciones()
	{
		return observaciones;
	}

	public void setObservaciones(String observaciones)
	{
		this.observaciones = observaciones;
	}

	
	@ManyToOne (fetch =FetchType.LAZY )
	@JoinColumn(name = "id_cabecera_planificacion")
	@JsonBackReference
	public DtoCabeceraPlanificacionViaje getCabeceraPlanificacion()
	{
		
			return cabeceraPlanificacion;
		
	}

	public void setCabeceraPlanificacion(DtoCabeceraPlanificacionViaje cabeceraPlanificacion)
	{
		this.cabeceraPlanificacion = cabeceraPlanificacion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_equipo", nullable = true)
	public DtoEquipoWF getEquipo()
	{
		return equipo;
	}

	public void setEquipo(DtoEquipoWF equipo)
	{
		this.equipo = equipo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tractor", nullable = true)
	public DtoTractorWF getTractor()
	{
		return tractor;
	}

	public void setTractor(DtoTractorWF tractor)
	{
		this.tractor = tractor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_chofer", nullable = true)
	public DtoChoferWF getChofer()
	{
		return chofer;
	}

	public void setChofer(DtoChoferWF chofer)
	{
		this.chofer = chofer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", nullable = true)
	public DtoUsuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(DtoUsuario usuario)
	{
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transportista", nullable = true)
	public DtoTransportistaWF getTransportista()
	{
		return transportista;
	}

	public void setTransportista(DtoTransportistaWF transportista)
	{
		this.transportista = transportista;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_almacen", nullable = true)
	public DtoAlmacenWF getAlamcen()
	{
		return alamcen;
	}

	public void setAlamcen(DtoAlmacenWF alamcen)
	{
		this.alamcen = alamcen;
	}

}
