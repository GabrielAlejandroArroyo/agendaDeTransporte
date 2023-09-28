package com.nemesis.agenda.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nemesis.agenda.entity.DtoToken;

public interface DaoToken extends JpaRepository<DtoToken, Integer>
{

	@Query ("SELECT c FROM DtoToken c WHERE c.user = ?1")
	public DtoToken  findByUser(String user);
	
	@Modifying
	@Query("delete from DtoToken f where f.user=?1")
	@Transactional
	public int deleteByUser(String user);
	
}
