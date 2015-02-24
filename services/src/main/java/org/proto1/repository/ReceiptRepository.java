/**
 * File ReceiptRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created 24/02/15
 *  
 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
	@Query("select new Map(r.id as receiptId, r.documentNo as receiptNo, r.product.id as productId, pn.name as productName) "
			+ "from Receipt r join r.product p join p.productNames pn "
			+ "where pn.language.id = :language_id")
	public List<Map<String, Object>> getListByLanguageId(@Param("language_id") Long languageId);
	
	@Query("select new Map(r.id as receiptId, i.id as ingredId, "
			+ "i.product.id as ingredProdId, pn.name as ingredName,"
			+ "i.qnty as qnty, dun.shortName as duName ) "
			+ "from Receipt r join r.ingredients i join i.product.productNames pn "
			+ "join i.dimensionUnit du join du.dimensionUnitNames dun "
			+ "where pn.language.id = :language_id and r.id=:receipt_id and dun.language.id = :language_id ")
	public List<Map<String, Object>> getIngredientsList(@Param("language_id") Long languageId, @Param("receipt_id") Long receiptId);
	
	@Query("select new Map(r.id as receiptId, i.id as ingredId, "
			+ "i.product.id as ingredProdId, pn.name as ingredName,"
			+ "i.qnty as qnty, dun.shortName as duName ) "
			+ "from Receipt r join r.byProducts i join i.product.productNames pn "
			+ "join i.dimensionUnit du join du.dimensionUnitNames dun "
			+ "where pn.language.id = :language_id and r.id=:receipt_id and dun.language.id = :language_id ")
	public List<Map<String, Object>> getByProductsList(@Param("language_id") Long languageId, @Param("receipt_id") Long receiptId);
}

/* getListByLanguageId
	select r.id as receiptId, r.documentNo as receiptNo, r.product.id as productId, pn.name as productName 
	from Receipt r join r.product p join p.productNames pn
	where pn.language.id = :language_id
*/

/*
	select r.id as receiptId, i.id as ingredId,
	i.product.id as ingredProdId, pn.name as ingredName, 
	i.qnty as qnty, dun.shortName 
	from Receipt r 
	join r.ingredients i join i.product.productNames pn 
	join i.dimensionUnit du join du.dimensionUnitNames dun
	where pn.language.id = :language_id and r.id=:receipt_id and dun.language.id = :language_id 
*/