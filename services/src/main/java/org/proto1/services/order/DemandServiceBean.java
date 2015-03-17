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
import org.proto1.domain.UnitOfMeasurementName;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.product.Product;
import org.proto1.repository.UnitOfMeasurementNameRepository;
import org.proto1.repository.UnitOfMeasurementRepository;
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
		private Long uomId;
		private String uomName;
		
		public RegisterKey(Long parameterId, String parameterValue, Long uomId, String uomName) {
			this.parameterId = parameterId;
			this.parameterValue = parameterValue;
			this.uomId = uomId;
			this.uomName = uomName; 
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

		public Long getUomId() {
			return uomId;
		}

		public void setUomId(Long uomId) {
			this.uomId = uomId;
		}

		public String getUomName() {
			return uomName;
		}

		public void setUomName(String uomName) {
			this.uomName = uomName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((parameterId == null) ? 0 : parameterId.hashCode());
			result = prime
					* result
					+ ((parameterValue == null) ? 0 : parameterValue.hashCode());
			result = prime * result + ((uomId == null) ? 0 : uomId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RegisterKey other = (RegisterKey) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (parameterId == null) {
				if (other.parameterId != null)
					return false;
			} else if (!parameterId.equals(other.parameterId))
				return false;
			if (parameterValue == null) {
				if (other.parameterValue != null)
					return false;
			} else if (!parameterValue.equals(other.parameterValue))
				return false;
			if (uomId == null) {
				if (other.uomId != null)
					return false;
			} else if (!uomId.equals(other.uomId))
				return false;
			return true;
		}

		private DemandServiceBean getOuterType() {
			return DemandServiceBean.this;
		}

		
	}

	@Autowired
	OrderLineRepository orderLineRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UnitOfMeasurementNameRepository unitOfMeasurementNameRepository;
	
	public List<Map<String, Object>> getConsolidatedDemand(Long languageId, Long productId,
			Long[] paramList) {
		Map<ArrayList<RegisterKey>, Double> register = new HashMap<ArrayList<RegisterKey>, Double> () ;
		
		for(OrderLine ol : orderLineRepository.getListByProductId(productId)) {
			List<Map<String, Object>> pvl = orderLineRepository.getParametersValues(paramList, ol.getId(), languageId);
			ArrayList<RegisterKey> key = new ArrayList<RegisterKey>();
			for(Map<String, Object> entry : pvl) {
				logger.info(entry.get("pid") +"->"+entry.get("pvalue") +" | " + entry.get("uomId") + "->" + entry.get("uomName"));
				key.add(new RegisterKey((Long)entry.get("pid"), (String)entry.get("pvalue"), (Long)entry.get("uomId"), (String)entry.get("uomName")) );
			}
			key.add(new RegisterKey(-1L, "UOM", ol.getUnitOfMeasurement().getId(), ""));
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
					map.put("olUOMId", key.getUomId());
					map.put("olUOMName", unitOfMeasurementNameRepository.getShortName(key.getUomId(), languageId));
				} else {
					map.put("F" + key.getParameterId(), key.getParameterValue());
					map.put("FUOM" + key.getParameterId(), key.getUomId());
					map.put("FVD" + key.getParameterId(), key.getParameterValue() + ((key.getUomId() != null) ? " " + key.getUomName() : ""));
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
				str += ":" + par.getParameterId() + "-" + par.getParameterValue() + " " + par.getUomName();
			}
			logger.debug(str + "->" + entry.getValue() + ":HASH:" + entry.hashCode());
		}
	}

	
}
