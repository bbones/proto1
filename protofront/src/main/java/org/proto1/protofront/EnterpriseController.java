package org.proto1.protofront;

import org.proto1.domain.Enterprise;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public @ResponseBody Enterprise submit(@RequestBody final Enterprise enterprise) {
		return  new Enterprise();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String sayHello(@PathVariable String name) {
		String result = "Hello " + name + " to dineshonjava.com!!!";
		return result;
	}
}
