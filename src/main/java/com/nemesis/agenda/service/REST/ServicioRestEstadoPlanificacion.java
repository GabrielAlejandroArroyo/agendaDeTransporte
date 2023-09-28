package com.nemesis.agenda.service.REST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoEstadoPlanificacion;
import com.nemesis.agenda.entity.DtoEstadoPlanificacion;




@RestController
@RequestMapping("estadoPlanificacion")
public class ServicioRestEstadoPlanificacion extends ServicioRestBase
{
	@Autowired
	private DaoEstadoPlanificacion dao;

	

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoEstadoPlanificacion>> getAll() throws Exception
	{
		List<DtoEstadoPlanificacion> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<DtoEstadoPlanificacion> getDto(@PathVariable("codigo") String codigo) throws Exception
	{
		Optional<DtoEstadoPlanificacion> optional = dao.findById(codigo);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	

}
	
