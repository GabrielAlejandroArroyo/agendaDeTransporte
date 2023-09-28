package com.nemesis.agenda.service.REST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoCabeceraPlanificacionViaje;
import com.nemesis.agenda.dao.DaoDetallePlanificacionViaje;
import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViaje;
import com.nemesis.agenda.entity.DtoDetallePlanificacionViaje;
import com.nemesis.agenda.entity.DtoEstadoPlanificacion;
import com.nemesis.agenda.entity.DtoTransportistaWF;

import io.swagger.annotations.ApiModelProperty;

@RestController
@RequestMapping("cabeceraPlanificacionViaje")
public class ServicioRestCabeceraPlanificacionViaje extends ServicioRestBase
{
	@Autowired
	private DaoCabeceraPlanificacionViaje dao;

	@Autowired
	private DaoDetallePlanificacionViaje daoDetallePlanificacionViaje;

	// @PersistenceContext
	// private EntityManager entityManager;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViaje>> getAll() throws Exception
	{
		List<DtoCabeceraPlanificacionViaje> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<DtoCabeceraPlanificacionViaje> getById(@PathVariable("id") Integer id) throws Exception
	{
		Optional<DtoCabeceraPlanificacionViaje> optional = dao.findById(id);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(value = "estado/{estado}/{transportista}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViaje>> getByEstado(@PathVariable("estado") String codigoEstado,
			@PathVariable("transportista") String codigoTransportista) throws Exception
	{
		DtoEstadoPlanificacion estado = new DtoEstadoPlanificacion();
		estado.setCodigo(codigoEstado);
		DtoTransportistaWF transportista = new DtoTransportistaWF();
		transportista.setCodigo(codigoTransportista);

		List<DtoCabeceraPlanificacionViaje> lista = dao.findByEstado(estado, transportista);
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "pendiente/transportista/{transportisa}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViaje>> getByPendientesAsignacionPorTransportistao(
			@PathVariable("transportisa") String transportista) throws Exception
	{
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fechaCarga = simpleDateFormat.format(new Date());
		DtoTransportistaWF dto = new DtoTransportistaWF();
		dto.setCodigo(transportista);
		List<DtoCabeceraPlanificacionViaje> lista = dao.findByPendientesAsignacionPorTransportista(fechaCarga, dto);
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "asignados/transportista/{transportisa}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViaje>> getByAsignacionPorTransportista(
			@PathVariable("transportisa") String transportista) throws Exception
	{
		DtoTransportistaWF dto = new DtoTransportistaWF();
		dto.setCodigo(transportista);
		List<DtoCabeceraPlanificacionViaje> lista = dao.findByAsignadosPorTransportista(dto);
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<DtoCabeceraPlanificacionViaje> create(@RequestBody DtoCabeceraPlanificacionViaje dto)
			throws Exception
	{
		DtoCabeceraPlanificacionViaje newDto = dao.save(dto);
		generarDetalle(newDto);
		return ResponseEntity.ok(newDto);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
	{
		dao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<DtoCabeceraPlanificacionViaje> update(@RequestBody DtoCabeceraPlanificacionViaje dto)
			throws Exception
	{

		DtoCabeceraPlanificacionViaje newDto = dao.save(dto);
		generarDetalle(newDto);
		return ResponseEntity.ok(newDto);

	}

	@RequestMapping(value = "inEstado/{inEstados}/{transportista}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViaje>> getInEstados(
			@PathVariable("inEstados") String codigoDeEstados,
			@PathVariable("transportista") String codigoTransportista) throws Exception
	{
		StringTokenizer stk = new StringTokenizer(codigoDeEstados, "-");

		List<String> listaEstados = new ArrayList<String>();
		while (stk.hasMoreTokens())
		{
			listaEstados.add(stk.nextToken());
		}

		DtoTransportistaWF transportista = new DtoTransportistaWF();
		transportista.setCodigo(codigoTransportista);

		List<DtoCabeceraPlanificacionViaje> lista = dao.findInEstados(listaEstados, transportista);
		return ResponseEntity.ok(lista);
	}

	@ApiModelProperty(required = true, dataType = "org.joda.time.LocalDate")
	@RequestMapping(value = "inEstadoPorRango/{inEstados}/{transportista}/{fechaDesde}/{fechaHasta}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViaje>> getInEstadosPorRangoFechas(
			@PathVariable("inEstados") String codigoDeEstados,
			@PathVariable(name = "transportista", required = false) String transportista,
			@PathVariable(name = "fechaDesde", required = false) String fechaDesde,
			@PathVariable(name = "fechaHasta", required = false) String fechaHasta) throws Exception
	{

		StringTokenizer stk = new StringTokenizer(codigoDeEstados, "-");
		List<String> listaEstados = new ArrayList<String>();
		while (stk.hasMoreTokens())
		{
			listaEstados.add(stk.nextToken());
		}
		DtoTransportistaWF dtoTransportista = new DtoTransportistaWF();
		String strDateFormat = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		String fechaDe = sdf.format(new Date());
		String fechaHa = "29991231";
		dtoTransportista.setCodigo(transportista);
		if (!(fechaDesde.equals("0")))
		{
			try
			{
				Date fechaDate = sdf.parse(fechaDesde);
				fechaDe = fechaDesde;
			}
			catch (Exception ex)
			{
				throw new Exception("Fecha invalida - formato yyyyMMd");
			}
		}

		if (!(fechaHasta.equals("0")))
		{
			try
			{
				Date fechaDate = sdf.parse(fechaHasta);
				fechaHa = fechaHasta;
			}
			catch (Exception ex)
			{
				throw new Exception("Fecha invalida - formato yyyyMMd");
			}
		}
		List<DtoCabeceraPlanificacionViaje> lista = dao.findInEstadosPorRango(listaEstados,
				dtoTransportista.getCodigo(), dtoTransportista, fechaDe, fechaHa);
		return ResponseEntity.ok(lista);
	}

	/*
	 * @RequestMapping(value = {"prueba/{estado}/","prueba/{}"}, method =
	 * RequestMethod.GET )//, produces = MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity <String> getPrueba(@PathVariable(required = false)
	 * Optional<String> estado) { if (estado.isPresent()) { return
	 * ResponseEntity.ok(estado.get()); }
	 * 
	 * return ResponseEntity.ok("sin valor"); }
	 */
	private void generarDetalle(DtoCabeceraPlanificacionViaje newDto)
	{

		DtoDetallePlanificacionViaje dtoDetallePlanificacionViaje = new DtoDetallePlanificacionViaje();
		dtoDetallePlanificacionViaje.setAlamcen(newDto.getAlmacen());

		dtoDetallePlanificacionViaje.setCabeceraPlanificacion(newDto);
		//DtoCabeceraPlanificacionViaje dtoCabeceraPlanificacionViaje = new DtoCabeceraPlanificacionViaje();
		//dtoCabeceraPlanificacionViaje.setIdCabeceraPlanificacion(newDto.getIdCabeceraPlanificacion());
		//dtoDetallePlanificacionViaje.setCabeceraPlanificacion(dtoCabeceraPlanificacionViaje);
		try
		{
			dtoDetallePlanificacionViaje.setChofer(newDto.getChofer());
		}
		catch (Exception e)
		{

		}
		try
		{
			dtoDetallePlanificacionViaje.setEquipo(newDto.getEquipo());
		}
		catch (Exception e)
		{

		}
		dtoDetallePlanificacionViaje.setEstado(newDto.getEstado());
		dtoDetallePlanificacionViaje.setFechaHoraCreacion(new Date());
		try
		{
			dtoDetallePlanificacionViaje.setIdMuelle(null);
		}
		catch (Exception e)
		{

		}

		dtoDetallePlanificacionViaje.setNotificacion("");
		dtoDetallePlanificacionViaje.setObservaciones("");
		try
		{
			dtoDetallePlanificacionViaje.setTractor(newDto.getTractor());
		}
		catch (Exception e)
		{

		}
		try
		{
			dtoDetallePlanificacionViaje.setTransportista(newDto.getTransportista());
		}
		catch (Exception e)
		{

		}
		try
		{
			dtoDetallePlanificacionViaje.setUsuario(newDto.getUsuarioModificador());
		}
		catch (Exception e)
		{

		}
		daoDetallePlanificacionViaje.save(dtoDetallePlanificacionViaje);

	}

}
