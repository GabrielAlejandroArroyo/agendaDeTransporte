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
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table (name="PV_T_USUARIO_ROL")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="usuario", scope = DtoUsuario.class)
public class DtoUsuarioRol 
{
	Integer idUsuarioRol;
	DtoUsuario usuario;
	DtoRol rol;
	Date timestamp;

	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_usuario_rol")
	public Integer getIdUsuarioRol()
	{
		return idUsuarioRol;
	}
	public void setIdUsuarioRol(Integer idUsuarioRol)
	{
		this.idUsuarioRol = idUsuarioRol;
	}
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario",nullable = false)
	@JsonBackReference
	public DtoUsuario getUsuario()
	{
		return usuario;
	}
	public void setUsuario(DtoUsuario usuario)
	{
		this.usuario = usuario;
	}
	@ManyToOne(fetch=FetchType.EAGER,optional = false)
	@JoinColumn(name="id_rol")
	public DtoRol getRol()
	{
		return rol;
	}
	public void setRol(DtoRol rol)
	{
		this.rol = rol;
	}
	@Version
	public Date getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}
	
	

	
	
}
