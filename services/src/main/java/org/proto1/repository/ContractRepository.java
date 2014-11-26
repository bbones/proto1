package org.proto1.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}
