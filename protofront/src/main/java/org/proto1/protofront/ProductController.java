/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductParameter;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.dto.ProductDTO;
import org.proto1.dto.ProductNameDTO;
import org.proto1.dto.ProductParameterDTO;
import org.proto1.services.MasterDataService;
import org.proto1.services.product.ProductService;
import org.proto1.services.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	final static Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ProductService productService;

	@Autowired
	ProductTypeService productTypeService;

	@Autowired
	MasterDataService mds;


	@RequestMapping(value = "types", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getListByProductType(@RequestParam Long productTypeId,
			@RequestParam Long languageId) {
		List<Map<String, Object>> prodList = productService.getList(productTypeId, 1L);
		return prodList;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getList(@RequestParam Long languageId) {
		List<Map<String, Object>> prodList = productService.getList(languageId);
		return prodList;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ProductDTO findByID(@PathVariable Long id) {
		Product product = productService.getById(id);
		return mapper.map(product, ProductDTO.class);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody ProductDTO submit(@RequestParam Long languageId, final ProductDTO productDTO) {
		Product product = mapper.map(productDTO, Product.class);
		product = productService.save(product);
		productService.saveProductName(productDTO.getId(), product.getId(), languageId, 
				productDTO.getProductName(), productDTO.getVersion());
		mapper.map(product, productDTO);
		return productDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		productService.delete(new Long(id));
	}

	// Product Names
	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<ProductNameDTO> getProductNames(@PathVariable Long id) {
		List<ProductNameDTO> pnList = new ArrayList<ProductNameDTO>();
		for(ProductName pn : productService.getNamesList(id))
			pnList.add(mapper.map(pn, ProductNameDTO.class));
		return pnList;
	}

	@RequestMapping(value = "names",  method = RequestMethod.POST)
	public @ResponseBody ProductNameDTO saveProductName(ProductNameDTO productNameDTO) {
		ProductName pn = productService.saveProductName(
				productNameDTO.getNameId(), productNameDTO.getProductId(), 
				productNameDTO.getLanguageId(), productNameDTO.getProductName(),
				productNameDTO.getVersion()
			);
		productNameDTO.setNameId(pn.getId());
		return productNameDTO;
	}

	@RequestMapping(value = "names", method = RequestMethod.DELETE)
	public String deleteName(@RequestParam Long id) {
		productService.deleteName(id);
		return "success";
	}

	// Product Parameters
	@RequestMapping(value = "{productId}/parameters/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, java.lang.Object>> getProductParametersList(@PathVariable Long productId, 
			@RequestParam Long languageId) {
		return productService.getParameterList(productId, languageId);
	}

	@RequestMapping(value = "parameters",  method = RequestMethod.POST)
	public @ResponseBody ProductParameterDTO saveProductParameter(@ModelAttribute  ProductParameterDTO productParameter) {
		ProductParameter pp = productService.saveProductParameter(productParameter.getProductId(), 
				productParameter.getParameterId(), productParameter.isRequired());
		productParameter = mapper.map(pp, ProductParameterDTO.class);
		return productParameter;
	}

	@RequestMapping(value = "parameters/{id}",  method = RequestMethod.DELETE)
	public String deleteProductParameter(@RequestParam  Long id) {
		productService.deleteProductParameter(id);
		return "success";
	}

	@RequestMapping(value = "getNewProduct", method = RequestMethod.POST)
	public ProductDTO getNewProduct(@RequestParam(required=false) Long productTypeId, @RequestParam(required=false) Long languageId) {
		Product product = new Product();
		product.setProductNames(new ArrayList<ProductName>());
		ProductType pt = productTypeService.getNodeById(productTypeId);
		product.setProductType(pt);
		String nameForSend = "Not in list";
		for (LocalizedStringConstant name : mds.getRequiredLocalizedStringList("productName")) {
			ProductName pdn = new ProductName();
			pdn.setLanguage(name.getLanguage());
			if(name.getLanguage().getId().equals(languageId))
				nameForSend = name.getText();
			pdn.setProduct(product);
			pdn.setName(name.getText());
			product.getProductNames().add(pdn);
		}
		product = productService.save(product);
		ProductDTO productDTO =  mapper.map(product, ProductDTO.class);
		productDTO.setProductName(nameForSend);
		return productDTO;
	}
	

}
