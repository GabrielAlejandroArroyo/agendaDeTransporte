package com.nemesis.agenda.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PV_T_CABECERA_TEMPLATE")
public class DtoCabeceraTemplate
{

	Integer idCabeceraTemplate;
	String descripcion;
	Date fechaCreacion;
	Date fechaModificacion;
	Date timeStamp;
	List<DtoDetalleTemplate> detalleTemplate;

	@Id
	@Column(name = "id_cabecera_template")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_cabecera_template")
	public Integer getIdCabeceraTemplate()
	{
		return idCabeceraTemplate;
	}

	public void setIdCabeceraTemplate(Integer idCabeceraTemplate)
	{
		this.idCabeceraTemplate = idCabeceraTemplate;
	}

	@Column(name = "descripcion", length = 30)
	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	@Column(name = "fecha_creacion")
	public Date getFechaCreacion()
	{
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "fecha_modicicacion")
	public Date getFechaModificacion()
	{
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion)
	{
		this.fechaModificacion = fechaModificacion;
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
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cabeceraTemplate")	
	@JsonManagedReference
	public List<DtoDetalleTemplate> getDetalleTemplate()
	{
		if (this.getDetalleTemplate()==null)
		{
			return new ArrayList<DtoDetalleTemplate>();
		}
		else
		{
			return detalleTemplate;
		}
	}

	public void setDetalleTemplate(List<DtoDetalleTemplate> detalleTemplate)
	{
		this.detalleTemplate = detalleTemplate;
	}

}
