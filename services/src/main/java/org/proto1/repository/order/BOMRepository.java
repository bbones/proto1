/**
 * BOMRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.BOM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface BOMRepository extends CrudRepository<BOM, Long> {

	/**
	 * @param languageId
	 * @return
	 */
	@Query("select new Map(bom.id as bomId, bom.documentNo as bomNo, bom.issueDate as bomIssueDate) "
			+ "from BOM bom")
	List<Map<String, Object>> getBOMList();

}
