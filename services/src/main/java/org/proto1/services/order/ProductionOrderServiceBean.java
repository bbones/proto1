package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.OrderLine;
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

	/* (non-Javadoc)
	 * @see org.proto1.services.order.ProductionOrderService#createOrderBOMs(java.lang.Long)
	 */
	public void createOrderBOMs(Long productionOrderId) {
		ProductionOrder po = productionOrderRepository.findOne(productionOrderId);
		for(OrderLine ol : po.getLines()) {
			
		}
	}

}
