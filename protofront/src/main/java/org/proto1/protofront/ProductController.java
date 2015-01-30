package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.dto.ProductDTO;
import org.proto1.dto.ProductNameDTO;
import org.proto1.services.ProductService;
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


	@RequestMapping(value = "names/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ProductNameDTO> getProductNames(@PathVariable String id) {
		List<ProductNameDTO> productNamesDTO = new ArrayList<ProductNameDTO>();
		
		Product product = productService.getById(new Long(id));

		for(ProductName entry : product.getProductNames() ) {
			productNamesDTO.add(new ProductNameDTO(product.getId(), entry.getLanguage(), entry.getName()));
		}
		return productNamesDTO;
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
