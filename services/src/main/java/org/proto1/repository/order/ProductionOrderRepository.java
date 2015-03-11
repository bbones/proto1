package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.ProductionOrder;
import org.springframework.data.jpa.repository.Query;

public interface ProductionOrderRepository extends BaseOrderRepository<ProductionOrder> {

	@Query("select new Map(po.id as poId, po.documentNo as poNo, po.issueDate as poIssueDate) "
			+ "from ProductionOrder po")
	List<Map<String, Object>> getOrderList();

}

