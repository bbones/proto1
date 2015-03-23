/**
 * BOMServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.BOM;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.ProductionOrder;
import org.proto1.repository.order.BOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class BOMServiceBean implements BOMService {
	
	@Autowired
	ReceiptProvider receiptProvider; 
	
	@Autowired
	BOMRepository bomRepository;
	
	/* (non-Javadoc)
	 * @see org.proto1.services.order.BOMService#createBOM(org.proto1.domain.order.ProductionOrder)
	 */
	public void createBOM(ProductionOrder productionOrder) {
		for(OrderLine ol : productionOrder.getLines()) {
			BOM bom = new BOM();
			bom.setOrderLine(ol);
			bom.setReceipt(receiptProvider.getDefaultReceipt(ol.getProduct()));
			bom.setDocumentNo("BOM" + productionOrder.getDocumentNo()+"/"+ol.getId());
			bomRepository.save(bom);
		}

	}

	/* (non-Javadoc)
	 * @see org.proto1.services.order.BOMService#getBOMList(java.lang.Long)
	 */
	public List<Map<String, Object>> getBOMList(Long languageId) {
		return bomRepository.getBOMList();
	}

}
