package org.proto1.repository;

import java.util.Date;

import org.proto1.domain.Approve;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApproveRepository extends CrudRepository<Approve, Long> {

	@Query(" from Approve a where a.approveType.id=:approve_type_id and a.beginDate<=:actual_date and coalesce(a.endDate,'9999-01-01')>:actual_date")
	Approve getActualApprove(@Param("approve_type_id") Long approveTypeID, @Param("actual_date") Date actualDate );

}
