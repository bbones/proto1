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
	@RequestMapping(value = "submit", method = RequestMethod.POST, produces = "application/json", consumes="application/json" )
	public @ResponseBody EnterpriseDTO submit(@RequestBody final EnterpriseDTO enterpriseDTO) {
		System.out.println("As is->" + enterpriseDTO);
		return enterpriseDTO;
	}

	@RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json")
	public EnterpriseDTO sayHello() {
		return  new EnterpriseDTO(12L, "ISD");
	}
}
