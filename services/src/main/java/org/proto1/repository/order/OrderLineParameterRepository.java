/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.OrderLineParameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderLineParameterRepository extends CrudRepository<OrderLineParameter, Long> {
	
	@Query("select new Map(olp.id as olpId, olp.value as olpValue, pp.id as paramId, olp.derivative as derivative, pn.name as paramName, uom.id as uomId, uomn.shortName as uomName) "
			+ "from OrderLineParameter olp join olp.productParameter pp join pp.parameter.parameterNames pn "
			+ "left join olp.unitOfMeasurement uom left join uom.unitOfMeasurementNames uomn "
			+ "where olp.orderLine.id = :ol_id and pn.language.id = :language_id and (uomn.language.id = :language_id or uomn.language.id is null)")
	public List<Map<String, Object>> getOrderLineParameters(@Param("language_id") Long languageId, @Param("ol_id") Long orderLineId);

}

/* 
 * getOrderLineParameters
 */
/*
select olp.id as olpId, olp.value as olpValue, pp.id as prodParamId, olp.derivative as derivative, 
	pn.name as paramName, uom.id as uomId, uomn.shortName as uomName	
from OrderLineParameter olp join olp.productParameter pp join pp.parameter.parameterNames pn
	left join olp.unitOfMeasurement uom left join uom.unitOfMeasurementNames uomn 
where olp.orderLine.id = :ol_id and pn.language.id = :language_id and (uomn.language.id = :language_id or uomn.language.id is null)
*/