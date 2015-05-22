package org.proto1.services;

import org.proto1.domain.party.Party;
import org.proto1.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyServiceBean implements PartyService {
	
	@Autowired
	PartyRepository partyRepository;

	public Party getParty(Long id) {
		return partyRepository.findOne(id);
	}

}
