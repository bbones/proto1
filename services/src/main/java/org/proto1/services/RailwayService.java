/** Rsk 09.07.2015 */
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

}
