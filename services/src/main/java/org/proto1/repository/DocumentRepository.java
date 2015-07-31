package org.proto1.repository;

import org.proto1.domain.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long>{

}
