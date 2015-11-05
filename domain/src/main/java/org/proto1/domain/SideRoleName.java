/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="SIDE_ROLE_NAME", uniqueConstraints=@UniqueConstraint(columnNames = {"SIDE_ROLE_ID", "LANGUAGE_ID"})) 
public class SideRoleName extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name="SIDE_ROLE_ID")
	private SideRole sideRole;

	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;

	private String name;

	public SideRole getSideRole() {
		return sideRole;
	}

	public void setSideRole(SideRole sideRole) {
		this.sideRole = sideRole;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
