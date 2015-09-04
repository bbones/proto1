package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.product.ProductType;
import org.proto1.services.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongProductTypeConverter extends DozerConverter<Long, ProductType> {

	@Autowired
	ProductTypeService productTypeService;
	
	public LongProductTypeConverter() {
		super(Long.class, ProductType.class);
	}

	@Override
	public ProductType convertTo(Long source, ProductType destination) {
		return productTypeService.get(source);
	}

	@Override
	public Long convertFrom(ProductType source, Long destination) {
		return source != null ? source.getId() : null;
	}

}
