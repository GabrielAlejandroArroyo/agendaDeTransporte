package com.nemesis.agenda.service.REST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoAlmacenWF;
import com.nemesis.agenda.entity.DtoAlmacenWF;




@RestController
@RequestMapping("almacen")
public class ServicioRestAlmacenWF extends ServicioRestBase
{
	@Autowired
	private DaoAlmacenWF dao;

	

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoAlmacenWF>> getAll() throws Exception
	{
		List<DtoAlmacenWF> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<DtoAlmacenWF> getDto(@PathVariable("codigo") String codigo) throws Exception
	{
		Optional<DtoAlmacenWF> optional = dao.findById(codigo);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	
	
	/*
	//@GetMapping
	@RequestMapping (value ="{almacen}")
	public ResponseEntity<DtoAlmacenWF> getByCodigo(String almacen) throws Exception
	{
		try
		{
			DaoAlmacenWF dao = new DaoAlmacenWF();
			DtoAlmacenWF dto = dao.findByCodigo(almacen);			
			
			return ResponseEntity.ok(dto);
		}
		catch (Exception e)
		{
			throw e;
		}
	
	}
	@GetMapping
	public ResponseEntity <List<DtoAlmacenWF>> getAll() throws Exception
	{
		try
		{
			DaoAlmacenWF dao = new DaoAlmacenWF();
			DtoAlmacenWF[] array = dao.findAll();			
			List<DtoAlmacenWF> lista = new ArrayList<>();
			lista = Arrays.asList(array);
			return ResponseEntity.ok(lista);
		}
		catch (Exception e)
		{
			throw e;
		}
	
	}
	*/
}
	
