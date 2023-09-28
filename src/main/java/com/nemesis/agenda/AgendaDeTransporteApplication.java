package com.nemesis.agenda;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class AgendaDeTransporteApplication extends SpringBootServletInitializer
{

	public static void main(String[] args)
	{
		SpringApplication.run(AgendaDeTransporteApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter
	{

		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			/*
			 * http.csrf().disable() .addFilterAfter(new JWTAuthorizationFilter(),
			 * UsernamePasswordAuthenticationFilter.class)
			 * .authorizeRequests().antMatchers(HttpMethod.POST,
			 * "/login").permitAll().anyRequest().authenticated();
			 */
	        http
            .authorizeRequests()
                .antMatchers("/**").permitAll() // Rutas públicas
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .csrf().disable()
            .anonymous(); // Habilita el acceso anónimo
			
		}
	}
	
	@PostConstruct
    public void init(){
      // Setting Spring Boot SetTimeZone
      TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));
    }
}
