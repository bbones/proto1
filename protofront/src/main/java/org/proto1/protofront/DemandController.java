package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demand")
public class DemandController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "getconsol/{languageId}", method = RequestMethod.GET, consumes="application/json")
	public @ResponseBody List<Map<String, Object>> getConsolidatedDemand(@RequestBody List<Long> paramList) {
		for(Long l : paramList) {
			logger.debug("Parameter->" + l);
		}
		return null;
	}
}
