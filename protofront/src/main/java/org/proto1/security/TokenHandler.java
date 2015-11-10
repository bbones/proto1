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


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class TokenHandler {

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";
	protected final Log logger = LogFactory.getLog(getClass());
	private final Mac hmac;

	public TokenHandler(byte[] secretKey) {
		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
		}
	}

	public UserData parseUserDataFromToken(String token) {
		final String[] parts = token.split(SEPARATOR_SPLITTER);
		if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
			try {
				final byte[] userBytes = fromBase64(parts[0]);
				final byte[] hash = fromBase64(parts[1]);

				boolean validHash = Arrays.equals(createHmac(userBytes), hash);
				if (validHash) {
					final UserData userData= fromJSON(userBytes);					
					if (new Date().getTime() < userData.getExpires()) {
						logger.debug("UserData parseUserDataFromToken = "+userData);
						return userData;						
					}
				}
			} catch (IllegalArgumentException e) {
				//log tempering attempt here
			}
		}
		return null;
	}

	public String createTokenForUser(UserData userData) {
		byte[] userBytes = toJSON(userData);
		byte[] hash = createHmac(userBytes);
		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}

	private UserData fromJSON(final byte[] userBytes) {
		try {
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), UserData.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] toJSON(UserData userData) {
		try {
			return new ObjectMapper().writeValueAsBytes(userData);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String toBase64(byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	private byte[] fromBase64(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	// synchronized to guard internal hmac object
	private synchronized byte[] createHmac(byte[] content) {
		return hmac.doFinal(content);
	}

	public static void main(String[] args) {
		Date start = new Date();
		byte[] secret = new byte[70];
		new java.security.SecureRandom().nextBytes(secret);

		TokenHandler tokenHandler = new TokenHandler(secret);
		for (int i = 0; i < 10; i++) {
			final UserData userData = new UserData();
			userData.setUsername("xxx");
			userData.setId(111L);
			 Date expires =new Date(new Date().getTime() + 10000);
			userData.setExpires(expires.getTime());
			
			UserGrantedAuthority customRole = new UserGrantedAuthority("ROLE_USER");
			ArrayList<UserGrantedAuthority> roles = new ArrayList<UserGrantedAuthority>();
			roles.add(customRole);
			userData.setAuthorities(roles);
			final String token = tokenHandler.createTokenForUser(userData);
			final UserData parsedUserData = tokenHandler.parseUserDataFromToken(token);
			System.out.println(parsedUserData);
			System.out.println(parsedUserData.getUsername());
			if (parsedUserData == null || parsedUserData.getUsername() == null) {
				System.out.println(parsedUserData);
			}
		}
		System.out.println(System.currentTimeMillis() - start.getTime());
	}
	
}
