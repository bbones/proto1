/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.Receipt;
import org.proto1.domain.product.ReceiptItem;
import org.proto1.repository.product.ProductRepository;
import org.proto1.repository.product.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml", "classpath:/META-INF/product.xml","classpath:/META-INF/applicationContext.xml"})
public class RepositoryBehaviorTest extends AbstractJUnit4SpringContextTests{
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ReceiptRepository receiptRepository ;
	
	@Autowired
	ProductRepository pr;
	
	@Test
	public void test() {
		
		Product product = pr.findOne(148L);
		
		Receipt receipt = receiptRepository.findOne(235L);
		for(ReceiptItem ri : receipt.getIngredients()) {
			logger.debug(ri.getId() + "--" + ri.getReceipt().getDocumentNo() + "####" + ri.getProduct().getId());
			if (ri.getId() == 312) {
				logger.debug("Updating...");
				ri.setProduct(product);
				
			}
		}
		receiptRepository.save(receipt);
	}
	
}
