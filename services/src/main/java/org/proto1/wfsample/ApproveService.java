package org.proto1.wfsample;

import org.proto1.domain.party.Person;
import org.springframework.stereotype.Service;

@Service
public class ApproveService {
	public String findManagerForEmployee(Person person) {
		String manager = "ROLE_SUPERUSER";
		return manager;
	}
}
