/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.proto1.services.product.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	@Autowired
	ReceiptService receiptService;
	
	
	@RequestMapping(value = "listbylang/{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  receiptListByLanguage(@PathVariable Long languageId) {
		return receiptService.getReceiptList(languageId);
	}
	
	@RequestMapping(value = "ingredients/{languageId}&{receiptId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  ingredientsListByLanguage(@PathVariable Long languageId, 
			@PathVariable Long receiptId) {
		return receiptService.getIngredientsLis(languageId, receiptId);
	}
	
	@RequestMapping(value = "byproducts/{languageId}&{receiptId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  byproductsListByLanguage(@PathVariable Long languageId, 
			@PathVariable Long receiptId) {
		return receiptService.getByproductsList(languageId, receiptId);
	}
	

}
