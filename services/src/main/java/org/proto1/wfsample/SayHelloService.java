package org.proto1.wfsample;

import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SayHelloService {
	 private final Logger log = LoggerFactory.getLogger(SayHelloService.class);
	public void printMessageA(ActivityExecution execution) {
		log.debug("Hello world: variant A");
	}
	
	public void printMessageB(ActivityExecution execution) {
		log.debug("Hello world: variant B");
	}
}
