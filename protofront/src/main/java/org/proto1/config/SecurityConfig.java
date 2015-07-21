package org.proto1.config;

import javax.annotation.Priority;

import org.proto1.security.ActiveDirectoryGrantedAuthoritiesMapper;
import org.proto1.security.StatelessAuthenticationFilter;
import org.proto1.security.StatelessLoginFilter;
import org.proto1.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

@Configuration
@EnableWebSecurity
@ComponentScan("org.proto1.security")
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
@Priority(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler restAuthenticationSuccessHandler;
	@Autowired
	AuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	private AuthenticationFailureHandler restAuthenticationFailureHandler;
	@Autowired
	private ActiveDirectoryGrantedAuthoritiesMapper activeDirectoryGrantedAuthoritiesMapper;
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.authorizeRequests()
		 * //.antMatchers("/service/**").access("hasRole('ROLE_USER')")
		 * .anyRequest().anonymous() .and().httpBasic()
		 * .authenticationEntryPoint(restAuthenticationEntryPoint).and()
		 * .formLogin().loginPage("/login")
		 * .successHandler(restAuthenticationSuccessHandler)
		 * .failureHandler(restAuthenticationFailureHandler);
		 */
		http.anonymous()
				.and()
				.authorizeRequests()
				.anyRequest()
				.permitAll()
				.and()
				.csrf()
				.disable()
				// token header upon authentication
				.addFilterBefore(statelessLoginFilter(),
						UsernamePasswordAuthenticationFilter.class)
				// custom Token based authentication based on the header
				// previously given to the client
				.addFilterBefore(
						statelessAuthenticationFilter(),
						UsernamePasswordAuthenticationFilter.class)
				.servletApi().and().headers().cacheControl();
		http.headers().defaultsDisabled().addHeaderWriter(
				new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));

	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	private StatelessLoginFilter statelessLoginFilter() throws Exception {
		return new StatelessLoginFilter("/login", tokenAuthenticationService,
				userDetailsService(), authenticationManager());

	}

	private StatelessAuthenticationFilter statelessAuthenticationFilter() {
		return new StatelessAuthenticationFilter(tokenAuthenticationService);
	}
	/* integration with AD */
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	}

	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
				"isd.local", "ldap://IUD-DcKyiv:389");
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		provider.setAuthoritiesMapper(activeDirectoryGrantedAuthoritiesMapper);
		return provider;
	}
*/ 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	    .withUser("user1").password("111").roles("USER").and()
	    .withUser("user2").password("222").roles("USER", "SUPERUSER").and()
        .withUser("admin").password("000").roles("USER", "SUPERUSER", "ADMIN");

	    ;
	}

}