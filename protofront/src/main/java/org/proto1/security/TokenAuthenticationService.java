package org.proto1.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;
	protected final Log logger = LogFactory.getLog(getClass());
	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(
				DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response,
			UserAuthentication authentication) {
		logger.debug("TokenAuthenticationService.addAuthentication");
		final UserData userData = authentication.getDetails();
		userData.setExpires(System.currentTimeMillis() + TEN_DAYS);
		response.addHeader(AUTH_HEADER_NAME,
				tokenHandler.createTokenForUser(userData));
		logger.debug("authentication" + userData.getUsername());
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTH_HEADER_NAME);
	//	logger.debug("TokenAuthenticationService.getAuthentication token = "
	//			+ token + " requestURI: " + request.getRequestURI());
		if (token != null) {
			final UserData userData = tokenHandler
					.parseUserDataFromToken(token);
			if (userData != null) {
				return new UserAuthentication(userData);
			}
		}

		return null;
	}
}
