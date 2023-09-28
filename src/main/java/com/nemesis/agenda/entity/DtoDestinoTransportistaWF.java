package com.nemesis.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "V_DESTINO_TRANSPORTISTA_WF")
public class DtoDestinoTransportistaWF implements Comparable
{
	private String idDestinoTransportista;
	private DtoAlmacenWF almacen;
	private DtoDestinoWF destino;
	private DtoTransportistaWF transportista;
	private Integer diasDuracionViaje;
	private Integer diasLlegadaAnticipadaTurno;

	@Id
	@Column (name = "ID_DESTINO_TRANSPORTISTA")
	public String getIdDestinoTransportista()
	{
		return idDestinoTransportista;
	}

	public void setIdDestinoTransportista(String idDestinoTransportista)
	{
		this.idDestinoTransportista = idDestinoTransportista;
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="destino")
	public DtoDestinoWF getDestino()
	{
		return destino;
	}

	public void setDestino(DtoDestinoWF destino)
	{
		this.destino = destino;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="transportista")
	public DtoTransportistaWF getTransportista()
	{
		return transportista;
	}
	
	@Column (name = "DIAS_DURACION_VIAJE")
	public Integer getDiasDuracionViaje()
	{
		return diasDuracionViaje;
	}

	@Column (name = "DIAS_LLEGADA_ANTICIPADA_TURNO")
	public Integer getDiasLlegadaAnticipadaTurno()
	{
		return diasLlegadaAnticipadaTurno;
	}

	public void setTransportista(DtoTransportistaWF transportista)
	{
		this.transportista = transportista;
	}

	public void setDiasDuracionViaje(Integer diasDuracionViaje)
	{
		this.diasDuracionViaje = diasDuracionViaje;
	}

	public void setDiasLlegadaAnticipadaTurno(Integer diasLlegadaAnticipadaTurno)
	{
		this.diasLlegadaAnticipadaTurno = diasLlegadaAnticipadaTurno;
	}
	
	

	public int compareTo(Object o)
	{
		DtoDestinoTransportistaWF otroDto = (DtoDestinoTransportistaWF) o;
		// negativo si objeto1 < objeto2
		// cero si objeto1 = objeto2
		// positivo si objeto1 > objeto2
		/*
		
		*/
		int i = (new Integer(
				(this.getAlmacen().getAlmacen() + this.getDestino().getDestino()).compareTo(otroDto.getAlmacen().getAlmacen() + this.getDestino().getDestino())));
		return i;
	}

}
