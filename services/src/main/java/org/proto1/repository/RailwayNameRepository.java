/** Rsk 09.07.2015 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.RailwayName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RailwayNameRepository extends CrudRepository<RailwayName, Long> {
	@Query("select new Map(rwn.id as id, rwn.language.id as languageId " +
			",rwn.shortName as shortName, rwn.fullName as fullName " +
			",rwn.railway.id as railwayId, rwn.version as version) " +
			"from RailwayName as rwn " +
			"where rwn.railway.id = :railway_id " +
			"order by rwn.language.id asc")
	List<Map<String, Object>> getList(@Param("railway_id") Long railwayId);
}
