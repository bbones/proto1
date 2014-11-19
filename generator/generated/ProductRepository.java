package com.proto1.server.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
