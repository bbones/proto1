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
package org.proto1.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerConverter;
import org.proto1.domain.product.Product;
import org.proto1.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongProductConverter extends DozerConverter<Long, Product> {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ProductService productService;
	
	public LongProductConverter() {
		super(Long.class, Product.class);
	}

	@Override
	public Product convertTo(Long source, Product destination) {
		logger.info("ProductId" + source);
		return productService.getById(source);
	}

	@Override
	public Long convertFrom(Product source, Long destination) {
		return source.getId();
	}

}
