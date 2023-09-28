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

import com.nemesis.agenda.dao.DaoDetalleTemplate;
import com.nemesis.agenda.entity.DtoDetalleTemplate;

@RestController
@RequestMapping("detalleTemplate")
public class ServicioRestDetalleTemplate extends ServicioRestBase
{
	@Autowired
	private DaoDetalleTemplate dao;
		
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoDetalleTemplate>> getAll() throws Exception
	{
		List<DtoDetalleTemplate> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<DtoDetalleTemplate> getById(@PathVariable("id") Integer id) throws Exception
	{
		Optional<DtoDetalleTemplate> optional = dao.findById(id);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	@RequestMapping(value = "/cabecera{idCabeceraTemplate}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoDetalleTemplate>> getByIdCabecera(Integer idCabeceraTemplate) throws Exception
	{
		List<DtoDetalleTemplate> lista = dao.findByIdCabeceraTemplate(idCabeceraTemplate);
		return ResponseEntity.ok(lista);
	}
	
	
	@PostMapping
	public ResponseEntity<DtoDetalleTemplate> create(@RequestBody DtoDetalleTemplate dto)
			throws Exception
	{
		DtoDetalleTemplate newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoDetalleTemplate> update(@RequestBody DtoDetalleTemplate dto)
			throws Exception
	{

		DtoDetalleTemplate newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
