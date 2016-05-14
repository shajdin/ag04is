package com.shajdin.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shajdin.model.User;
import com.shajdin.service.UserService;


public class CustomUserDetailsService implements UserDetailsService{

	private UserService userService;
	
	public CustomUserDetailsService(UserService userService){
		this.userService = userService;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.getUserByUsername(username);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		if(user != null){
			return new SecurityUser(user, authorities);
		}
		
		throw new UsernameNotFoundException(
				"User '" + username + "' not found.");
		
		
	}

}
