package com.nemesis.agenda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoDetalleTemplate;

public interface DaoDetalleTemplate extends JpaRepository<DtoDetalleTemplate, Integer>
{
	@Query ("SELECT c FROM DtoDetalleTemplate c WHERE c.cabeceraTemplate = ?1 ")
	public List<DtoDetalleTemplate> findByIdCabeceraTemplate (Integer idCabeceraPlanificacion);
}
