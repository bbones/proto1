/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
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

	<T extends DTO, S extends AbstractEntity>T  encode(S source, Class<T> clazz) 
			throws InstantiationException, IllegalAccessException;
}
