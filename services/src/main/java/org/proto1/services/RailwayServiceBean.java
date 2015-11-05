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
/** Rsk 09.07.2015 */
package org.proto1.services;

import java.util.List;
import java.util.Map;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.proto1.domain.Railway;
import org.proto1.repository.RailwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RailwayServiceBean implements RailwayService {
	//protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	RailwayRepository railwayRepository;
	
	public Railway getRailway(Long id) {
		return railwayRepository.findOne(id);
	}
	
	public Railway getByRailwayCode(Integer railwayCode) {
		return railwayRepository.findByRailwayCode(railwayCode);
	}
	
	public List<Map<String, Object>> getRailwayList() {
		return railwayRepository.getList();
	}

	public Railway save(Railway railway) {
		//logger.debug("railway.getId()=" + railway.getId());
		//logger.debug("railway.getRailwayCode()=" + railway.getRailwayCode());
		//logger.debug("railway.getVersion()=" + railway.getVersion());
		return railwayRepository.save(railway);
	}
	
	public void delete(Long id) {
		railwayRepository.delete(id);
	}

}
