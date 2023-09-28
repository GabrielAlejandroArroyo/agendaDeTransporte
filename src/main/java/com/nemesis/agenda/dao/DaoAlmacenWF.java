package com.nemesis.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nemesis.agenda.entity.DtoAlmacenWF;


public interface DaoAlmacenWF  extends JpaRepository<DtoAlmacenWF, String>
{
/*
	Connection connection;
	private Statement stmt = null;
	private ResultSet rset = null;
	private DtoAlmacenWF[] array = null;


	public DtoAlmacenWF findByCodigo(String almacen) throws Exception
	{
		try
		{
			connection = ConexionBBDD.conectarBBDD();
			stmt = connection.createStatement();

			String query = "";
			query = "SELECT *  FROM  V_ALMACENES_WF  WHERE ALAMCEN = '" + almacen + "'";
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
	
	public DtoAlmacenWF[] findAll() throws Exception
	{
		try
		{
			connection = ConexionBBDD.conectarBBDD();
			stmt = connection.createStatement();

			String query = "";
			query = "SELECT *  FROM  V_ALMACENES_WF";
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
	
	private DtoAlmacenWF [] rsetMappingDTO (ResultSet rset) throws Exception
	{
		try
		{
		ArrayList<DtoAlmacenWF> arrayList = new ArrayList<DtoAlmacenWF>();
		while (rset.next())
		{
			DtoAlmacenWF dto = new DtoAlmacenWF();
			dto.setAlmacen(rset.getString("ALMACEN"));
			dto.setDireccion(rset.getString("DIRECCION"));
			dto.setLocalidad("LOCALIDAD");
			dto.setProvincia("PROVINCIA");
			dto.setDescripcion(rset.getString("DESCRIPCION"));
			
			
			arrayList.add(dto);
			
		}
		
		Collections.sort(arrayList);
		DtoAlmacenWF[] array = new DtoAlmacenWF[arrayList.size()];
		array = (DtoAlmacenWF[]) arrayList.toArray(array);		
		return array;
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}
*/
}
