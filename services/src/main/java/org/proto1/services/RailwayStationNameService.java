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

import org.proto1.domain.RailwayStationName;

public interface RailwayStationNameService {

	RailwayStationName getRailwayStationName(Long id);

}
