/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  
@RequestMapping("/greeting")
public class HelloRestController {
	 @RequestMapping(value = "test/{name}", method = RequestMethod.GET)  
	 public String sayHello(@PathVariable String name) {  
	  String result="Hello "+name+" to dineshonjava.com!!!";    
	  return result;  
	 } 
}
