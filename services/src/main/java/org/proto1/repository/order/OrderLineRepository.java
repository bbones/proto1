package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.OrderLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
	
	@Query("select new Map(ol.id as olId, ol.qnty as olQnty, ol.price as olPrice, ol.amount as olAmount, pn.name as productName, dun.shortName as duName) "
			+ "from OrderLine ol join ol.product p join p.productNames pn join ol.dimensionUnit du join du.dimensionUnitNames dun "
			+ "where pn.language.id = :language_id and dun.language.id = :language_id and ol.order.id =:so_id")
	
	public List<Map<String, Object>> getOrderLineList(@Param("language_id") Long languageId, @Param("so_id") Long salesOrderId);

}
