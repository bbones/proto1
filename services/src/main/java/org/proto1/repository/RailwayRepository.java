/** Rsk 09.07.2015 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Railway;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RailwayRepository extends CrudRepository<Railway, Long> {

	@Query("select new Map(rw.id as id, rw.railwayCode as railwayCode, " +
			"rw.version as version) " +
			"from Railway rw " +
			"order by rw.railwayCode asc")
	List<Map<String, Object>> getList();
	
	@Query("select rw " +
			"from Railway rw " +
			"where rw.railwayCode = :railway_code ")
	Railway findByRailwayCode(@Param("railway_code") Integer railwayCode);

}
