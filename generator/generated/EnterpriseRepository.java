package com.proto1.server.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.Enterprise;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long> {

}
