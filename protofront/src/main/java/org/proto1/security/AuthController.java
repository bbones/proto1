package org.proto1.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public UserData login(HttpServletRequest request,
			HttpServletResponse response, final UserData userData) {
		logger.debug("try to log" + userData.getUsername());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userData.getUsername(), userData.getPassword());
		try {
			Authentication authentication = authenticationManager
					.authenticate(token);
			userData.setLoggedIn(true);

			logger.debug("Роли пользователя:");
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				logger.debug(ga.getAuthority());
			}
		} catch (BadCredentialsException e) {
			userData.setLoggedIn(false);
		}
		return userData;
	}

}