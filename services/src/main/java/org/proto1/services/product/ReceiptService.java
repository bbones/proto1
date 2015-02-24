package org.proto1.services.product;

import java.util.List;
import java.util.Map;

public interface ReceiptService {

	List<Map<String, Object>> getReceiptList(Long languageId);

	List<Map<String, Object>> getIngredientsLis(Long languageId, Long receiptId);

	List<Map<String, Object>> getByproductsList(Long languageId, Long receiptId);

}
