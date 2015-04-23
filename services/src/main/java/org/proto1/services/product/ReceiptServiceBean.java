/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File ReceiptServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created 24/02/15
 * TODO 
 */
package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.product.Receipt;
import org.proto1.domain.product.ReceiptItem;
import org.proto1.repository.product.ReceiptItemRepository;
import org.proto1.repository.product.ReceiptRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceBean implements ReceiptService {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ReceiptRepository receiptRepository;
	
	@Autowired
	ReceiptItemRepository receiptItemRepository;
	
	// Receipt methods
	
	public List<Map<String, Object>> getReceiptList(Long languageId) {
		
		return receiptRepository.getList(languageId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ReceiptService#get(java.lang.Long)
	 */
	public Receipt get(Long receiptId) {
		return receiptRepository.findOne(receiptId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ReceiptService#saveReceipt(org.proto1.domain.product.Receipt)
	 */
	public Receipt saveReceipt(Receipt receipt) {
		return receiptRepository.save(receipt);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ReceiptService#deleteReceipt(java.lang.Long)
	 */
	public void deleteReceipt(Long receiptId) {
		receiptRepository.delete(receiptId);
		
	}

	// Ingredient methods
	
	public List<Map<String, Object>> getIngredientsList(Long languageId,
			Long receiptId) {

		return receiptRepository.getIngredientsList(languageId, receiptId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ReceiptService#saveIngredient(org.proto1.domain.product.ReceiptItem)
	 */
	@Transactional
	public ReceiptItem saveIngredient(Long receiptId, ReceiptItem ingredient) {
		ReceiptItem result = ingredient;

		Receipt receipt = receiptRepository.findOne(receiptId);
		ingredient.setReceipt(receipt);

		if (ingredient.getId() == null) {
			receipt.getIngredients().add(ingredient);
		} else {
			for (ReceiptItem ri : receipt.getIngredients()) {
				if (ri.getId().equals(ingredient.getId())) {
					logger.debug("Found for update");
					BeanUtils.copyProperties(ingredient, ri);
					result = ri;
				}
			}
		}
		receiptRepository.save(receipt);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ReceiptService#deleteIngredient(java.lang.Long)
	 */
	public void deleteIngredient(Long receiptId, Long ingredientId) {
		Receipt receipt = receiptRepository.findOne(receiptId);
		ReceiptItem riForDelete = null;
		for (ReceiptItem ri : receipt.getIngredients()) {
			if (ri.getId().equals(ingredientId)) {
				logger.debug("Found for delete");
				riForDelete = ri;
			}
		}
		receipt.getIngredients().remove(riForDelete);
		receiptRepository.save(receipt);
	}

	// Byproducts methods
	
	public List<Map<String, Object>> getByproductsList(Long languageId,
			Long receiptId) {
		return receiptRepository.getByProductsList(languageId, receiptId);
	}


}
