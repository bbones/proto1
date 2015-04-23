/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Receipt;
import org.proto1.domain.product.ReceiptItem;

public interface ReceiptService {

	List<Map<String, Object>> getReceiptList(Long languageId);

	/**
	 * @param receiptId
	 * @return
	 */
	Receipt get(Long receiptId);

	/**
	 * @param receiptId
	 * @return void
	 */
	void deleteReceipt(Long receiptId);

	/**
	 * @param Receipt receipt
	 * @return Receipt
	 */
	Receipt saveReceipt(Receipt receipt);

	List<Map<String, Object>> getIngredientsList(Long languageId, Long receiptId);

	List<Map<String, Object>> getByproductsList(Long languageId, Long receiptId);

	/**
	 * @param receiptId 
	 * @param ReceiptItem ingredient
	 * @return ReceiptItem
	 */
	ReceiptItem saveIngredient(Long receiptId, ReceiptItem ingredient);

	/**
	 * @param Long ingredientId
	 */
	void deleteIngredient(Long receiptId, Long ingredientId);



}
