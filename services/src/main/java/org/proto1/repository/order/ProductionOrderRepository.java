package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.ProductionOrder;
import org.springframework.data.jpa.repository.Query;

public interface ProductionOrderRepository extends BaseOrderRepository<ProductionOrder> {

	@Query("select new Map(po.id as id, po.documentNo as documentNo, po.issueDate as issueDate) "
			+ "from ProductionOrder po")
	List<Map<String, Object>> getOrderList();

}

