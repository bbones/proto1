package org.proto1.protofront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductType;
import org.proto1.dto.ProductTypeDTO;
import org.proto1.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {
	@Autowired
	ProductTypeService pds;

	@RequestMapping(value = "getByParentTypeId", method = RequestMethod.POST)
	public List<ProductTypeDTO> getByParentTypeId(@RequestParam(required=false) Long id) {
		List<ProductTypeDTO> ptDTOlist = new ArrayList<ProductTypeDTO>();
		
		for(ProductType pt :  pds.getByParentTypeId(id)) {
			ProductTypeDTO ptDTO = new ProductTypeDTO();
			ptDTO.setId(pt.getId());
			ProductType parent = pt.getParentType();
			if (parent != null)
				ptDTO.setParentId(parent.getId());
			else
				ptDTO.setParentId(null);
			ptDTO.setName(pt.getProductTypeNames().get(0).getName());
			ptDTO.setState("closed");
			ptDTOlist.add(ptDTO);
		}
		return ptDTOlist;
	}

	@RequestMapping(value = "getByParentTypeIdByLanguageId/{languageId}", method = RequestMethod.POST)
	public List<Map<String, Object>> getByParentTypeIdByLanguageId(@RequestParam(required=false) Long id, @PathVariable Long languageId) {
		List<Map<String, Object>> ptList = pds.getByParentTypeIdByLanguageId(id, languageId);
		for(Map<String, Object> pt : ptList) {
			pt.put("state", "closed");
		}
		return ptList;
	}

}
