/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;

import org.proto1.domain.Language;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends CrudRepository<Language, Long> {
	
	@Query("from Language l where l.required = true")
	List<Language> getRequiredLanguageList();

	@Query("from LocalizedStringConstant lcs where lcs.key = :key and lcs.language.id = :language_id")
	LocalizedStringConstant getLocalizedStringConstant(@Param("key") String key, @Param("language_id") Long languageId);
	
	@Query("from LocalizedStringConstant lcs where lcs.key = :key")
	List<LocalizedStringConstant> getLocalizedStringConstantList(@Param("key") String key);

	@Query("from LocalizedStringConstant lcs where lcs.key = :key and lcs.language.required = true")
	List<LocalizedStringConstant> getRequiredLocalizedStringConstantList(@Param("key") String key);

}
