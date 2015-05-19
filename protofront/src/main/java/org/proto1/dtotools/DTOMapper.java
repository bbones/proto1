package org.proto1.dtotools;

import java.lang.reflect.InvocationTargetException;

import org.proto1.domain.AbstractEntity;
import org.proto1.dto.DTO;
import org.springframework.beans.BeansException;

public interface DTOMapper {
	
	<T extends AbstractEntity, S extends DTO>T  decode(S source, Class<T> clazz) 
			throws InstantiationException, IllegalAccessException, 
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, 
			BeansException, ClassNotFoundException;

}
