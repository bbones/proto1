/**
 * Request.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.protofront.order;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proto1.domain.order.Request;
import org.proto1.dto.order.RequestDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.order.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/requests")
public class RequestController {
	@Autowired
	RequestService requestService;
	
	@Autowired
	LanguageService languageService;

	@Autowired
	BaseOrderMapper mapper;

	@RequestMapping(value = "/lang:{languageId}", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@PathVariable Long languageId) {
		return requestService.getOrderList(languageId);
	}

	@RequestMapping(value = "/lang:{languageId}", method = RequestMethod.POST )
	public @ResponseBody Map<String, Object> create(@PathVariable Long languageId,
			RequestDTO requestDTO) 
			throws InstantiationException, IllegalAccessException, ParseException {
		Request po = mapper.map(requestDTO, Request.class);
		po = requestService.save(po);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("orderId", po.getId());
		result.put("orderNo", po.getDocumentNo());
		result.put("issueDate", po.getIssueDate());
		return result;
	}
	
	@RequestMapping(value = "/{requestId}", method = RequestMethod.DELETE)
	public void deleteRequest(@PathVariable Long requestId) {
		requestService.delete(requestId);
	}


	@RequestMapping(value = "/{rId}/lines/lang:{languageId}", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getOrderLines(@PathVariable Long rId, 
			@PathVariable Long languageId) {
		return requestService.getOrderLines(rId, languageId);
	}

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		    public void setAsText(String value) {
		        try {
		            setValue(new SimpleDateFormat("dd.MM.yyyy").parse(value));
		        } catch(ParseException e) {
		            setValue(null);
		        }
		    }

		    public String getAsText() {
		        return new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());
		    }        

		});	    // as shown above
	}
}
