/** Rsk 09.07.2015 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Railway;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RailwayRepository extends CrudRepository<Railway, Long> {

	@Query("select new Map(rw.id as id, rw.railwayCode as railwayCode, " +
			"rw.version as version) " +
			"from Railway rw ")
	List<Map<String, Object>> getList();

}
