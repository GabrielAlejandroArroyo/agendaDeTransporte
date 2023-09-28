package com.nemesis.agenda.service.REST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoCabeceraPlanificacionViajeLazy;
import com.nemesis.agenda.dao.DaoDetallePlanificacionViaje;
import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViajeLazy;
import com.nemesis.agenda.entity.DtoEstadoPlanificacion;
import com.nemesis.agenda.entity.DtoTransportistaWF;

import io.swagger.annotations.ApiModelProperty;

@RestController
@RequestMapping("cabeceraPlanificacionViajeLazy")
public class ServicioRestCabeceraPlanificacionViajeLazy extends ServicioRestBase
{
	@Autowired
	private DaoCabeceraPlanificacionViajeLazy dao;

	@Autowired
	private DaoDetallePlanificacionViaje daoDetallePlanificacionViaje;

	// @PersistenceContext
	// private EntityManager entityManager;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getAll() throws Exception
	{
		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<DtoCabeceraPlanificacionViajeLazy> getById(@PathVariable("id") Integer id) throws Exception
	{
		Optional<DtoCabeceraPlanificacionViajeLazy> optional = dao.findById(id);
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
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getByEstado(@PathVariable("estado") String codigoEstado,
			@PathVariable("transportista") String codigoTransportista) throws Exception
	{
		DtoEstadoPlanificacion estado = new DtoEstadoPlanificacion();
		estado.setCodigo(codigoEstado);
		DtoTransportistaWF transportista = new DtoTransportistaWF();
		transportista.setCodigo(codigoTransportista);

		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findByEstado(estado, transportista);
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "pendiente/transportista/{transportisa}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getByPendientesAsignacionPorTransportistao(
			@PathVariable("transportisa") String transportista) throws Exception
	{
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fechaCarga = simpleDateFormat.format(new Date());
		DtoTransportistaWF dto = new DtoTransportistaWF();
		dto.setCodigo(transportista);
		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findByPendientesAsignacionPorTransportista(fechaCarga, dto);
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "asignados/transportista/{transportisa}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getByAsignacionPorTransportista(
			@PathVariable("transportisa") String transportista) throws Exception
	{
		DtoTransportistaWF dto = new DtoTransportistaWF();
		dto.setCodigo(transportista);
		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findByAsignadosPorTransportista(dto);
		return ResponseEntity.ok(lista);
	}



	@RequestMapping(value = "inEstado/{inEstados}/{transportista}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getInEstados(
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

		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findInEstados(listaEstados, transportista);
		return ResponseEntity.ok(lista);
	}

	@ApiModelProperty(required = true, dataType = "org.joda.time.LocalDate")
	@RequestMapping(value = "inEstadoTransportistaPorRango/{inEstados}/{transportista}/{fechaDesde}/{fechaHasta}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getInEstadosPorRangoFechas(
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
		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findInEstadosPorRango(listaEstados,
				dtoTransportista.getCodigo(), dtoTransportista, fechaDe, fechaHa);
		return ResponseEntity.ok(lista);
	}

	
	@RequestMapping(value = "vigentesALL", method = RequestMethod.GET)
	public ResponseEntity<List<DtoCabeceraPlanificacionViajeLazy>> getVigentesAll() throws Exception
	{

		String strDateFormat = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		String fechaDe = sdf.format(new Date());
		String fechaHa = "29991231";
		
		List<DtoCabeceraPlanificacionViajeLazy> lista = dao.findAllVigentes(fechaDe);
		return ResponseEntity.ok(lista);
	}

}
