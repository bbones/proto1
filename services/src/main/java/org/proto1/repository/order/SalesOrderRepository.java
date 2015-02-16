package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long> {
	@Query("select new Map(e.id as enterpriseId, en.name as enterpriseName) " + 
			"from Enterprise e join e.enterpriseNames en  " + 
			"where en.language.id = :language_id")
	public List<Map<String, Object>> getListByLanguageId(@Param("language_id") Long languageId);

}
