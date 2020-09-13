package com.mintisoft.service;
import java.util.List;

import com.mintisoft.model.User;

public interface IUserService {

	public Integer saveUser(User user);
	public List<User> getAllUsers();
	
	
}
