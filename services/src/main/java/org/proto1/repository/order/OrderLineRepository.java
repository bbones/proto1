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

import org.proto1.domain.order.OrderLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
	
	@Query("select new Map("
			+ "ol.id as orderLineId, ol.order.id as orderId, ol.product.id as productId, ol.unitOfMeasurement.id as uomId, "
			+ "ol.qnty as qnty, ol.price as price, ol.amount as amount, ol.version as version,"
			+ "pn.name as productName, uomn.shortName as uomName) "
			+ "from OrderLine ol join ol.product p join p.productNames pn join ol.unitOfMeasurement uom join uom.unitOfMeasurementNames uomn "
			+ "where pn.language.id = :language_id and uomn.language.id = :language_id and ol.order.id =:order_id")
	
	public List<Map<String, Object>> getOrderLineList(@Param("order_id") Long orderId, @Param("language_id") Long languageId);
	
	public List<OrderLine> getListByProductId(Long productId);
	
	@Query("from OrderLine ol "
			+ "where (ol.product.id = :product_id) and "
			+ "((ol.order.id in (select id from SalesOrder)) or (ol.order.id in (select id from BOM)))")
	public List<OrderLine> getUncoveredDemandLines(@Param("product_id") Long productId);
	
	@Query("select new Map(olp.productParameter.id as pid, olp.value as pvalue,  olp.unitOfMeasurement.id as uomId, uomn.shortName as uomName) "
			+ "from OrderLine ol join ol.orderLineParameterList olp left outer join olp.unitOfMeasurement.unitOfMeasurementNames uomn "
			+ "where olp.productParameter.id in :param_list and ol.id = :order_line_id and (uomn.language.id = :language_id or uomn.language.id is null)")
	public List<Map<String, Object>> getParametersValues(@Param("param_list") Long[] parameterList, @Param("order_line_id") Long orderLineId,
			@Param("language_id") Long languageId);
	
	@Query("select new Map(ol.id as id, ol.qnty as qntyu) "
			+ "from OrderLine ol where ol.product.id=:product_id")
	public List<Map<String, Object>> getLBPID(@Param("product_id") Long productId);

}

/*
select olp.productParameter.id as pid, olp.value as pvalue,  olp.unitOfMeasurement.id as uomId, uomn.shortName as uomName
from OrderLine ol join ol.orderLineParameterList olp left outer join olp.unitOfMeasurement.unitOfMeasurementNames uomn 
where olp.productParameter.id in (1,2,3) and ol.id = :order_line_id and (uomn.language.id = :language_id or uomn.language.id is null)
 */

