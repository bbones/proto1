package org.proto1.tools;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;

public class ContextUtility {

	public static Set<Class<?>> getClassList(ApplicationContext applicationContext, String domainPackage) {
		Set<Class<?>> classList = new HashSet<Class<?>>();
		for (String name : applicationContext.getBeanDefinitionNames()) {
			Class<?> clazz = applicationContext.getBean(name).getClass();
			if (clazz.getCanonicalName().startsWith(domainPackage))
				classList.add(clazz);
		}
		return classList;
		
	}
	

}
