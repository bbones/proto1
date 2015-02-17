package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.repository.order.SalesOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderServiceBean implements SalesOrderService {
	
	@Autowired
	SalesOrderRepository salesOrderRepository;
	
	@Autowired
	ApplicationConstants applicationConstant;


	public List<Map<String, Object>> getSalesOrderList(Long languageId) {
		return salesOrderRepository.getListByLanguageId(languageId, 
				applicationConstant.getDefaultBuyerRole().getId());
	}

}
