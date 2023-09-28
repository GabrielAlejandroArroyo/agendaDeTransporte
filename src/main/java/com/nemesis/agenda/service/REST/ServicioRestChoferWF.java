package com.nemesis.agenda.service.REST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoChoferWF;
import com.nemesis.agenda.entity.DtoAlmacenWF;
import com.nemesis.agenda.entity.DtoChoferWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;

@RestController
@RequestMapping("chofer")
public class ServicioRestChoferWF extends ServicioRestBase
{
	@Autowired
	private DaoChoferWF dao;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoChoferWF>> getAll() throws Exception
	{
		List<DtoChoferWF> lista = dao.findAllValidos();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "allByTrasportistaAlmacen/{codigoTransportista}/{codigoAlmacen}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoChoferWF>> getAllByTranspòrtista(String codigoTransportista, String codigoAlamcen) throws Exception
	{
		DtoTransportistaWF dtoTransportistaWF = new DtoTransportistaWF();
		dtoTransportistaWF.setCodigo(codigoTransportista);
		DtoAlmacenWF dtoAlmacenWf = new DtoAlmacenWF();
		dtoAlmacenWf.setAlmacen(codigoAlamcen);
		List<DtoChoferWF> lista = dao.findAllByCodigoTrabsportistaAlmacen(dtoTransportistaWF,dtoAlmacenWf);
		return ResponseEntity.ok(lista);
	}
	
	

	// @RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	@RequestMapping(value = "/{codigo}/{almacen}", method = RequestMethod.GET)
	public ResponseEntity<DtoChoferWF> getDtoByAlmacen(@PathVariable("codigo") Integer codigo,
			@PathVariable("almacen") DtoAlmacenWF almacen) throws Exception
	{
		// DtoChoferWF optional = dao.findByCodigoChoferCodigoAlamcen(codigo,almacen);
		DtoChoferWF dto = dao.findByCodigoChoferCodigoAlamcen(codigo, almacen);
		if (dto != null)
		{
			return ResponseEntity.ok(dto);

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<DtoChoferWF> getDto(@PathVariable("codigo") Integer codigo) throws Exception
	{
		// DtoChoferWF optional = dao.findByCodigoChoferCodigoAlamcen(codigo,almacen);
		//DtoChoferWF dto = dao.findByCodigoChofer(codigo);
		DtoAlmacenWF dtoAlamcen = new DtoAlmacenWF();
		dtoAlamcen.setAlmacen("001");
		DtoChoferWF dto = dao.findByCodigoChoferCodigoAlamcen(codigo, dtoAlamcen);
		if (dto != null)
		{
			return ResponseEntity.ok(dto);

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
}
