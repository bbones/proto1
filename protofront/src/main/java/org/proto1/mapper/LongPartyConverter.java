package org.proto1.mapper;

import org.dozer.DozerConverter;
import org.proto1.domain.party.Party;
import org.proto1.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;

public class LongPartyConverter extends DozerConverter<Long, Party> {
	
	@Autowired
	PartyService partyService;

	public LongPartyConverter() {
		super(Long.class, Party.class);
	}

	@Override
	public Party convertTo(Long source, Party destination) {
		return partyService.getParty(source);
	}

	@Override
	public Long convertFrom(Party source, Long destination) {
		return source.getId();
	}

}
