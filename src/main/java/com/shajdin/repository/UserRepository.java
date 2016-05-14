package com.shajdin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shajdin.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
}
