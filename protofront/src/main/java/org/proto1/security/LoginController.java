package org.proto1.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LoginController {
	protected final Log logger = LogFactory.getLog(getClass());
	@RequestMapping(value = "/currentuser", method = RequestMethod.GET)
	@ResponseBody
	public UserData getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 logger.debug("LoginController.getCurrent authentication = "+authentication);
		if (authentication instanceof UserAuthentication) {
			return ((UserAuthentication) authentication).getDetails();
		}
		return new UserData(authentication.getName()); //anonymous user support
	}
}
