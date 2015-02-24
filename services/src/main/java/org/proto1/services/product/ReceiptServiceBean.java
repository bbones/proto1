/**
 * File ReceiptServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created 24/02/15
 * TODO 
 */
package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import org.proto1.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceBean implements ReceiptService {

	@Autowired
	ReceiptRepository receiptRepository;
	
	public List<Map<String, Object>> getReceiptList(Long languageId) {
		
		return receiptRepository.getListByLanguageId(languageId);
	}

	public List<Map<String, Object>> getIngredientsLis(Long languageId,
			Long receiptId) {

		return receiptRepository.getIngredientsList(languageId, receiptId);
	}

	public List<Map<String, Object>> getByproductsList(Long languageId,
			Long receiptId) {
		return receiptRepository.getByProductsList(languageId, receiptId);
	}

}
