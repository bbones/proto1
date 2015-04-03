/**
 * RequestRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.Request;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface RequestRepository extends BaseOrderRepository<Request> {
	@Query("select new Map(req.id as rId, req.documentNo as rNo, req.issueDate as rIssueDate) from Request req")
	public List<Map<String, Object>> getList();

}
