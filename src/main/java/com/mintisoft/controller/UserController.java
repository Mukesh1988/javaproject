package com.mintisoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mintisoft.model.User;
import com.mintisoft.service.IUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/register")
public class UserController {

	@Autowired
	private IUserService service; // HAS-A

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id = service.saveUser(user);
		String message = "User '" + id + "' saved";
		
		return ResponseEntity.ok("User '"+id+"' saved");
		
		//return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllProducts() {
		List<User> list = service.getAllUsers();
		return ResponseEntity.ok(list);
	}

}
