/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
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
