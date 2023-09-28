package com.nemesis.agenda.service.REST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoMensajeria;
import com.nemesis.agenda.entity.DtoMensajeria;

@RestController
@RequestMapping("mensajeria")
public class ServicioRestMensajeria extends ServicioRestBase
{
	@Autowired
	private DaoMensajeria dao;
	
	
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoMensajeria>> getAll() throws Exception
	{
		List<DtoMensajeria> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{idMensajeria}", method = RequestMethod.GET)
	public ResponseEntity<DtoMensajeria> getDto(@PathVariable("idMensajeria") Integer id) throws Exception
	{
		Optional<DtoMensajeria> optional = dao.findById(id);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<DtoMensajeria> create(@RequestBody DtoMensajeria dto)
			throws Exception
	{
		DtoMensajeria newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{idMensajeria}")
	public ResponseEntity<Void> delete(@PathVariable("idMensajeria") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoMensajeria> update(@RequestBody DtoMensajeria dto)
			throws Exception
	{
		DtoMensajeria newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
