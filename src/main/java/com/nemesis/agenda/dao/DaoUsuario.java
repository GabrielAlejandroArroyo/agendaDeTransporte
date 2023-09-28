package com.nemesis.agenda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nemesis.agenda.entity.DtoUsuario;

public interface DaoUsuario extends JpaRepository<DtoUsuario, Integer>
{
	@Query ("SELECT c FROM DtoUsuario c WHERE c.user = ?1 and habilitado = true ")
	public DtoUsuario  findByUser(String user);
}
