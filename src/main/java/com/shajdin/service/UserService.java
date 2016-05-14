package com.shajdin.service;

import com.shajdin.model.User;

public interface UserService {

	public boolean usernameExists(String username);
	
	public User getUserById(Long id);
	
	public User getUserByUsername(String username);
	
	public void createUser(User user);
	
}
