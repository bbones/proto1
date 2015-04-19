package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.repository.order.PurchaseOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseOrderServiceBean extends BaseOrderServiceBean implements
		PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	ApplicationConstants applicationConstant;


	public List<Map<String, Object>> getOrderList(Long languageId) {
		
		return purchaseOrderRepository.getList(languageId, 
				applicationConstant.getDefaultSellerRole().getId());
	}

	public void delete(Long orderId) {
		// TODO Auto-generated method stub

	}

}
