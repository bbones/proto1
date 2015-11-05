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