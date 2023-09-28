package com.nemesis.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;

@Entity
@Table (name="V_ALMACENES_WF")
//@Subselect("SELECT A.CALMACEN,DALMACEC||' - '||DALMACEL,DDIRECCI,DLOCALID,DPROVINC FROM SICAD.F013ALMA A, SICAD.LA013ALMA L WHERE A.CEMPRESA = '01' and A.CEMPRESA=L.CEMPRESA AND A.CALMACEN=L.CALMACEN AND L.XCONTPOR='S'")
public class DtoAlmacenWF implements Comparable
{
	private String descripcion;
	private String direccion;
	private String localidad;
	private String provincia;
	private String almacen;
	private String idDescripcion;

	@Column(name ="Descripcion",insertable = false, updatable = false)
	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	@Column(name="Direccion",insertable = false, updatable = false)
	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	@Column(name="Localidad",insertable = false, updatable = false)
	public String getLocalidad()
	{
		return localidad;
	}

	public void setLocalidad(String localidad)
	{
		this.localidad = localidad;
	}
	@Column(name="Provincia",insertable = false, updatable = false)
	public String getProvincia()
	{
		return provincia;
	}
	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}

	@Id
	@Column(name ="Almacen",insertable = false, updatable = false)
	public String getAlmacen()
	{
		return almacen;
	}
	public void setAlmacen(String almacen)
	{
		this.almacen = almacen;
	}

	@Transient
	public String getIdDescripcion()
	{
		return "["+this.getAlmacen()+"]"+this.getDescripcion();
		//return idDescripcion;
	}

	public void setIdDescripcion(String idDescripcion)
	{
		this.idDescripcion = idDescripcion;
	}

	public int compareTo(Object o)
	{
		DtoAlmacenWF otroDto = (DtoAlmacenWF)o;
		// negativo si objeto1 < objeto2
	    //  cero     si objeto1 = objeto2
	    //  positivo si objeto1 > objeto2
		/*
		
		*/
		int i =(new Integer(
				(this.getAlmacen()).compareTo			
				(otroDto.getAlmacen())));
		return i;
	}


}
