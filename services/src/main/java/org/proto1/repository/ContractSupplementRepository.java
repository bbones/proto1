/**
 * ContractSupplementRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 28, 2015
 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.ContractSupplement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface ContractSupplementRepository extends CrudRepository<ContractSupplement, Long> {
	@Query("select new Map( cs.id as supplementId, cs.documentNo as documentNo, cs.issueDate as issueDate, cur.charCode as currencyCode) " 
			+ "from ContractSupplement cs join cs.currency cur "
			+ "where cs.contract.id=:contract_id")
	public List<Map<String, Object>> list(@Param("contract_id") Long contractId);

}
