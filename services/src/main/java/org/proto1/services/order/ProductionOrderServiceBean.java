package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.ProductionOrder;
import org.proto1.repository.order.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionOrderServiceBean extends BaseOrderServiceBean implements ProductionOrderService  {
	
	@Autowired
	ProductionOrderRepository productionOrderRepository;

	public List<Map<String, Object>> getProductionOrderList(Long languageId) {
		return productionOrderRepository.getOrderList();
	}

	public ProductionOrder save(ProductionOrder productionOrder) {
		return productionOrderRepository.save(productionOrder);
	}

}
