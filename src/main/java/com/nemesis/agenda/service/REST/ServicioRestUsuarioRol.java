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

import com.nemesis.agenda.dao.DaoUsuarioRol;
import com.nemesis.agenda.entity.DtoUsuarioRol;

@RestController
@RequestMapping("usuarioRol")
public class ServicioRestUsuarioRol extends ServicioRestBase
{
	@Autowired
	private DaoUsuarioRol dao;
	
	
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoUsuarioRol>> getAll() throws Exception
	{
		List<DtoUsuarioRol> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{idUsuarioRol}", method = RequestMethod.GET)
	public ResponseEntity<DtoUsuarioRol> getDto(@PathVariable("idUsuarioRol") Integer id) throws Exception
	{
		Optional<DtoUsuarioRol> optional = dao.findById(id);
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
	public ResponseEntity<DtoUsuarioRol> create(@RequestBody DtoUsuarioRol dto)
			throws Exception
	{
		DtoUsuarioRol newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{idUsuarioRol}")
	public ResponseEntity<Void> delete(@PathVariable("idUsuarioRol") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoUsuarioRol> update(@RequestBody DtoUsuarioRol dto)
			throws Exception
	{
		DtoUsuarioRol newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
