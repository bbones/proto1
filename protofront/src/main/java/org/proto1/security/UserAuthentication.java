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
package org.proto1.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;



public class UserAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8812952892758114560L;
	private final UserData userData;
	private boolean authenticated = true;

	public UserAuthentication(UserData userData) {
		this.userData = userData;
	}

	@Override
	public String getName() {
		return userData.getUsername();
	}

	@Override
	public Collection<UserGrantedAuthority> getAuthorities() {
		return userData.getAuthorities();
	}

	@Override
	public String toString() {
		return "UserAuthentication [userData=" + userData
				+ ", authenticated=" + authenticated + ", getName()="
				+ getName() + ", getAuthorities()=" + getAuthorities()
				+ ", getCredentials()=" + getCredentials() + ", getDetails()="
				+ getDetails() + ", getPrincipal()=" + getPrincipal() + "]";
	}

	@Override
	public Object getCredentials() {
		return userData.getPassword();
	}

	@Override
	public UserData getDetails() {
		return userData;
	}

	@Override
	public Object getPrincipal() {
		return userData.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
