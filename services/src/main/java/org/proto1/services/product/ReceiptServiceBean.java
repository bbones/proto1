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

import org.proto1.domain.product.Receipt;
import org.proto1.domain.product.ReceiptItem;
import org.proto1.repository.product.ReceiptItemRepository;
import org.proto1.repository.product.ReceiptRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceBean implements ReceiptService {

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
	public ReceiptItem saveIngredient(ReceiptItem ingredient) {
		ReceiptItem result = ingredient;
		if (ingredient.getId() == null) {
			ingredient.getReceipt().getIngredients().add(ingredient);
		} else {
			for (ReceiptItem ri : ingredient.getReceipt().getIngredients()) {
				if (ri.getId() == ingredient.getId()) {
					BeanUtils.copyProperties(ingredient, ri);
					result = ri;
				}
			}
		}
		receiptRepository.save(ingredient.getReceipt());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.product.ReceiptService#deleteIngredient(java.lang.Long)
	 */
	public void deleteIngredient(Long ingredientId) {
		// TODO Auto-generated method stub
		
	}

	// Byproducts methods
	
	public List<Map<String, Object>> getByproductsList(Long languageId,
			Long receiptId) {
		return receiptRepository.getByProductsList(languageId, receiptId);
	}


}
