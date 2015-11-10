/*******************************************************************************
 * Copyright (C) 2015   Boris Efimenko
 *
 * mail:Boris.Efimenko@isd.com.ua
 * https://github.com/BorisEfimenko
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
package org.proto1.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PlainSpecification<T> implements Specification<T> {

	private SearchCriteria criteria;

	public PlainSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if ((criteria.getValue() == null) || criteria.getValue().toString().equals("")) {
			// if value is null then always true
			return cb.and();
		} else if (criteria.getOperation().equalsIgnoreCase("=")) {
			return cb.equal(root.<String> get(criteria.getKey()), criteria
					.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(">")) {
			return cb.greaterThanOrEqualTo(
					root.<String> get(criteria.getKey()), criteria.getValue()
							.toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return cb.lessThanOrEqualTo(root.<String> get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("like")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return cb.like(root.<String> get(criteria.getKey()), criteria
						.getValue().toString());
			} else {
				return cb.equal(root.get(criteria.getKey()),
						criteria.getValue());
			}
		}
		return null;
	}
}