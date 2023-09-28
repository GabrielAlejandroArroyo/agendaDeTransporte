package com.nemesis.agenda.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoAlmacenWF;
import com.nemesis.agenda.entity.DtoEquipoWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;


public interface DaoEquipoWF  extends JpaRepository<DtoEquipoWF, Integer>
{
	@Query ("SELECT c FROM DtoEquipoWF c where c.almacen = ?1 and c.transportista =?2 and c.autorizado='S'")
	public List<DtoEquipoWF>  findEquiposDisponibles(DtoAlmacenWF dtoAlmacen,DtoTransportistaWF dtoTransportista);

}
