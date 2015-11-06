/*******************************************************************************
 * Copyright (C) 2015  Boris Efimenko
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
package org.proto1.workflow;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.proto1.domain.Approve;
import org.proto1.domain.ApproveType;
import org.proto1.domain.Document;
import org.proto1.domain.party.Person;
import org.proto1.repository.ApproveRepository;
import org.proto1.repository.ApproveTypeRepository;
import org.proto1.repository.DocumentRepository;
import org.proto1.repository.party.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ApproveDocumentServiceBean implements ApproveDocumentService {
	private final Logger log = LoggerFactory.getLogger(ApproveDocumentServiceBean.class);
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	ApproveRepository approveRepository;
	@Autowired
	ApproveTypeRepository approveTypeRepository;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	PersonRepository personRepository;

	public void startApprove(Long documentID, Long approveTypeID) {
		Document document = documentRepository.findOne(documentID);
		// TODO: Придумать откуда брать дату актуальности
		Date sysdate = document.getIssueDate();
		Assert.notNull(sysdate,"Issue date from document is null!");
		Approve approve = approveRepository.getActualApprove(approveTypeID, sysdate);
		// TODO:завести пользователей с датами. Взять активного пользователя.
		// Пока так
		Long curentUserID = 33l;
		Person currentPerson = personRepository.findOne(curentUserID);
		log.debug(currentPerson.getPassportNo());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("contractID", documentID);
		variables.put("initiator", currentPerson);
		// ProcessInstance processInstance =
		runtimeService.startProcessInstanceByKey(approve.getProcessDefinitionKey(), variables);

	}

	public void startApproveByName(Long documentID, String approveTypeName) {
		List<ApproveType> approveTypes=approveTypeRepository.findByName(approveTypeName);
		Assert.isTrue(approveTypes.size()==1,"approveTypeName.findByName('"+approveTypeName+"') return "+approveTypes.size()+ " records!");
		Long approveTypeID = approveTypes.get(0).getId();
		startApprove(documentID, approveTypeID);
	}
}
