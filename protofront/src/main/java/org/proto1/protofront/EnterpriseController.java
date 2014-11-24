package org.proto1.protofront;

import org.proto1.dto.EnterpriseDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
	@RequestMapping(value = "submit", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody String submit(@RequestBody final EnterpriseDTO jsonData) {
		System.out.println("As is->" + jsonData);
/*
		org.json.JSONObject obj = new org.json.JSONObject(enterprise);  
		System.out.println("enterpriseId->" + obj.getLong("enterpriseId"));
		System.out.println("enterpriseId->" + obj.getString("enterpriseName"));
*/		
		return  "Redirect"; // new EnterpriseDTO(12L, "ISD");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String sayHello(@PathVariable String name) {
		String result = "Hello " + name + " to dineshonjava.com!!!";
		return result;
	}
}
