package com.nemesis.agenda.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViaje;
import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViajeLazy;
import com.nemesis.agenda.entity.DtoEquipoWF;
import com.nemesis.agenda.entity.DtoEstadoPlanificacion;
import com.nemesis.agenda.entity.DtoTransportistaWF;

public interface DaoCabeceraPlanificacionViajeLazy extends JpaRepository<DtoCabeceraPlanificacionViajeLazy, Integer>
{
	//public List<DtoCabeceraPlanificacionViaje> getDtoCabeceraPlanificacionViajeByEstado(HashMap<String, Object> conditions);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViajeLazy c WHERE c.estado = ?1 and c.transportista=?2")
	public List<DtoCabeceraPlanificacionViajeLazy>  findByEstado(DtoEstadoPlanificacion estado,DtoTransportistaWF transportista);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViajeLazy c, DtoDestinoTransportistaWF d WHERE c.estado = '00' " 
	+ " and c.fechaCarga >?1 and  d.transportista =?2 "
	+ " and c.destino = d.destino and c.almacen = d.almacen")
	public List<DtoCabeceraPlanificacionViajeLazy>  findByPendientesAsignacionPorTransportista(String fechaCarga,DtoTransportistaWF transportista);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViajeLazy c"
			+ " WHERE c.transportista =?1 and c.estado in('10','20','21','25') order by c.fechaCarga")
	public List<DtoCabeceraPlanificacionViajeLazy>  findByAsignadosPorTransportista(DtoTransportistaWF transportista);
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViajeLazy c WHERE c.estado in (select d from DtoEstadoPlanificacion d where d.codigo in (?1)) and c.transportista=?2")
	public List<DtoCabeceraPlanificacionViajeLazy>  findInEstados(List <String> estados,DtoTransportistaWF transportista);
	
	//@Query ("SELECT c FROM DtoCabeceraPlanificacionViaje c WHERE c.estado in (select d from DtoEstadoPlanificacion d where d.codigo in (?1)) and c.transportista=?2 and c.fechaCarga between ?3 and ?4")
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViajeLazy c WHERE c.estado in (select d from DtoEstadoPlanificacion d where d.codigo in (?1)) and ((?2= '0') or c.transportista =?3) and c.fechaCarga between ?4 and ?5")
	public List<DtoCabeceraPlanificacionViajeLazy>  findInEstadosPorRango(List <String> estados,String codigoTransportista,DtoTransportistaWF transportista, String fechaDesde, String fechaHasta );
	
	
	@Query ("SELECT c FROM DtoCabeceraPlanificacionViajeLazy c WHERE c.fechaCarga >= ?1 ")
	public List<DtoCabeceraPlanificacionViajeLazy>  findAllVigentes(String fechaDesde);
}
