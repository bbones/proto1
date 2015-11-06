package org.proto1.repository;

import java.util.List;

import org.proto1.domain.ApproveType;
import org.springframework.data.repository.CrudRepository;

public interface ApproveTypeRepository extends CrudRepository<ApproveType, Long> {

	 List<ApproveType> findByName(String name);

}
