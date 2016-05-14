package com.shajdin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shajdin.model.User;

public class CurrentUser {
	
	public static SecurityUser getSecurityUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return (SecurityUser) auth.getPrincipal();
		}
		return null;
	}

	public static User getUser() {
		SecurityUser securityUser = getSecurityUser();
		if (securityUser != null) {
			return securityUser.getUser();
		}
		return null;
	}
}
