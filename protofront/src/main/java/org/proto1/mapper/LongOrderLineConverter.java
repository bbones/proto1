/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
/**
 * TODO Access Rules!!!
 */
package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.order.OrderLine;
import org.proto1.services.order.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongOrderLineConverter extends DozerConverter<Long, OrderLine> {

	@Autowired
	OrderLineService orderLineService;
	
	public LongOrderLineConverter() {
		super(Long.class, OrderLine.class);
	}

	@Override
	public OrderLine convertTo(Long source, OrderLine destination) {
		if (source != null)
			return orderLineService.get(source);
		else
			return null;
	}

	@Override
	public Long convertFrom(OrderLine source, Long destination) {
		return source.getId();
	}

}
