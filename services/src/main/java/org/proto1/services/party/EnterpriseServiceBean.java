/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.repository.party.EnterpriseNameRepository;
import org.proto1.repository.party.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	EnterpriseNameRepository enterpriseNameRepository;
	
	public Enterprise get(Long id) {
		return enterpriseRepository.findOne(id);
	}

	public void setEnterpriseRepository(EnterpriseRepository erep) {
		enterpriseRepository = erep;
	}

	public Enterprise save(Enterprise enterprise) {
		return enterpriseRepository.save(enterprise);
	}

	public void delete(Long id) {
		enterpriseRepository.delete(id);
	}

	public List<Map<String, Object>> getEnterpriseList(Long languageId) {
		return enterpriseRepository.getListByLanguageId(languageId);
	}

	public List<EnterpriseName> getNamesList(Long enterpriseId) {
		return enterpriseNameRepository.getByEnterpriseId(enterpriseId);
	}

	public Long getEnterpriseListCounter(Long languageId, String searchStr) {
		return enterpriseRepository.getEnterpriseCounter(languageId, searchStr);
	}

	public List<Map<String, Object>> getList(Long languageId, String searchStr,
			Pageable p) {
		return enterpriseRepository.partyList(languageId, searchStr, p);
	}

	public EnterpriseName saveName(EnterpriseName enterpriseName) {
		return enterpriseNameRepository.save(enterpriseName);
	}

	public void deleteName(Long id) {
		enterpriseNameRepository.delete(id);
		
	}

	public Long getEnterpriseListCounter(Long languageId, Enterprise exmpl) {
		return null; // enterpriseRepository.countById(exmpl);
	}
	
	public List<EnterpriseName> getList(Long languageId, Map<String, Object> example, Pageable p) {
		
		Specifications<EnterpriseName> qbe = null;
		
		for(final Map.Entry<String, Object> entry : example.entrySet()) {
			final String path[] = entry.getKey().split("\\.");
		
			Specification<EnterpriseName> spec = new Specification <EnterpriseName>() {

				public Predicate toPredicate(Root<EnterpriseName> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					logger.debug(entry.getValue().getClass().getName());
					if (path.length > 1) {
						Join<EnterpriseName, Enterprise> ent = root.join(path[0]);
						return cb.equal(ent.get(path[1]), entry.getValue());
					} else {
						if (entry.getValue().getClass().getName().equals("java.lang.String"))
							return cb.like(root.<String>get(path[0]), (Expression<String>) entry.getValue());
						else
							return cb.equal(root.get(path[0]), entry.getValue());
					}
					
				}
				
			};
			if (qbe == null) {
				qbe = Specifications.where(spec);
			} else {
				qbe.and(spec);
			}
		}
		return enterpriseNameRepository.findAll(qbe);
	}

	public Long getEnterpriseListCounter(Long languageId, Map<String, Object> example) {
		// TODO Auto-generated method stub
		return null;
	}

}
