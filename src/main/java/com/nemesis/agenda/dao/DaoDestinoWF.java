package com.nemesis.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import com.nemesis.agenda.entity.DtoDestinoWF;


public class DaoDestinoWF
{
	Connection connection;
	private Statement stmt = null;
	private ResultSet rset = null;
	private DtoDestinoWF[] array = null;

	public DtoDestinoWF findDestinoWFById(String destino) throws Exception
	{
		try
		{
			connection = ConexionBBDD.conectarBBDD();
			stmt = connection.createStatement();

			String query = "";
			query = "SELECT *  FROM  V_DESTINOS_WF  WHERE DESTINO = '" + destino + "'";
			rset = stmt.executeQuery(query);
			array =rsetMappingDTO(rset);
			
			return array [0];

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			array =null;
			connection.close();
		}
	}
	
	public DtoDestinoWF[] findDestinoAll() throws Exception
	{
		try
		{
			connection = ConexionBBDD.conectarBBDD();
			stmt = connection.createStatement();

			String query = "";
			query = "SELECT *  FROM  V_DESTINOS_WF";
			rset = stmt.executeQuery(query);
			array =rsetMappingDTO(rset);
			
			return array;

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			array =null;
			connection.close();
		}
	}
	
	private DtoDestinoWF [] rsetMappingDTO (ResultSet rset) throws Exception
	{
		try
		{
		ArrayList<DtoDestinoWF> arrayList = new ArrayList<DtoDestinoWF>();
		while (rset.next())
		{
			DtoDestinoWF dto = new DtoDestinoWF();
			dto.setDestino(rset.getString("DESTINO"));
			dto.setNombre(rset.getString("NOMBRE"));
			dto.setDireccion(rset.getString("DIRECCION"));
			dto.setCasillaCorreo("CASILLA_CORREO");
			dto.setDiasAnticipacionTurno("DIAS_ANTICIPACION_TURNO");
			dto.setDuracionViaje("DURACION_VIAJE_IV");
			dto.setLocalidad("LOCALIDAD");
			dto.setProvincia("PROVINCIA");
			dto.setTelefonoContacto("TELEFONO_CONTACTO");
			
			
			arrayList.add(dto);
			
		}
		
		Collections.sort(arrayList);
		DtoDestinoWF[] array = new DtoDestinoWF[arrayList.size()];
		array = (DtoDestinoWF[]) arrayList.toArray(array);		
		return array;
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}

}
