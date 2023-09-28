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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "PV_T_USUARIO", indexes = @Index(name = "PV_I_USUARIO01", unique = true, columnList = "usuario"))
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@idUsuario", scope = DtoUsuario.class)
public class DtoUsuario
{
	private Integer idUsuario;
	private String user;
	private String password;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private Boolean habilitado;
	private DtoTransportistaWF transportista;
	private Date timestamp;
	private List<DtoUsuarioRol> usuarioRol;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pv_sq_usuario")
	public Integer getIdUsuario()
	{
		return idUsuario;
	}

	public void setIdUsuario(Integer idusuario)
	{
		this.idUsuario = idusuario;
	}

	@Column(name = "usuario", length = 20)
	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	@Column(name = "clave", length = 20)
	@JsonProperty(access = Access.WRITE_ONLY )
	public String getPassword()
	{
		return password;
		
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "nombre", length = 20)
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	@Column(name = "apellido", length = 20)
	public String getApellido()
	{
		return apellido;
	}

	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}

	@Column(name = "email", length = 50)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "telefono", length = 50)
	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	@Column(name = "habilitado")
	public Boolean getHabilitado()
	{
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado)
	{
		this.habilitado = habilitado;
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
	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}
	// @JsonManagedReference
	// @OneToMany (mappedBy = "usuario",cascade = CascadeType.)

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public List<DtoUsuarioRol> getUsuarioRol()
	{
		if (this.usuarioRol == null)
		{
			return new ArrayList<DtoUsuarioRol>();
		}
		else
		{
			return usuarioRol;
		}
	}

	public void setUsuarioRol(List<DtoUsuarioRol> usuarioRol)
	{
		this.usuarioRol = usuarioRol;
	}

}
