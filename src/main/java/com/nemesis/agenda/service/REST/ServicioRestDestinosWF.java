package com.nemesis.agenda.service.REST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoDestinoWF;
import com.nemesis.agenda.entity.DtoDestinoWF;

@RestController
@RequestMapping("destino")
public class ServicioRestDestinosWF extends ServicioRestBase
{
	//@GetMapping
	@RequestMapping (value ="{idDestino}")
	public ResponseEntity<DtoDestinoWF> getById(String idDestino) throws Exception
	{
		try
		{
			DaoDestinoWF dao = new DaoDestinoWF();
			DtoDestinoWF dto = dao.findDestinoWFById(idDestino);			
			
			return ResponseEntity.ok(dto);
		}
		catch (Exception e)
		{
			throw e;
		}
	
	}
	@GetMapping
	public ResponseEntity <List<DtoDestinoWF>> getAll() throws Exception
	{
		try
		{
			DaoDestinoWF dao = new DaoDestinoWF();
			DtoDestinoWF [] array = dao.findDestinoAll();			
			List<DtoDestinoWF> lista = new ArrayList<>();
			lista = Arrays.asList(array);
			return ResponseEntity.ok(lista);
		}
		catch (Exception e)
		{
			throw e;
		}
	
	}
}
	
