package com.shajdin.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shajdin.model.User;



public class SecurityUser extends org.springframework.security.core.userdetails.User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;

	
	public SecurityUser(User user, List<SimpleGrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), authorities);
		this.user=user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
