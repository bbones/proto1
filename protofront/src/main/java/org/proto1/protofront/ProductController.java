package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.dto.ProductDTO;
import org.proto1.dto.ProductNameDTO;
import org.proto1.dto.ProductTypeDTO;
import org.proto1.services.MasterDataService;
import org.proto1.services.ProductService;
import org.proto1.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/product")
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


	@RequestMapping(value = "prodListByProdTypeIdByLanguageId/{productTypeId}&{languageId}", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> prodListByProdTypeIdByLanguageId(@PathVariable Long productTypeId, @PathVariable Long languageId) {
		List<Map<String, Object>> prodList = productService.getListByProdTypeIdByLanguageId(productTypeId, languageId);
		return prodList;
	}

	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public @ResponseBody ProductDTO submit(final ProductDTO productDTO) {
		Product product = mapper.map(productDTO, Product.class);
		product = productService.save(product);
		mapper.map(product, productDTO);
		return productDTO;
	}

	@RequestMapping(value = "findByID/{id}", method = RequestMethod.GET)
	public @ResponseBody ProductDTO findByID(@PathVariable String id) {
		Product product = productService.getById(new Long(id));
		return mapper.map(product, ProductDTO.class);
	}

	@RequestMapping(value = "deleteByID/{id}", method = RequestMethod.POST)
	public void delete(@PathVariable String id) {
		productService.delete(new Long(id));
	}


	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public RedirectView findByURLID(@PathVariable String id) {
		
		return new RedirectView("/protofront/index.html#Product:"+id);
	}


	@RequestMapping(value = "names/{id}", method = RequestMethod.POST)
	public @ResponseBody List<ProductNameDTO> getProductNames(@PathVariable String id) {
		List<ProductNameDTO> pnList = new ArrayList<ProductNameDTO>();
		for(ProductName pn : productService.getNamesList(new Long(id)))
			pnList.add(mapper.map(pn, ProductNameDTO.class));
		return pnList;
	}

	@RequestMapping(value = "getNewProduct", method = RequestMethod.POST)
	public ProductDTO getNewProduct(@RequestParam(required=false) Long productTypeId, @RequestParam(required=false) Long languageId) {
		Product product = new Product();
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
		productDTO.setLocalizedProductName(nameForSend);
		return productDTO;
	}

	@RequestMapping(value = "savenames",  method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public @ResponseBody List<ProductNameDTO> saveProductNames(@RequestBody ArrayList<ProductNameDTO> productNames) {
		if (productNames.size() > 0) {
		
			for(ProductNameDTO nameDTO : productNames ) 
				productService.saveProductName(nameDTO.getProductId(), nameDTO.getLanguageId(), nameDTO.getProductName());
			return productNames;
		} else 
			return null;
	}
	
	@RequestMapping(value = "savename",  method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public @ResponseBody ProductNameDTO saveProductName(@RequestBody ProductNameDTO productName) {
		logger.debug(productName.getProductName());
		return productName;
	}

	@RequestMapping(value = "deleteName/{productId},{languageId}", method = RequestMethod.POST)
	public void deleteName(@PathVariable String productId, @PathVariable String languageId) {
		productService.deleteName(new Long(productId), new Long(languageId));
	}

}
