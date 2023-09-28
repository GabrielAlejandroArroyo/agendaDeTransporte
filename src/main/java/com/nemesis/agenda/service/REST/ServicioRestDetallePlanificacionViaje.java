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

import com.nemesis.agenda.dao.DaoDetallePlanificacionViaje;
import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViaje;
import com.nemesis.agenda.entity.DtoDetallePlanificacionViaje;

@RestController
@RequestMapping("detallePlanificacionViaje")
public class ServicioRestDetallePlanificacionViaje extends ServicioRestBase
{
	@Autowired
	private DaoDetallePlanificacionViaje dao;
		
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoDetallePlanificacionViaje>> getAll() throws Exception
	{
		List<DtoDetallePlanificacionViaje> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value ="porIdCabeceraPlanificacion/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoDetallePlanificacionViaje>> getIdCabeceraPlanificacionViaje(@PathVariable("id") Integer id ) throws Exception
	{
		
		//DtoCabeceraPlanificacionViaje dtoCabeceraPlanificacionViaje = new DtoCabeceraPlanificacionViaje();
		//dtoCabeceraPlanificacionViaje.setIdCabeceraPlanificacion(id);
		List<DtoDetallePlanificacionViaje> lista = dao.findByIdCabeceraPlanificacionViaje(id);
		return ResponseEntity.ok(lista);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<DtoDetallePlanificacionViaje> getById(@PathVariable("id") Integer id) throws Exception
	{
		Optional<DtoDetallePlanificacionViaje> optional = dao.findById(id);
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
	public ResponseEntity<DtoDetallePlanificacionViaje> create(@RequestBody DtoDetallePlanificacionViaje dto)
			throws Exception
	{
		DtoDetallePlanificacionViaje newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoDetallePlanificacionViaje> update(@RequestBody DtoDetallePlanificacionViaje dto)
			throws Exception
	{

		DtoDetallePlanificacionViaje newDto = dao.save(dto);
		return ResponseEntity.ok(newDto);

	}
}
