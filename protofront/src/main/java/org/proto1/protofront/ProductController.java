package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.dozer.Mapper;
import org.proto1.domain.Language;
import org.proto1.domain.product.Product;
import org.proto1.dto.ProductDTO;
import org.proto1.dto.ProductNameDTO;
import org.proto1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	Mapper mapper;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "submit", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody ProductDTO submit(final ProductDTO productDTO) {
		Product product = mapper.map(productDTO, Product.class);
		product = productService.save(product);
		mapper.map(product, productDTO);
		return productDTO;
	}

	@RequestMapping(value = "findByID/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProductDTO findByID(@PathVariable String id) {
		Product product = productService.getById(new Long(id));
		return mapper.map(product, ProductDTO.class);
	}

	@RequestMapping(value = "deleteByID/{id}", method = RequestMethod.POST, produces = "application/json")
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

		for(Map.Entry<Language, String> entry : productService.getById(new Long(id)).getProductNames().entrySet()) {
			productNamesDTO.add(new ProductNameDTO(entry.getKey(), entry.getValue()));
		}
		return productNamesDTO;
	}
}
