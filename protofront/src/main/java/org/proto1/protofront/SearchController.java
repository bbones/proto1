package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	EnterpriseService enterpriseService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<EnterpriseName> getList(@RequestParam Long languageId, @RequestParam Map<String, Object> example, 
			@RequestParam int page, @RequestParam int rows) {
		Pageable p = new PageRequest(page-1, rows);
		return enterpriseService.getList(languageId, example, p);
	}
}
