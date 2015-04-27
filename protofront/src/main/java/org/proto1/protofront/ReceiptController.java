/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.Receipt;
import org.proto1.domain.product.ReceiptItem;
import org.proto1.dto.ReceiptDTO;
import org.proto1.dto.ReceiptItemDTO;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.product.ProductService;
import org.proto1.services.product.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
	
	@Autowired
	Mapper mapper;

	@Autowired
	ReceiptService receiptService;
	
	@Autowired
	ProductService productService;

	@Autowired
	UnitOfMeasurementService unitOfMeasurementService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  receiptList(@RequestParam Long languageId) {
		return receiptService.getReceiptList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody ReceiptDTO  save(@RequestParam Long languageId, ReceiptDTO receiptDTO) {
		Receipt receipt = new Receipt();
		if(receiptDTO.getReceiptId() != null) 
			receipt.setId(receiptDTO.getReceiptId());
		Product product = productService.getById(receiptDTO.getProductId());
		receipt.setProduct(product);
		UnitOfMeasurement uom = unitOfMeasurementService.get(receiptDTO.getUomId());
		receipt.setUnitOfMeasurement(uom);
		receipt.setVersion(receiptDTO.getVersion());
		receipt = receiptService.saveReceipt(receipt);
		receiptDTO.setReceiptId(receipt.getId());
		receiptDTO.setVersion(receipt.getVersion());
		return receiptDTO;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE )
	public void  delete(@PathVariable Long receiptId) {
		receiptService.deleteReceipt(receiptId);
	}
	
	@RequestMapping(value = "{receiptId}/ingredients", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  ingredientsList(@RequestParam Long languageId, 
			@PathVariable Long receiptId) {
		return receiptService.getIngredientsList(languageId, receiptId);
	}
	
	@RequestMapping(value = "{receiptId}/ingredients", method = RequestMethod.POST )
	public @ResponseBody ReceiptItemDTO  saveIngredient(@PathVariable Long receiptId, @RequestParam Long languageId, ReceiptItemDTO ingredientDTO) {
		ReceiptItem ingredient = mapReceiptItem(ingredientDTO);
		ingredient = receiptService.saveIngredient(receiptId, ingredient);
		ingredientDTO = mapReceiptItemDTO(ingredient);
		return ingredientDTO;
	}
	
	@RequestMapping(value = "{receiptId}/ingredients/{ingredientId}", method = RequestMethod.DELETE )
	public void  deleteIngredient(@PathVariable Long receiptId, @PathVariable Long ingredientId) {
		receiptService.deleteIngredient(receiptId, ingredientId);
	}
	
	@RequestMapping(value = "{receiptId}/byproducts", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  byproductsListByLanguage(@RequestParam Long languageId, 
			@PathVariable Long receiptId) {
		return receiptService.getByproductsList(languageId, receiptId);
	}
	
	/**
	 * @param ReceiptItem receiptItem
	 * @return
	 */
	private ReceiptItemDTO mapReceiptItemDTO(ReceiptItem receiptItem) {
		ReceiptItemDTO receiptItemDTO = new ReceiptItemDTO();
		receiptItemDTO.setReceiptItemId(receiptItem.getId());
		receiptItemDTO.setProductId(receiptItem.getProduct().getId());
		receiptItemDTO.setReceiptId(receiptItem.getReceipt().getId());
		receiptItemDTO.setQnty(receiptItem.getQnty());
		receiptItemDTO.setMaster(receiptItem.isMaster());
		receiptItemDTO.setVersion(receiptItem.getVersion());
		return receiptItemDTO;
	}

	/**
	 * @param ReceiptItemDTO receiptItemDTO
	 * @return ReceiptItem
	 * Doesn't map receipt!!!
	 */
	private ReceiptItem mapReceiptItem( ReceiptItemDTO receiptItemDTO) {
		ReceiptItem receiptItem = new ReceiptItem();
		if (receiptItemDTO.getReceiptItemId() != null)
			receiptItem.setId(receiptItemDTO.getReceiptItemId());
		Product product = productService.getById(receiptItemDTO.getProductId());
		receiptItem.setProduct(product);
		receiptItem.setMaster(receiptItemDTO.isMaster());
		receiptItem.setQnty(receiptItemDTO.getQnty());
		UnitOfMeasurement unitOfMeasurement = unitOfMeasurementService.get(receiptItemDTO.getUomId());
		receiptItem.setUnitOfMeasurement(unitOfMeasurement);
		return receiptItem;
	}


}
