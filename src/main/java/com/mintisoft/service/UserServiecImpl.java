package com.mintisoft.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mintisoft.model.User;
import com.mintisoft.repository.UserRepository;

@Service
public class UserServiecImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository repo; // HAS-A
	
	@Autowired
	private	BCryptPasswordEncoder encoder;
	
	
	//this method is called while registring process
	@Override
	public Integer saveUser(User user) {

		// reading the user passeord
		String pwd = user.getUserPwd();
		
		// converting into unreadable fromat
		pwd = encoder.encode(pwd);
		
		// set back to user model class so that we can save it
		user.setUserPwd(pwd);
		
		// saving in db
		return repo.save(user).getId();// it will return the id
	}

	/*
	 * this is called on login process
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// This is model User class
		User user = repo.findByUserEmail(username);

		List<String> roles = user.getRoles();

		// here we will have list of authorities that why take Set<>
		Set<GrantedAuthority> authorities = new HashSet<>();

		// now convertint roles to authorities

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));

		}
		
		// this is spring security user
		org.springframework.security.core.userdetails.User uob = new org.springframework.security.core.userdetails.
																User(
																	username, 
																	user.getUserPwd(), 
																	authorities);

		return uob;
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	
			
}
