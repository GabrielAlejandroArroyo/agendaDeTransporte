package com.nemesis.agenda.service.REST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoTractorWF;
import com.nemesis.agenda.entity.DtoAlmacenWF;
import com.nemesis.agenda.entity.DtoTractorWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;



@RestController
@RequestMapping("tractor")
public class ServicioRestTractorWF extends ServicioRestBase
{
	@Autowired
	private DaoTractorWF dao;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoTractorWF>> getAll() throws Exception
	{
		List<DtoTractorWF> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<DtoTractorWF> getDto(@PathVariable("codigo") Integer codigo) throws Exception
	{
		Optional<DtoTractorWF> optional = dao.findById(codigo);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "allByTrasportistaAlmacen/{codigoTransportista}/{codigoAlmacen}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoTractorWF>> getAllByTranspòrtista(String codigoTransportista, String codigoAlamcen) throws Exception
	{
		DtoTransportistaWF dtoTransportistaWF = new DtoTransportistaWF();
		dtoTransportistaWF.setCodigo(codigoTransportista);
		DtoAlmacenWF dtoAlmacenWf = new DtoAlmacenWF();
		dtoAlmacenWf.setAlmacen(codigoAlamcen);
		List<DtoTractorWF> lista = dao.findAllByCodigoTrabsportistaAlmacen(dtoTransportistaWF,dtoAlmacenWf);
		return ResponseEntity.ok(lista);
	}
}
	
