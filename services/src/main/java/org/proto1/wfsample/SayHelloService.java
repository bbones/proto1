/*******************************************************************************
 * Copyright (C) 2015   Boris Efimenko
 *
 * mail:Boris.Efimenko@isd.com.ua
 * https://github.com/BorisEfimenko
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/

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
