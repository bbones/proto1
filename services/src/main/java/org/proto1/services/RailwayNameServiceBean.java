/** Rsk 09.07.2015 */
package org.proto1.services;

import org.proto1.domain.RailwayName;
import org.proto1.repository.RailwayNameRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RailwayNameServiceBean implements RailwayNameService {
	
	@Autowired
	RailwayNameRepository railwayNameRepository;
	
	public RailwayName getRailwayName(Long id) {
		return railwayNameRepository.findOne(id);
	}

}
