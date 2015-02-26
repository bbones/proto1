/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */
package org.proto1.services.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.product.Product;
import org.proto1.repository.ProductRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandServiceBean implements DemandService {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	OrderLineRepository orderLineRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Map<String, Object>> getConsolidatedDemand(Long languageId, Long productId,
			Long[] paramList) {
		Map<ArrayList<String>, Double> register = new HashMap<ArrayList<String>, Double> () ;
		
		for(OrderLine ol : orderLineRepository.getListByProductId(productId)) {
			List<Map<String, Object>> pvl = orderLineRepository.getParametersValues(paramList, ol.getId());
			ArrayList<String> key = new ArrayList<String>();
			for(Map<String, Object> entry : pvl) {
				logger.info(entry.get("pid") +"->"+entry.get("pvalue"));
				key.add(entry.get("pid")+ "&" + entry.get("pvalue") );
			}
			Double oldValue = register.get(key);
			if (oldValue != null) {
				register.put(key,  oldValue + ol.getQnty());
			} else {
				register.put(key,  ol.getQnty());
			}
		}
		printRegister(register);
		
		return registerToJSON(register, languageId, paramList);
	}

	private List<Map<String, Object>> registerToJSON(
			Map<ArrayList<String>, Double> register, Long languageId, Long[] paramList) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>> ();
		for(Map.Entry<ArrayList<String>, Double> entry : register.entrySet()) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			for(String key : entry.getKey()) {
				map.put("F" + key.substring(0,key.indexOf('&')), key.substring(key.indexOf('&')+1));
			}
			map.put("qnty", entry.getValue());
			result.add(map);
		}	
		return result;
	}

	private void printRegister(Map<ArrayList<String>, Double> register) {
		logger.debug("Register --------------");
		for(Map.Entry<ArrayList<String>, Double> entry : register.entrySet()) {
			logger.debug(entry.getKey() + "->" + entry.getValue());
		}
	}

	
}
