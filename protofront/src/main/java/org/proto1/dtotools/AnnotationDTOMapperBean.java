package org.proto1.dtotools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.AbstractEntity;
import org.proto1.dto.DTO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnotationDTOMapperBean implements DTOMapper {
	@Autowired
	BeanFactory beanFactory;
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public <T extends AbstractEntity, S extends DTO> T decode(S source,
			Class<T> clazz) throws InstantiationException, IllegalAccessException, SecurityException, 
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, BeansException, ClassNotFoundException {
		T entity = clazz.newInstance();
		Method[] methods = source.getClass().getDeclaredMethods();
		for (Method method : methods ) {
			for (DTODecode annotation :method.getAnnotationsByType(DTODecode.class) ) {
				// logger.debug(annotation.destination());
				Method m;
				Object sourceValue = method.invoke(source, new Object[]{});
				if (sourceValue != null) {
					if (!annotation.service().isEmpty()) {
						Object service = beanFactory.getBean(Class.forName(annotation.service())); // get Service
						// get Service method
						Method sm = service.getClass().getMethod(annotation.method(), new Class[]{method.getReturnType()}); 
						// get setter for annotated destination
						m = clazz.getMethod(annotation.destination(), new Class[]{ sm.getReturnType()});
						// invoke setter with result of service method with sourceValue
						Object methodResult = sm.invoke(service, new Object[]{sourceValue});
						m.invoke(entity, new Object[]{methodResult}); 
					} else {
						m = clazz.getMethod(annotation.destination(), new Class[]{method.getReturnType()});
						m.invoke(entity, new Object[]{sourceValue}); 
					}
				}
			}
		}
		return entity;
	}

	@Override
	public <T extends DTO, S extends AbstractEntity> T encode(S source,
			Class<T> clazz) throws InstantiationException, IllegalAccessException  {
		T entity = clazz.newInstance();
		Method[] methods = source.getClass().getDeclaredMethods();
		for (Method method : methods ) {
			for (DTOEncode annotation :method.getAnnotationsByType(DTOEncode.class) ) {
				
			}
		}
		return null;
	}

}
