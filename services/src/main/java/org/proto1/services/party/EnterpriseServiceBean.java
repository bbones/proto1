/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.services.party;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.repository.party.EnterpriseNameRepository;
import org.proto1.repository.party.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	EnterpriseNameRepository enterpriseNameRepository;
	
	@Autowired
	Client esclient;
	
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

	public String esindex() throws IOException {
		for(Enterprise ent : enterpriseRepository.findAll()) {
			XContentBuilder source = jsonBuilder()
	            .startObject()
		        .field("id", ent.getId())
		        .field("eskid", ent.getEskId());
	
	        source.field("names").startArray();
	        for (EnterpriseName en : ent.getEnterpriseNames()) {
	        	source
	        		.startObject()
	        			.field("language", en.getLanguage().getName())
	        			.field("name", en.getName())
	        			.endObject();
	        }
	        source.endArray().endObject();
			IndexResponse response = esclient.prepareIndex("proto1", "enterprise", ent.getId().toString()).setSource(source).get();
			if (!response.isCreated())
				return response.toString();
		}
		return "Done";
	}

}
