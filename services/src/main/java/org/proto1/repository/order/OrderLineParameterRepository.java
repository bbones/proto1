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
package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.OrderLineParameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderLineParameterRepository extends CrudRepository<OrderLineParameter, Long> {
	
	@Query("select new Map(olp.id as olpId, olp.value as olpValue, olp.orderLine.id as olId, olp.version as version, "
			+ "pp.id as prodParamId, pp.parameter.id as paramId, "
			+ "olp.derivative as derivative, pn.name as paramName, uom.id as uomId, uomn.shortName as uomName) "
			+ "from OrderLineParameter olp join olp.productParameter pp join pp.parameter.parameterNames pn "
			+ "left join olp.unitOfMeasurement uom left join uom.unitOfMeasurementNames uomn "
			+ "where olp.orderLine.id = :ol_id and pn.language.id = :language_id and (uomn.language.id = :language_id or uomn.language.id is null)")
	public List<Map<String, Object>> getOrderLineParameters(@Param("language_id") Long languageId, @Param("ol_id") Long orderLineId);

}

/* 
 * getOrderLineParameters
 */
/*
select olp.id as olpId, olp.value as olpValue,  olp.orderLine.id as olId, olp.version as version, 
	pp.id as prodParamId, olp.derivative as derivative, 
	pn.name as paramName, uom.id as uomId, uomn.shortName as uomName	
from OrderLineParameter olp join olp.productParameter pp join pp.parameter.parameterNames pn
	left join olp.unitOfMeasurement uom left join uom.unitOfMeasurementNames uomn 
where olp.orderLine.id = :ol_id and pn.language.id = :language_id and (uomn.language.id = :language_id or uomn.language.id is null)
*/