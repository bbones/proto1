package org.proto1.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.Period;

public interface PeriodRepository extends CrudRepository<Period, Long> {

}