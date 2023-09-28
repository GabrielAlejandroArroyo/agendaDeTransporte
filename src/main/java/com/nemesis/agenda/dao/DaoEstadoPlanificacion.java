package com.nemesis.agenda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemesis.agenda.entity.DtoEstadoPlanificacion;


public interface DaoEstadoPlanificacion  extends JpaRepository<DtoEstadoPlanificacion, String>
{

}
