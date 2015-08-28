/** Rsk 09.07.2015 */
package org.proto1.services;

import org.proto1.domain.RailwayStationName;
import org.proto1.repository.RailwayStationNameRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RailwayStationNameServiceBean implements RailwayStationNameService {

	@Autowired
	RailwayStationNameRepository railwayStationNameRepository;
	
	public RailwayStationName getRailwayStationName(Long id) {
		return railwayStationNameRepository.findOne(id);
	}

}
