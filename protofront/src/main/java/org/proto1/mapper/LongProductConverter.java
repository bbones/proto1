package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.product.Product;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongProductConverter extends DozerConverter<Long, Product> {

	@Autowired
	ProductService productService;
	
	public LongProductConverter() {
		super(Long.class, Product.class);
	}

	@Override
	public Product convertTo(Long source, Product destination) {
		return productService.getById(source);
	}

	@Override
	public Long convertFrom(Product source, Long destination) {
		return source.getId();
	}

}
