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
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PV_T_CABECERA_PLANIFICACION")
public class DtoCabeceraPlanificacionViaje
{

	Integer idCabeceraPlanificacion;
	String numeroPlanificacion;

	DtoAlmacenWF almacen;
	DtoDestinoWF destino;
	String fechaCarga;
	String horaCarga;
	String tipoCarga;
	DtoEstadoPlanificacion estado;
	Boolean cancelable;
	Date fechaHoraCreacion;
	Date fechaHoraModificacion;
	DtoEquipoWF equipo;
	DtoTractorWF tractor;
	DtoChoferWF chofer;
	DtoUsuario usuarioCreador;
	DtoUsuario usuarioModificador;
	DtoUsuario usuarioCancelador;
	String observacion;
	DtoTransportistaWF transportista;
	List<DtoDetallePlanificacionViaje> colDetallePlanificacionViajes;
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

	@Column(name = "cancelable")
	public Boolean getCancelable()
	{
		return cancelable;
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

	public void setCancelable(Boolean cancelable)
	{
		this.cancelable = cancelable;
	}

	@Column(name = "fecha_hora_creacion")
	public Date getFechaHoraCreacion()
	{
		return fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion)
	{
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	@Column(name = "fecha_hora_modificacion")
	public Date getFechaHoraModificacion()
	{
		return fechaHoraModificacion;
	}

	public void setFechaHoraModificacion(Date fechaHoraModificacion)
	{
		this.fechaHoraModificacion = fechaHoraModificacion;
	}

	@Column(name = "observacoion", length = 100)
	public String getObservacion()
	{
		return observacion;
	}

	public void setObservacion(String observacion)
	{
		this.observacion = observacion;
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
	@JoinColumn(name = "id_equipo")
	public DtoEquipoWF getEquipo()
	{
		return equipo;
	}

	public void setEquipo(DtoEquipoWF equipo)
	{
		this.equipo = equipo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tractor")
	public DtoTractorWF getTractor()
	{
		return tractor;
	}

	public void setTractor(DtoTractorWF tractor)
	{
		this.tractor = tractor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_chofer")
	public DtoChoferWF getChofer()
	{
		return chofer;
	}

	public void setChofer(DtoChoferWF chofer)
	{
		this.chofer = chofer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_creacion")
	public DtoUsuario getUsuarioCreador()
	{
		return usuarioCreador;
	}

	public void setUsuarioCreador(DtoUsuario usuarioCreador)
	{
		this.usuarioCreador = usuarioCreador;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_modificacion")
	public DtoUsuario getUsuarioModificador()
	{
		return usuarioModificador;
	}

	public void setUsuarioModificador(DtoUsuario usuarioModificador)
	{
		this.usuarioModificador = usuarioModificador;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_cancelacion")
	public DtoUsuario getUsuarioCancelador()
	{
		return usuarioCancelador;
	}

	public void setUsuarioCancelador(DtoUsuario usuarioCancelador)
	{
		this.usuarioCancelador = usuarioCancelador;
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
	@Basic(optional = false ,fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cabeceraPlanificacion")
	@JsonManagedReference
	public List<DtoDetallePlanificacionViaje> getColDetallePlanificacionViajes()
	{
		if (this.colDetallePlanificacionViajes == null)
		{
			return new ArrayList<DtoDetallePlanificacionViaje>();
		}
		else
		{
			return colDetallePlanificacionViajes;
		}
		
	}

	public void setColDetallePlanificacionViajes(List<DtoDetallePlanificacionViaje> colDetallePlanificacionViajes)
	{
		this.colDetallePlanificacionViajes = colDetallePlanificacionViajes;
	}

}
