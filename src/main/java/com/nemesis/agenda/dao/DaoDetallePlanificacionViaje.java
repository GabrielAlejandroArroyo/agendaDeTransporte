package com.nemesis.agenda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViaje;
import com.nemesis.agenda.entity.DtoDetallePlanificacionViaje;

public interface DaoDetallePlanificacionViaje extends JpaRepository<DtoDetallePlanificacionViaje, Integer>
{
	
	@Query ("SELECT c FROM DtoDetallePlanificacionViaje c WHERE ID_CABECERA_PLANIFICACION=?1")
	public List<DtoDetallePlanificacionViaje>  findByIdCabeceraPlanificacionViaje(Integer  id);
	
}
