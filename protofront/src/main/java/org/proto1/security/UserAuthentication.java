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
