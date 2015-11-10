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
package org.proto1.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="APPROVE")
public class Approve extends AbstractEntity {

	@NotNull
	private String name;
	@NotNull
	private String processDefinitionKey;
	@DateTimeFormat(pattern = "DD.MM.YY")
	@NotNull
	@Column(name="begin_date", nullable=false)
	private Date beginDate;
	@DateTimeFormat(pattern = "DD.MM.YY")
	@Column(name="end_date", nullable=true)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name="APPROVE_TYPE_ID", nullable=false,  referencedColumnName="id")
	private ApproveType approveType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ApproveType getApproveType() {
		return approveType;
	}

	public void setApproveType(ApproveType approveType) {
		this.approveType = approveType;
	}

}
