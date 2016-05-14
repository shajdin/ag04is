package com.shajdin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shajdin.model.User;
import com.shajdin.repository.UserRepository;

@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean usernameExists(String username){
		return getUserByUsername(username) == null ? false : true;
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);
	}
	
	
}
