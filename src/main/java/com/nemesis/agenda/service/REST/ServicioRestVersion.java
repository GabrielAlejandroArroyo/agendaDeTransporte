package com.nemesis.agenda.service.REST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicioRestVersion extends ServicioRestBase
{
	
		@RequestMapping("version")
		public String versionAPI()
		//public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) 
		{	
			return pv.getVesion();
		}

}
