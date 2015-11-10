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
