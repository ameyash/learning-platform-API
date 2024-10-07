package com.gamified.learning_platform.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gamified.learning_platform.Repositories.UserRepository;
import com.gamified.learning_platform.Models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return user.get();
	}

	// <summary>Create user</summary>
	// <return>String</return>
	public String createUser(User user) throws Exception {
		try {
			userRepository.save(user);

			return "User Added";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	// <summary>Find user by username</summary>
	// <return>User</return>
	public User findByUsername(String email) throws Exception {
		
		Optional<User> userOptional = userRepository.findByEmail(email);

		// Handle the case where the user is not found
		if (userOptional.isPresent()) {
			return userOptional.get();
		} 
		return null;
	}
}
