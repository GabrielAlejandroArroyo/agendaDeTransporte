package com.nemesis.agenda.service.REST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoTransportistaWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;

@RestController
@RequestMapping("transportista")
public class ServicioRestTransportistasWF extends ServicioRestBase
{
	@Autowired
	private DaoTransportistaWF dao;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoTransportistaWF>> getAll() throws Exception
	{
		List<DtoTransportistaWF> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<DtoTransportistaWF> getDto(@PathVariable("codigo") String codigo) throws Exception
	{
		Optional<DtoTransportistaWF> optional = dao.findById(codigo);
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
