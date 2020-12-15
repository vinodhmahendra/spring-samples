package io.tanzu.workshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.tanzu.workshop.repository.UserRepository;
import io.tanzu.workshop.exceptions.UserNotFoundException;
import io.tanzu.workshop.model.User;

@RestController
public class UserResource {
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		else {
			throw new UserNotFoundException("id-" +id);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser( @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
