/*******************************************************************************
 * Copyright (C) 2015   Serhiy Romaniuk 
 *
 * mail:rsk@isd.com.ua
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
package org.proto1.services;

import java.util.List;
import java.util.Map;
import org.proto1.domain.Railway;

public interface RailwayService {

	Railway getRailway(Long id);
	
	Railway getByRailwayCode(Integer railwayCode);
	
	List<Map<String, Object>> getRailwayList();
	
	Railway save(Railway railway);
	
	void delete(Long id);
	
	List<Railway> findAll(Railway criteria);

}
