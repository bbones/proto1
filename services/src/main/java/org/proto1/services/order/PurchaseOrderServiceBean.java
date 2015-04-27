package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.PurchaseOrder;
import org.proto1.repository.order.PurchaseOrderRepository;
import org.proto1.services.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceBean extends BaseOrderServiceBean<PurchaseOrder> implements PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	ApplicationConstants applicationConstant;


	public List<Map<String, Object>> getOrderList(Long languageId) {
		
		return purchaseOrderRepository.getList(languageId, 
				applicationConstant.getDefaultSellerRole().getId());
	}

	public void delete(Long orderId) {
		purchaseOrderRepository.delete(orderId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.PurchaseOrderService#save(org.proto1.domain.order.PurchaseOrder)
	 */
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		return purchaseOrderRepository.save(purchaseOrder);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BaseOrderService#get(java.lang.Long)
	 */
	public PurchaseOrder get(Long orderId) {
		return purchaseOrderRepository.findOne(orderId);
	}


}
