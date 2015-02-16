package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.proto1.domain.party.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	@Query("select new Map(p.id as personId, pn.lastName as lastName, pn.firstName as firstName, pn.middleName as middleName) " + 
			"from Person p join p.personNames pn  " + 
			"where pn.language.id = :language_id")
	public List<Map<String, Object>> getListByLanguageId(@Param("language_id") Long languageId);

}
