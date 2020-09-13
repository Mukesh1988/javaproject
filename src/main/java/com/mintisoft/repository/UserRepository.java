package com.mintisoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mintisoft.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//select * from users_tabs where usermail=? 
	User findByUserEmail(String userEmail);
	
}
