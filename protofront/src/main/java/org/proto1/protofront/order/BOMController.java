/**
 * BOMController.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 20, 2015
 */
package org.proto1.protofront.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.proto1.services.order.BOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@RestController
@RequestMapping("/boms")
public class BOMController {
	@Autowired
	BOMService bomService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  bomListByLanguage(@RequestParam Long languageId) {
		return bomService.getOrderList(languageId);
	}
	
	@RequestMapping(value = "{bomId}/lines", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  bomLineList(@PathVariable Long bomId, @RequestParam Long languageId) {
		return bomService.getOrderLines(bomId, languageId);
	}
	
	@RequestMapping(value = "lines/{olId}/lineparameters", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  prodOrderLineParameters(@PathVariable Long olId, @RequestParam Long languageId) {
		List<Map<String, Object>> result = bomService.getOrderLineParameters(olId, languageId);
		return (result == null) ? new ArrayList<Map<String, Object>>() : result;
	}
	
}
