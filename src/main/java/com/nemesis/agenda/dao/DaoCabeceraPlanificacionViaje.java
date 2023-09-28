package com.nemesis.agenda.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViaje;
import com.nemesis.agenda.entity.DtoEquipoWF;
import com.nemesis.agenda.entity.DtoEstadoPlanificacion;
import com.nemesis.agenda.entity.DtoTransportistaWF;

public interface DaoCabeceraPlanificacionViaje extends JpaRepository<DtoCabeceraPlanificacionViaje, Integer>
{
	//public List<DtoCabeceraPlanificacionViaje> getDtoCabeceraPlanificacionViajeByEstado(HashMap<String, Object> conditions);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c WHERE c.estado = ?1 and c.transportista=?2")
	public List<DtoCabeceraPlanificacionViaje>  findByEstado(DtoEstadoPlanificacion estado,DtoTransportistaWF transportista);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c, DtoDestinoTransportistaWF d WHERE c.estado = '00' " 
	+ " and c.fechaCarga >?1 and  d.transportista =?2 "
	+ " and c.destino = d.destino and c.almacen = d.almacen")
	public List<DtoCabeceraPlanificacionViaje>  findByPendientesAsignacionPorTransportista(String fechaCarga,DtoTransportistaWF transportista);
	
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c"
			+ " WHERE c.transportista =?1 and c.equipo=?2 and c.estado in('10','20','21','25','30') order by c.fechaCarga")
	public List<DtoCabeceraPlanificacionViaje>  findByAsignadosPorTransportistaEquipo(DtoTransportistaWF transportista,DtoEquipoWF equipo);

	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c"
			+ " WHERE c.transportista =?1 and c.estado in('10','20','21','25') order by c.fechaCarga")
	public List<DtoCabeceraPlanificacionViaje>  findByAsignadosPorTransportista(DtoTransportistaWF transportista);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c WHERE c.estado in (select d from DtoEstadoPlanificacion d where d.codigo in (?1)) and c.transportista=?2")
	public List<DtoCabeceraPlanificacionViaje>  findInEstados(List <String> estados,DtoTransportistaWF transportista);
	
	//@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c WHERE c.estado in (select d from DtoEstadoPlanificacion d where d.codigo in (?1)) and c.transportista=?2 and c.fechaCarga between ?3 and ?4")
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c WHERE c.estado in (select d from DtoEstadoPlanificacion d where d.codigo in (?1)) and ((?2= '0') or c.transportista =?3) and c.fechaCarga between ?4 and ?5")
	public List<DtoCabeceraPlanificacionViaje>  findInEstadosPorRango(List <String> estados,String codigoTransportista,DtoTransportistaWF transportista, String fechaDesde, String fechaHasta );
}
