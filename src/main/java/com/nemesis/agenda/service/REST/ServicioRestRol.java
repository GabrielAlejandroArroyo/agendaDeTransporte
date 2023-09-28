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

import com.nemesis.agenda.dao.DaoRol;
import com.nemesis.agenda.entity.DtoRol;

@RestController
@RequestMapping("rol")
public class ServicioRestRol extends ServicioRestBase
{
	@Autowired
	private DaoRol dao;
	
	
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoRol>> getAll() throws Exception
	{
		List<DtoRol> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{idRol}", method = RequestMethod.GET)
	public ResponseEntity<DtoRol> getDto(@PathVariable("idRol") Integer id) throws Exception
	{
		Optional<DtoRol> optional = dao.findById(id);
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
	public ResponseEntity<DtoRol> create(@RequestBody DtoRol dto)
			throws Exception
	{
		DtoRol newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{idRol}")
	public ResponseEntity<Void> delete(@PathVariable("idRol") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoRol> update(@RequestBody DtoRol dto)
			throws Exception
	{
		DtoRol newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
