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
package org.proto1.security;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3702595391494687419L;
	private String authority;

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UserGrantedAuthority(String authority) {
		this.authority = authority;
	}

	public UserGrantedAuthority() {
	
	}

	public String getAuthority() {
		return authority;
	}
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof UserGrantedAuthority) {
			return authority.equals(((UserGrantedAuthority) obj).authority);
		}

		return false;
	}

	public int hashCode() {
		return this.authority.hashCode();
	}

	public String toString() {
		return this.authority;
	}
}
