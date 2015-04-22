/**
 * SimpleReceiptProvider.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.services.order;

import org.proto1.domain.product.Product;
import org.proto1.domain.product.Receipt;
import org.proto1.repository.product.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class SimpleReceiptProvider implements ReceiptProvider {
	
	@Autowired
	ReceiptRepository receiptRepository;

	/* (non-Javadoc)
	 * @see org.proto1.services.order.ReceiptProvider#getDefaultReceipt(org.proto1.domain.product.Product)
	 */
	public Receipt getDefaultReceipt(Product product) {
		return receiptRepository.getReceiptByProductAndByDefaultTrue(product);
	}

}
