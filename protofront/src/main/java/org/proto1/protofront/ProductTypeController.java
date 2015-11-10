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
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.dto.ProductTypeDTO;
import org.proto1.dto.ProductTypeNameDTO;
import org.proto1.services.MasterDataService;
import org.proto1.services.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {
	@Autowired
	ProductTypeService pts;
	
	@Autowired
	MasterDataService mds;

	@Autowired
	Mapper mapper;

	@RequestMapping(value = "parents", method = RequestMethod.GET)
	public List<Map<String, Object>> getByParentTypeIdByLanguageId(@RequestParam(required=false) Long id, @RequestParam Long languageId) {
		List<Map<String, Object>> ptList = pts.getByParentTypeIdByLanguageId(id, languageId);
		for(Map<String, Object> pt : ptList) {
			if (pts.countChild((Long)pt.get("id")) > 0)
				pt.put("state", "closed");
			else
				pt.put("state", "open");
		}
		return ptList;
	}
	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProductTypeDTO getNewProductType(@RequestParam(required=false) Long parentId, @RequestParam(required=false) Long languageId) {
		ProductType pt = new ProductType();
		if (parentId != null) {
			ProductType parent = pts.get(parentId);
			pt.setParentType(parent);
		}
		String nameForSend = "Not in list";
		for (LocalizedStringConstant name : mds.getRequiredLocalizedStringList("productType")) {
			ProductTypeName pdn = new ProductTypeName();
			pdn.setLanguage(name.getLanguage());
			if(name.getLanguage().getId().equals(languageId))
				nameForSend = name.getText();
			pdn.setProductType(pt);
			pdn.setName(name.getText());
			pt.getProductTypeNames().add(pdn);
		}
		pt = pts.save(pt);
		ProductTypeDTO ptDTO =  mapper.map(pt, ProductTypeDTO.class);
		ptDTO.setLocalizedProductName(nameForSend);
		return ptDTO;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteProductType(@PathVariable Long id) {
		pts.deleteProductTypeById(id);
	}

	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public List<ProductTypeNameDTO> getNames(@PathVariable Long id) {
		List<ProductTypeNameDTO> ptNamesList = new ArrayList<ProductTypeNameDTO>();
		for(ProductTypeName ptn : pts.getNames(id)) {

			ProductTypeNameDTO ptnDTO = mapper.map(ptn, ProductTypeNameDTO.class);
			ptNamesList.add(ptnDTO);
			
		}
		return ptNamesList;
	}

	@RequestMapping(value = "names", method = RequestMethod.POST)
	public ProductTypeNameDTO saveName(ProductTypeNameDTO productTypeNameDTO) {
		ProductTypeName ptn = mapper.map(productTypeNameDTO, ProductTypeName.class);
		ptn = pts.saveProductTypeName(ptn);
		productTypeNameDTO = mapper.map(ptn, ProductTypeNameDTO.class); 
		return productTypeNameDTO;
	}

}
