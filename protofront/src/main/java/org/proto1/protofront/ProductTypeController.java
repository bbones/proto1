package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.dto.ProductTypeDTO;
import org.proto1.dto.ProductTypeNameDTO;
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

	@Autowired
	Mapper mapper;

	@RequestMapping(value = "getNames/{id}", method = RequestMethod.GET)
	public List<ProductTypeNameDTO> getNames(@PathVariable Long id) {
		List<ProductTypeNameDTO> ptNamesList = new ArrayList<ProductTypeNameDTO>();
		for(ProductTypeName ptn : pds.getNames(id)) {

			ProductTypeNameDTO ptnDTO = mapper.map(ptn, ProductTypeNameDTO.class);
			ptNamesList.add(ptnDTO);
			
		}
		return ptNamesList;
	}

	@RequestMapping(value = "getByParentTypeIdByLanguageId/{languageId}", method = RequestMethod.POST)
	public List<Map<String, Object>> getByParentTypeIdByLanguageId(@RequestParam(required=false) Long id, @PathVariable Long languageId) {
		List<Map<String, Object>> ptList = pds.getByParentTypeIdByLanguageId(id, languageId);
		for(Map<String, Object> pt : ptList) {
			if (pds.countChild((Long)pt.get("id")) > 0)
				pt.put("state", "closed");
			else
				pt.put("state", "open");
		}
		return ptList;
	}
	
	@RequestMapping(value = "getNewProductType", method = RequestMethod.POST)
	public ProductTypeDTO getNewProductType(@RequestParam(required=false) Long parentId, @RequestParam(required=false) Long languageId) {
		ProductType pt = new ProductType();
		
		
		return mapper.map(pt, ProductTypeDTO.class);
	}

}
