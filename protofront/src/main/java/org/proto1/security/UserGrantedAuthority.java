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
