package com.nemesis.agenda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoAlmacenWF;
import com.nemesis.agenda.entity.DtoChoferWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;

public interface DaoChoferWF extends JpaRepository<DtoChoferWF, Integer>
{
	@Query ("SELECT c FROM  DtoChoferWF c WHERE c.codigo = ?1 AND c.almacen =?2")
	public DtoChoferWF  findByCodigoChoferCodigoAlamcen(Integer codigo,DtoAlmacenWF codigoAlmacen);
	
	@Query ("SELECT c FROM  DtoChoferWF c, DtoTransportistaWF d, DtoAlmacenWF e WHERE c.transportista = d.codigo and c.almacen=e.almacen")
	public List<DtoChoferWF>  findAllValidos();
	
	@Query ("SELECT c FROM  DtoChoferWF c, DtoTransportistaWF d, DtoAlmacenWF e WHERE c.transportista = d.codigo and c.almacen=e.almacen and c.transportista=?1 and c.almacen=?2")
	public List<DtoChoferWF>  findAllByCodigoTrabsportistaAlmacen(DtoTransportistaWF transportista, DtoAlmacenWF almacen);
	
	@Query ("SELECT c FROM  DtoChoferWF c WHERE c.codigo = ?1")
	public DtoChoferWF  findByCodigoChofer(Integer codigo);
}
