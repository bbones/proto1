package org.proto1.workflow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.proto1.domain.Approve;
import org.proto1.domain.Document;
import org.proto1.domain.party.Person;
import org.proto1.repository.ApproveRepository;
import org.proto1.repository.DocumentRepository;
import org.proto1.repository.party.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ApproveDocumentServiceBean implements ApproveDocumentService {
	private final Logger log = LoggerFactory.getLogger(ApproveDocumentServiceBean.class);
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	ApproveRepository approveRepository;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	PersonRepository personRepository;

	public void startApprove(Long documentID, Long approveTypeID) {
		Document document = documentRepository.findOne(documentID);
		// TODO: Придумать откуда брать дату актуальности
		Date sysdate = java.util.Calendar.getInstance().getTime();
		Approve approve = approveRepository.getActualApprove(
				approveTypeID, sysdate);
		// TODO:завести пользователей с датами. Взять активного пользователя.
		// Пока так
		Long curentUserID = 33l;
		Person currentPerson = personRepository.findOne(curentUserID);
		log.debug(currentPerson.getPassportNo());
		Map<String,Object> variables=new HashMap<String,Object>();
		  variables.put("document",document);
		  variables.put("initiator",currentPerson);
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(approve.getProcessDefinitionKey(), variables);
		
	}
}
