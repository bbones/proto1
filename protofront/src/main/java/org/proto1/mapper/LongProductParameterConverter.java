/**
 * Project proto1
 * File LongProductParameterConverter.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created 08.07.15
 */

package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.product.ProductParameter;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongProductParameterConverter extends
		DozerConverter<Long, ProductParameter> {

	@Autowired
	ProductService productService;
	
	public LongProductParameterConverter() {
		super(Long.class, ProductParameter.class);
	}

	@Override
	public ProductParameter convertTo(Long source, ProductParameter destination) {
		return productService.getProductParameter(source);
	}

	@Override
	public Long convertFrom(ProductParameter source, Long destination) {
		return source.getId();
	}

}
