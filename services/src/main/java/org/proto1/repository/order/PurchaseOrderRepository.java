package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseOrderRepository extends BaseOrderRepository<PurchaseOrder> {
	@Query("select new Map(po.id as orderId, po.documentNo as orderNo, po.issueDate as issueDate, coalesce(pn.lastName, en.name) as customerName) " +
			"from PurchaseOrder po	join po.contractSupplement cs join cs.contract c join c.contractSides csides " + 
			"join csides.party p left join p.personNames pn	left join p.enterpriseNames en " +
			"where ((pn.language.id = :language_id) or (en.language.id = :language_id)) and csides.sideRole.id = :siderole_id")
	List<Map<String, Object>> getList(@Param("language_id") Long languageId, @Param("siderole_id") Long sideRoleId);
}
