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
