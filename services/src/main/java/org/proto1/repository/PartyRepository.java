/**
 * PartyRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created May 12, 2015
 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface PartyRepository extends JpaRepository<Party, Long> {
	@Query("select new Map(p.id as id, "
			+ "coalesce(en.name, pn.lastName || ' ' ||  pn.firstName) as name) "
			+ "from Party p "
			+ "left join p.enterpriseNames en "
			+ "left join p.personNames pn "
			+ "where en.language.id=:language_id or pn.language.id=:language_id")
	public List<Map<String, Object>> partyList(@Param("language_id") Long languageId);
}
