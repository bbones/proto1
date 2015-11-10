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
/**
 * File ReceiptRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created 24/02/15
 *  
 */
package org.proto1.repository.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Product;
import org.proto1.domain.product.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
	@Query("select new Map(r.id as receiptId, r.documentNo as documentNo, "
			+ "r.product.id as productId, pn.name as productName, "
			+ "r.unitOfMeasurement.id as uomId, uomn.shortName as uomName) "
			+ "from Receipt r "
			+ "join r.product.productNames pn "
			+ "join r.unitOfMeasurement.unitOfMeasurementNames uomn "
			+ "where pn.language.id = :language_id and uomn.language.id = :language_id")
	public List<Map<String, Object>> getList(@Param("language_id") Long languageId);
	
	@Query("select new Map(r.id as receiptId, i.id as receiptItemId, "
			+ "i.product.id as productId, pn.name as productName,"
			+ "i.qnty as qnty, i.unitOfMeasurement.id as uomId, uomn.shortName as uomName, i.version as version ) "
			+ "from Receipt r join r.ingredients i join i.product.productNames pn "
			+ "join i.unitOfMeasurement uom join uom.unitOfMeasurementNames uomn "
			+ "where pn.language.id = :language_id and r.id=:receipt_id and uomn.language.id = :language_id ")
	public List<Map<String, Object>> getIngredientsList(@Param("language_id") Long languageId, @Param("receipt_id") Long receiptId);
	
	@Query("select new Map(r.id as receiptId, i.id as receiptItemId, "
			+ "i.product.id as productId, pn.name as productName,"
			+ "i.qnty as qnty, i.unitOfMeasurement.id as uomId, uomn.shortName as uomName ) "
			+ "from Receipt r join r.byProducts i join i.product.productNames pn "
			+ "join i.unitOfMeasurement uom join uom.unitOfMeasurementNames uomn "
			+ "where pn.language.id = :language_id and r.id=:receipt_id and uomn.language.id = :language_id ")
	public List<Map<String, Object>> getByProductsList(@Param("language_id") Long languageId, @Param("receipt_id") Long receiptId);
	
	public Receipt getReceiptByProductAndByDefaultTrue(Product product);
}

/*select r.id as receiptId, r.documentNo as documentNo, 
	r.product.id as productId, pn.name as productName, 
	r.unitOfMeasurement.id as uomId, uomn.shortName as uomName
from Receipt r 
	join r.product.productNames pn
	join r.unitOfMeasurement.unitOfMeasurementNames uomn
where 
	pn.language.id = :language_id and uomn.language.id = :language_id
*/

/*
	select r.id as receiptId, i.id as ingredId,
	i.product.id as ingredProdId, pn.name as ingredName, 
	i.qnty as qnty, dun.shortName 
	from Receipt r 
	join r.ingredients i join i.product.productNames pn 
	join i.unitOfMeasurement uom join uom.unitOfMeasurementNames uomn 
	where pn.language.id = :language_id and r.id=:receipt_id and dun.language.id = :language_id 
*/