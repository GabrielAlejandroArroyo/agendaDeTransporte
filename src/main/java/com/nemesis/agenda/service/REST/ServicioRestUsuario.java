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

import com.nemesis.agenda.dao.DaoUsuario;
import com.nemesis.agenda.entity.DtoUsuario;

@RestController
@RequestMapping("usuario")
public class ServicioRestUsuario extends ServicioRestBase
{
	@Autowired
	private DaoUsuario dao;

	

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoUsuario>> getAll() throws Exception
	{
		List<DtoUsuario> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<DtoUsuario> getDto(@PathVariable("idUsuario") Integer id) throws Exception
	{
		Optional<DtoUsuario> optional = dao.findById(id);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(value = "/porUsuario/{user}", method = RequestMethod.GET)
	public ResponseEntity<DtoUsuario> getDtoByUser(@PathVariable("user") String user) throws Exception
	{
		DtoUsuario dto = dao.findByUser(user);
		if (dto!=null)
		{
			return ResponseEntity.ok(dto);

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<DtoUsuario> create(@RequestBody DtoUsuario dto) throws Exception
	{
		DtoUsuario newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{idUsuario}")
	public ResponseEntity<Void> delete(@PathVariable("idUsuario") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoUsuario> update(@RequestBody DtoUsuario dto) throws Exception
	{
		DtoUsuario newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
