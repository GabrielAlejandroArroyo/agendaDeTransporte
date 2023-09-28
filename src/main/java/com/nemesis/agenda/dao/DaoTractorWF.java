package com.nemesis.agenda.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoAlmacenWF;
import com.nemesis.agenda.entity.DtoTractorWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;



public interface DaoTractorWF  extends JpaRepository<DtoTractorWF, Integer> 
{

	@Query ("SELECT c FROM  DtoTractorWF c, DtoTransportistaWF d, DtoAlmacenWF e WHERE c.transportista = d.codigo and c.almacen=e.almacen and c.transportista=?1 and c.almacen=?2")
	public List<DtoTractorWF>  findAllByCodigoTrabsportistaAlmacen(DtoTransportistaWF transportista, DtoAlmacenWF almacen);

	
}
