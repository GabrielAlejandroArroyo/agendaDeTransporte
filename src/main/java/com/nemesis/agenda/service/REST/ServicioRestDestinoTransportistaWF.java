package com.nemesis.agenda.service.REST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoDestinoTransportistaWF;
import com.nemesis.agenda.entity.DtoDestinoTransportistaWF;




@RestController
@RequestMapping("destinoTransportista")
public class ServicioRestDestinoTransportistaWF extends ServicioRestBase
{
	@Autowired
	private DaoDestinoTransportistaWF dao;

	

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoDestinoTransportistaWF>> getAll() throws Exception
	{
		List<DtoDestinoTransportistaWF> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

}
	
