package org.proto1.repository;

import org.proto1.domain.Language;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends CrudRepository<Language, Long> {
	
	@Query("from LocalizedStringConstant lcs where lcs.key = :key and lcs.language.id = :language_id")
	LocalizedStringConstant getLocalizedStringConstant(@Param("key") String key, @Param("language_id") Long languageId);

}
