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
	MasterDataService mds;

	@Autowired
	Mapper mapper;

	@RequestMapping(value = "getByParentTypeIdByLanguageId/{languageId}", method = RequestMethod.POST)
	public List<Map<String, Object>> getByParentTypeIdByLanguageId(@RequestParam(required=false) Long id, @PathVariable Long languageId) {
		List<Map<String, Object>> ptList = pds.getByParentTypeIdByLanguageId(id, languageId);
		for(Map<String, Object> pt : ptList) {
			if (pds.countChild((Long)pt.get("id")) > 0)
				pt.put("state", "closed");
			else
				pt.put("state", "open");
			pt.put("text", pt.get("name")); // "text" field for EasyUI tree and combotree
		}
		return ptList;
	}
	
	@RequestMapping(value = "getNewProductType", method = RequestMethod.POST)
	public ProductTypeDTO getNewProductType(@RequestParam(required=false) Long parentId, @RequestParam(required=false) Long languageId) {
		ProductType pt = new ProductType();
		ProductType parent = pds.getNodeById(parentId);
		pt.setParentType(parent);
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
		pt = pds.save(pt);
		ProductTypeDTO ptDTO =  mapper.map(pt, ProductTypeDTO.class);
		ptDTO.setLocalizedProductName(nameForSend);
		return ptDTO;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteProductType(@RequestParam(required=false) Long id) {
		pds.deleteProductTypeById(id);
	}

	@RequestMapping(value = "getNames/{id}", method = RequestMethod.POST)
	public List<ProductTypeNameDTO> getNames(@PathVariable Long id) {
		List<ProductTypeNameDTO> ptNamesList = new ArrayList<ProductTypeNameDTO>();
		for(ProductTypeName ptn : pds.getNames(id)) {

			ProductTypeNameDTO ptnDTO = mapper.map(ptn, ProductTypeNameDTO.class);
			ptNamesList.add(ptnDTO);
			
		}
		return ptNamesList;
	}

	@RequestMapping(value = "saveName", method = RequestMethod.POST)
	public void updateName(@RequestParam(required=false) Long nameId, @RequestParam(required=false) Long productTypeId, @RequestParam(required=false) Long languageId, 
			@RequestParam(required=false) String productTypeName) {
		pds.saveProductTypeName(nameId, productTypeId, languageId, productTypeName);
	}

}
