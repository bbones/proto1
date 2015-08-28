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
