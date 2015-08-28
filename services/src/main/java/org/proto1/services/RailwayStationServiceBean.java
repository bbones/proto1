/** Rsk 09.07.2015 */
package org.proto1.services;

import org.proto1.domain.RailwayStation;
import org.proto1.repository.RailwayStationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RailwayStationServiceBean implements RailwayStationService {

	@Autowired
	RailwayStationRepository railwayStationRepository;
	
	public RailwayStation getRailwayStation(Long id) {
		return railwayStationRepository.findOne(id);
	}

}
