package org.proto1.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	@SuppressWarnings("unused")
	private final UserDetailsService userDetailsService;
	protected final Log logger = LogFactory.getLog(getClass());
	public StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
			UserDetailsService userDetailsService, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		logger.debug("StatelessLoginFilter");
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		logger.debug("StatelessLoginFilter.attemptAuthentication");
		final UserData userData = new ObjectMapper().readValue(request.getInputStream(), UserData.class);
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				userData.getUsername(), userData.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException {
		logger.debug("StatelessLoginFilter.successfulAuthentication");
	//	 super.successfulAuthentication(request, response, chain, authentication);

	
		// Lookup the complete User object from the database and create an Authentication for it
		//by redfox
		//final UserData userData = (UserData)userDetailsService.loadUserByUsername(authentication.getName());
		final UserData userData = new UserData();
		userData.setUsername(authentication.getName());
		ArrayList<UserGrantedAuthority> roles = new ArrayList<UserGrantedAuthority>();	
		for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(new UserGrantedAuthority(authority.getAuthority()));
        }		
		userData.setAuthorities(roles);
		/* TODO: взять из базы ID*/
		userData.setId(999L);
		final UserAuthentication userAuthentication = new UserAuthentication(userData);
		
		logger.debug("StatelessLoginFilter.successfulAuthentication userAuthentication = "+userAuthentication );
		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);
	
		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);

	}
}