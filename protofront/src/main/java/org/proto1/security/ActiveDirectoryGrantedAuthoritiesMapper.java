package org.proto1.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.stereotype.Component;
@Component
public class ActiveDirectoryGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLE_USER = "ROLE_USER";
    public ActiveDirectoryGrantedAuthoritiesMapper()
    { }

    public Collection<? extends GrantedAuthority> mapAuthorities(
            final Collection<? extends GrantedAuthority> authorities)
    {

    	 Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
                 for (GrantedAuthority authority : authorities)
        {
            roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + authority.getAuthority().toUpperCase()));            
        }
        roles.add(new SimpleGrantedAuthority(ROLE_USER));                    
        return roles;
    }
}