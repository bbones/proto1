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

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * UserData. Simple DTO (Data Transfert Object) used to give a structure to a
 * login status return in the login process
 */

public class UserData implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1253053354699667427L;

	/**
	 * Boolean logged in current status
	 */
	private boolean loggedIn;

	/**
	 * Username (or login) currently logged in (or null if not logged in)
	 */
	private String username;

	private String password;

	private long expires;
	private Long id;
	private  Collection<UserGrantedAuthority> authorities;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserData() {
	}

	public UserData(String username) {
		super();
		this.username=username;		
	}

	/**
	 * Is logged in
	 *
	 * @return loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Set logged in
	 *
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Get username (or login)
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set username (or login)
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}



	public Collection<UserGrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<UserGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserData [loggedIn=" + loggedIn + ", username=" + username
				+ ", expires=" + expires + ", id=" + id + ", authorities="
				+ authorities + "]";
	}

}