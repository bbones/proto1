/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
package org.proto1.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.proto1.tools.ContextUtility;
import org.springframework.context.ApplicationContext;

public class RepositoryGenerator {

	private ApplicationContext applicationContext;
	private String domainPackage;
	
	public RepositoryGenerator(ApplicationContext applicationContext, String domainPackage) {
		this.applicationContext = applicationContext;
		this.domainPackage = domainPackage;
	}

	public String getContextInfo() {
		String result = "\t\nContext Info\n";
		
		result = result + "\t\tClasses :"+ ContextUtility.getClassList(applicationContext, domainPackage).size();
		
		return result;
	}
	
	public void generateRepositoryCode(String templateFile, String defaultPackage) throws IOException {
		
		Properties p = new Properties();
	    p.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
	    p.setProperty("file.resource.loader.path", ".");		
	    Velocity.init(p);
		VelocityContext vc = new VelocityContext();
		for(Class<?> clazz : ContextUtility.getClassList(applicationContext, domainPackage)) {
			vc.put("canonical", clazz.getCanonicalName());
			vc.put("name", clazz.getSimpleName());
			vc.put("defPack", defaultPackage);
			Template template = Velocity.getTemplate(templateFile, "utf-8");
			BufferedWriter bw = new BufferedWriter(new PrintWriter("generated/" + 
						clazz.getSimpleName()+"Repository.java", "utf-8")); 
			template.merge(vc, bw); 		
			bw.flush();
			bw.close(); 
		}
	}

}
