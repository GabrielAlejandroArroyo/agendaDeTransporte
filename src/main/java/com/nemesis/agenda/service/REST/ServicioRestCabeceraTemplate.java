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

import com.nemesis.agenda.dao.DaoCabeceraTemplate;
import com.nemesis.agenda.entity.DtoCabeceraTemplate;

@RestController
@RequestMapping("cabeceraTemplate")
public class ServicioRestCabeceraTemplate extends ServicioRestBase
{
	@Autowired
	private DaoCabeceraTemplate dao;
	
	
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraTemplate>> getAll() throws Exception
	{
		List<DtoCabeceraTemplate> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<DtoCabeceraTemplate> getById(@PathVariable("id") Integer id) throws Exception
	{
		Optional<DtoCabeceraTemplate> optional = dao.findById(id);
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
	public ResponseEntity<DtoCabeceraTemplate> create(@RequestBody DtoCabeceraTemplate dto)
			throws Exception
	{
		DtoCabeceraTemplate newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoCabeceraTemplate> update(@RequestBody DtoCabeceraTemplate dto)
			throws Exception
	{
		DtoCabeceraTemplate newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
