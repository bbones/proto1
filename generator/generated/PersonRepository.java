package com.proto1.server.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
