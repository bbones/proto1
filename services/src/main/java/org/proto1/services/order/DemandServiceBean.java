/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
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
import org.proto1.domain.DimensionUnitName;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.product.Product;
import org.proto1.repository.DimensionUnitNameRepository;
import org.proto1.repository.DimensionUnitRepository;
import org.proto1.repository.ProductRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandServiceBean implements DemandService {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private class RegisterKey {
		private Long parameterId;
		private String parameterValue;
		private Long duId;
		private String duName;
		
		public RegisterKey(Long parameterId, String parameterValue, Long duId, String duName) {
			this.parameterId = parameterId;
			this.parameterValue = parameterValue;
			this.duId = duId;
			this.duName = duName; 
		}

		public Long getParameterId() {
			return parameterId;
		}

		public void setParameterId(Long parameterId) {
			this.parameterId = parameterId;
		}

		public String getParameterValue() {
			return parameterValue;
		}

		public void setParameterValue(String parameterValue) {
			this.parameterValue = parameterValue;
		}

		public Long getDuId() {
			return duId;
		}

		public void setDuId(Long duId) {
			this.duId = duId;
		}

		public String getDuName() {
			return duName;
		}

		public void setDuName(String duName) {
			this.duName = duName;
		}
		
		
	}

	@Autowired
	OrderLineRepository orderLineRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	DimensionUnitNameRepository dimensionUnitNameRepository;
	
	public List<Map<String, Object>> getConsolidatedDemand(Long languageId, Long productId,
			Long[] paramList) {
		Map<ArrayList<RegisterKey>, Double> register = new HashMap<ArrayList<RegisterKey>, Double> () ;
		
		for(OrderLine ol : orderLineRepository.getListByProductId(productId)) {
			List<Map<String, Object>> pvl = orderLineRepository.getParametersValues(paramList, ol.getId(), languageId);
			ArrayList<RegisterKey> key = new ArrayList<RegisterKey>();
			for(Map<String, Object> entry : pvl) {
				logger.info(entry.get("pid") +"->"+entry.get("pvalue") +" | " + entry.get("duId") + "->" + entry.get("duName"));
				key.add(new RegisterKey((Long)entry.get("pid"), (String)entry.get("pvalue"), (Long)entry.get("duId"), (String)entry.get("duName")) );
			}
			key.add(new RegisterKey(-1L, "DU", ol.getDimensionUnit().getId(), ""));
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
			Map<ArrayList<RegisterKey>, Double> register, Long languageId, Long[] paramList) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>> ();
		for(Map.Entry<ArrayList<RegisterKey>, Double> entry : register.entrySet()) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			for(RegisterKey key : entry.getKey()) {
				if (key.getParameterId() == -1) {
					map.put("olDUId", key.getDuId());
					map.put("olDUName", dimensionUnitNameRepository.getShortName(key.getDuId(), languageId));
				} else {
					map.put("F" + key.getParameterId(), key.getParameterValue());
					map.put("FDU" + key.getParameterId(), key.getDuId());
					map.put("FVD" + key.getParameterId(), key.getParameterValue() + ((key.getDuId() != null) ? " " + key.getDuName() : ""));
				}
			}
			
			map.put("qnty", entry.getValue());
			result.add(map);
		}	
		return result;
	}

	private void printRegister(Map<ArrayList<RegisterKey>, Double> register) {
		logger.debug("Register --------------");
		for(Map.Entry<ArrayList<RegisterKey>, Double> entry : register.entrySet()) {
			String str = "KEY:";
			for(RegisterKey par : entry.getKey()) {
				str += ":" + par.getParameterId() + "-" + par.getParameterValue() + " " + par.getDuName();
			}
			logger.debug(str + "->" + entry.getValue());
		}
	}

	
}
