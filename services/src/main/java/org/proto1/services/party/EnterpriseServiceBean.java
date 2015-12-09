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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.repository.party.EnterpriseNameRepository;
import org.proto1.repository.party.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	private final Logger log = LoggerFactory.getLogger(EnterpriseServiceBean.class);

	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	EnterpriseNameRepository enterpriseNameRepository;
	
	@Autowired
	Client esclient;
	
	@Override
	public Enterprise get(Long id) {
		return enterpriseRepository.findOne(id);
	}

	@Override
	public Enterprise save(Enterprise enterprise) {
		return enterpriseRepository.save(enterprise);
	}

	@Override
	public void delete(Long id) {
		enterpriseRepository.delete(id);
	}

	@Override
	public List<Map<String, Object>> getEnterpriseList(Long languageId) {
		return enterpriseRepository.getListByLanguageId(languageId);
	}

	@Override
	public List<EnterpriseName> getNamesList(Long enterpriseId) {
		return enterpriseNameRepository.getByEnterpriseId(enterpriseId);
	}

	@Override
	public Long getEnterpriseListCounter(Long languageId, String searchStr) {
		return enterpriseRepository.getEnterpriseCounter(languageId, searchStr);
	}

	@Override
	public List<Map<String, Object>> getList(Long languageId, String searchStr,
			Pageable p) {
		return enterpriseRepository.partyList(languageId, searchStr, p);
	}

	@Override
	public EnterpriseName saveName(EnterpriseName enterpriseName) {
		return enterpriseNameRepository.save(enterpriseName);
	}

	@Override
	public void deleteName(Long id) {
		enterpriseNameRepository.delete(id);
		
	}

	@Override
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

	@Override
	public Page<Map<String, Object>> search(Long languageId, Map<String, String> example, Pageable p) {
		cleanExample(example);
		List<QueryBuilder> qbl = new ArrayList<QueryBuilder>();
		for (Map.Entry<String, String> e : example.entrySet()) {
			log.debug(e.getKey() + "-->" + e.getValue());
			if (!e.getValue().isEmpty()) {
				if (e.getValue().contains("*")) {
					qbl.add(QueryBuilders.wildcardQuery(e.getKey(), e.getValue()));
				} else {
					qbl.add(QueryBuilders.matchQuery(e.getKey(), e.getValue()));
				}
			}
		} // End for
		if (qbl.isEmpty())
			return null;
		
		QueryBuilder qb;
		if (qbl.size() == 1) {
			qb = qbl.get(0);
		} else {
			qb = QueryBuilders.boolQuery();
			for (QueryBuilder q : qbl) {
				((BoolQueryBuilder)qb).must(q);
			}
		}
		log.debug(qb.toString());
		SearchResponse response = esclient.prepareSearch("test").setTypes("enterprise")
				.setQuery(qb).get();
		log.debug(response.toString());
		// Page<Map<String, Object>> pm = new PageImpl<Map<String, Object>>(content, pageable, total)
		return null;
	}

	private void cleanExample(Map<String, String> example) {
		example.remove("page");
		example.remove("rows");
		example.remove("languageId");
	}

}
