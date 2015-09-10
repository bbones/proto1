/** Rsk 09.07.2015 */
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.RailwayName;

public interface RailwayNameService {

	RailwayName getRailwayName(Long id);
	
	List<Map<String, Object>> getRailwayNameList(Long railwayId);
	
	RailwayName save(RailwayName railwayName);
	
	void delete(Long id);

}
