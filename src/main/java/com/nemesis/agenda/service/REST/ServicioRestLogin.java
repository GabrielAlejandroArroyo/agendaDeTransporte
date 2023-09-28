package com.nemesis.agenda.service.REST;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nemesis.agenda.config.PropertiesValue;
import com.nemesis.agenda.dao.DaoMensajeria;
import com.nemesis.agenda.dao.DaoToken;
import com.nemesis.agenda.dao.DaoUsuario;
import com.nemesis.agenda.entity.DtoCredential;
import com.nemesis.agenda.entity.DtoMensajeria;
import com.nemesis.agenda.entity.DtoToken;
import com.nemesis.agenda.entity.DtoUsuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("login")
public class ServicioRestLogin extends ServicioRestBase
{
	@Autowired
	private DaoUsuario daoUsuario;

	@Autowired
	private DaoMensajeria daoMensajeria;

	@Autowired
	private DaoToken daoToken;

	@PersistenceContext
	EntityManager emManager;

	@PostMapping
	public DtoCredential login(@RequestBody DtoCredential dto) throws Exception
	{
		/**
		 * Autenticacion en dos pasos
		 * Siempre validamos usuario y pass
		 * Paso 1 Generamos el token 
		 * 		Se genera el token
		 * 		Se envia por SMS
		 * 		Se devuelve el paso 1 confirmado
		 * Paso 2 generamos el token JWT
		 * 		Validamos el token y su vigencia
		 * 		Generamos el token JWT
		 * 		Confirmamos el paso 2 confirmado
		 */
		dto.setPaso1(false);
		dto.setPaso2(false);
		try
		{
			// Recuperamos el usuario y validamos la clave
			DtoUsuario dtoUsuario = daoUsuario.findByUser(dto.getUser());

			if (!(dtoUsuario.getPassword().equals(dto.getPassword())))
			{
				dto.setMensaje("La clave no corresponde al usuario");
				return dto;
			}

			//Si no viene el token estamos en el paso uno y generamos el mismo
			if (dto.getToken() == null)
			{
				//Borramos los token del usuario

				daoToken.deleteByUser(dto.getUser());
				
				String token = generaRandom();
				//Insertamos el token para el envio de SMS
				DtoMensajeria dtoMensajeria = new DtoMensajeria();
				dtoMensajeria.setEstado("00");
				dtoMensajeria.setFechaHoraCreacion(new Date());
				dtoMensajeria.setIdDestinatario(dtoUsuario.getTelefono());
				dtoMensajeria.setMensaje("Código de Autorización: " + token);
				daoMensajeria.save(dtoMensajeria);

				//insertamos el token para el segundo paso y control
				DtoToken dtoToken = new DtoToken();
				dtoToken.setToken(token);
				dtoToken.setUser(dto.getUser());
					//Definimos la expiracion
				int expiracionToken = new Integer(pv.getExpirationToken()).intValue();
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date()); 
				calendar.add(Calendar.MILLISECOND, expiracionToken);
				dtoToken.setFechaHoraVigencia(calendar.getTime());
				daoToken.save(dtoToken);

				dto.setMensaje("Se envia TOKEN por SMS");
				dto.setPaso1(true);
				return dto;
			}
			else //El token viene por parametro
			{
				try
				{
					//Recuperamos el token 
					DtoToken dtoToken = daoToken.findByUser(dto.getUser());
					//Validamos que el mismo corresponda al usuario y este vigente
					if (dto.getToken().equals(dtoToken.getToken()))
					{
						if (dtoToken.getFechaHoraVigencia().compareTo(new Date()) > 0)
						{
							//Generamos el token JWT
							String token = getJWTToken(dto.getUser());
							dto.setTokenJWT(token);
							dto.setMensaje("valido");
							dto.setProfile(daoUsuario.findByUser(dto.getUser()));
							dto.getProfile().setPassword(null);
							dto.setPaso1(true);
							dto.setPaso2(true);
						}
						else
						{
							dto.setMensaje("TOKEN caducado - Generar nuevo token");
							dto.setPaso1(false);
							dto.setPaso2(false);
							daoToken.deleteByUser(dto.getUser());
						}
					}
					else
					{
						dto.setMensaje("TOKEN invalido - Generar nuevo token");
						dto.setPaso1(false);
						dto.setPaso2(false);
						daoToken.deleteByUser(dto.getUser());
					}
				}
				catch (Exception e)
				{
					dto.setMensaje("Error al recupeara el TOKEN: " + e.getMessage());
					dto.setPaso1(false);
					dto.setPaso2(false);
				}

			}
			return dto;
		}
		catch (Exception e)
		{
			dto.setMensaje("Usuario no existe");
			return dto;
		}
	}

	private String getJWTToken(String username)
	{
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + pv.getExpirationJWT()))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	private String generaRandom()
	{
		// Generamos un numero aleatorio de 6 digitos
		Random aleatorio = new Random(System.currentTimeMillis());
		int intAletorio = aleatorio.nextInt(999999);

		// Lo formateamos con 0 por delante
		String patron = "000000";
		DecimalFormat objDF = new DecimalFormat(patron);
		String cadena = objDF.format(intAletorio);
		// return cadena;
		/** TODO sacar
		 * 
		 */
		Boolean test = new Boolean(pv.getTestToken());
		if (test)
		{
			return pv.getTestNroToken();
		}
		else
		{
			return cadena;
		}
		
	}
}
