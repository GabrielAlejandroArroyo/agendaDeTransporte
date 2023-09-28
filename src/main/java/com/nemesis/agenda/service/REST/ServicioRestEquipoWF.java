package com.nemesis.agenda.service.REST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.dao.DaoCabeceraPlanificacionViaje;
import com.nemesis.agenda.dao.DaoEquipoWF;
import com.nemesis.agenda.entity.DtoCabeceraPlanificacionViaje;
import com.nemesis.agenda.entity.DtoEquipoWF;
import com.nemesis.agenda.entity.DtoTransportistaWF;

import net.bytebuddy.asm.Advice.Exit;

@RestController
@RequestMapping("equipo")
public class ServicioRestEquipoWF extends ServicioRestBase
{
	@Autowired
	private DaoEquipoWF dao;

	@Autowired
	DaoCabeceraPlanificacionViaje daoCabeceraPlanificacionViaje;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<DtoEquipoWF>> getAll() throws Exception
	{
		List<DtoEquipoWF> lista = dao.findAll();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "allDisponibles/{idCabeceraPlanificacion}/{codigoTransportista}", method = RequestMethod.GET)
	public ResponseEntity<List<DtoEquipoWF>> getAllDisponibles(Integer idCabeceraPlanificacionViaje,
			String codigoTransportista) throws Exception
	{
		try
		{
			//Buscamos la cabecera
			Optional<DtoCabeceraPlanificacionViaje> optional = daoCabeceraPlanificacionViaje
					.findById(idCabeceraPlanificacionViaje);

			if (optional.isPresent())
			{
				DtoCabeceraPlanificacionViaje dtoCabeceraPlanificacionViaje = optional.get();
				DtoTransportistaWF transportista = new DtoTransportistaWF();
				transportista.setCodigo(codigoTransportista);

				//Buscmos todos los equipos que tiene el transportista autorizados para el almacen de salida
				List<DtoEquipoWF> listaEquipo = dao.findEquiposDisponibles(dtoCabeceraPlanificacionViaje.getAlmacen(),
						transportista);
				//Cargamos la duracion del viaje a reservar
				int duracionViajeParaPlanificar = new Integer(
						dtoCabeceraPlanificacionViaje.getDestino().getDuracionViaje()).intValue();
				//Cargamos en un treeMap los qeuipos disponibles del transportista
				TreeMap<Integer, DtoEquipoWF> treeMapEquipoDisponiblesMap = new TreeMap<>();
				for (Iterator iterator = listaEquipo.iterator(); iterator.hasNext();)
				{
					DtoEquipoWF dtoEquipoWF = (DtoEquipoWF) iterator.next();
					// por cada equipo buscamos que viajes tiene asignados
					List<DtoCabeceraPlanificacionViaje> listaViajesPlanificados = daoCabeceraPlanificacionViaje
							.findByAsignadosPorTransportistaEquipo(transportista, dtoEquipoWF);
					boolean equipoValido = true;
					// Evaluamos si la fechaCarga del primer viaje es mayor
					for (Iterator iterator2 = listaViajesPlanificados.iterator(); iterator2.hasNext();)
					{
						
						
						DtoCabeceraPlanificacionViaje dtoCabeceraPlanificacionViajeAsignados = (DtoCabeceraPlanificacionViaje) iterator2
								.next();
						//verificamos si la fecha de carga a planificar es mayor o menor que la que tiene comprometidas y le calculamos la duracion del viaje
						Integer fechaCargaParaPlanificar = new Integer(dtoCabeceraPlanificacionViaje.getFechaCarga());
						Integer fechaCargaPlanificada = new Integer(
								dtoCabeceraPlanificacionViajeAsignados.getFechaCarga());
						int diasDuracion = 0;
						boolean fechaMenorALaPlanificada = true;
						if (fechaCargaPlanificada < fechaCargaParaPlanificar)
						{
							diasDuracion = new Integer(
									dtoCabeceraPlanificacionViajeAsignados.getDestino().getDuracionViaje()).intValue();
							fechaMenorALaPlanificada = true;
						}
						else
						{
							diasDuracion = -1
									* (new Integer(dtoCabeceraPlanificacionViaje.getDestino().getDuracionViaje()));
							fechaMenorALaPlanificada = false;
						}

						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
						String dateInString = dtoCabeceraPlanificacionViajeAsignados.getFechaCarga();
						Date date = formatter.parse(dateInString);

						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date); // Configuramos la fecha que se recibe
						calendar.add(Calendar.DAY_OF_YEAR, diasDuracion);

						String pattern = "yyyyMMdd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String fechaReserva = simpleDateFormat.format(calendar.getTime());
						//Comparamos las fechas
						if (fechaMenorALaPlanificada)
						{
							if (new Integer(fechaReserva) >= fechaCargaParaPlanificar)
							{
								equipoValido =false;
								break;
							}

						}
						else
						{
							if (new Integer(fechaReserva) <= fechaCargaParaPlanificar)
							{
								equipoValido =false;
								break;
							}
						}
					}
					if (equipoValido) 
					{
						treeMapEquipoDisponiblesMap.put(dtoEquipoWF.getCodigo(), dtoEquipoWF);
					}

				}
				;
				listaEquipo = new ArrayList(treeMapEquipoDisponiblesMap.values());
				return ResponseEntity.ok(listaEquipo);
			}
			else
			{
				// return ResponseEntity.noContent().build();
				throw new Exception("No existe la cabecera");
			}

		}
		catch (Exception e)
		{
			throw e;
		}

	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<DtoEquipoWF> getDto(@PathVariable("codigo") Integer codigo) throws Exception
	{
		Optional<DtoEquipoWF> optional = dao.findById(codigo);
		if (optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());

		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
}
