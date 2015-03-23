/**
 * BOMController.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 20, 2015
 */
package org.proto1.protofront.order;

import java.util.List;
import java.util.Map;

import org.proto1.services.order.BOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@RestController
@RequestMapping("/bom")
public class BOMController {
	@Autowired
	BOMService bomService;
	
	@RequestMapping(value = "listbylang/{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  bomListByLanguage(@PathVariable Long languageId) {
		return bomService.getBOMList(languageId);
	}
	
}