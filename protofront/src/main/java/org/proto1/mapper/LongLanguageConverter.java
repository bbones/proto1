package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.Language;
import org.proto1.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;

public class LongLanguageConverter extends DozerConverter<Long, Language> {

	@Autowired
	LanguageService languageService;
	
	public LongLanguageConverter() {
		super(Long.class, Language.class);
	}

	@Override
	public Language convertTo(Long source, Language destination) {
		return languageService.get(source);
	}

	@Override
	public Long convertFrom(Language source, Long destination) {
		return source.getId();
	}

}
