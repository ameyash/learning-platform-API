package com.gamified.learning_platform.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamified.learning_platform.Common.JwtUtil;
import com.gamified.learning_platform.Models.AuthRequest;
import com.gamified.learning_platform.Models.AuthResponse;
import com.gamified.learning_platform.Models.User;
import com.gamified.learning_platform.Models.Dto.UserDto;
import com.gamified.learning_platform.Services.MyUserDetailsService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			System.out.println(authRequest.getUsername()+" "+authRequest.getPassword());
			authenticationManager
					.authenticate((Authentication) new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
							authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}

		final UserDetails userDetails = findByusername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
		User userDetails = null;
		try {
			userDetails = userDetailsService.findByUsername(userDto.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userDetails == null) {
			User user = new User();
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setUsername(userDto.getEmail());
			user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Hash the password

			try {
				userDetailsService.createUser(user);
			} catch (Exception e) {
				return (ResponseEntity<String>) ResponseEntity.internalServerError();
			}
			System.out.println(user);
			return ResponseEntity.ok("User registered successfully!");
		}
		return ResponseEntity.ofNullable("User already exist");

	}

	private UserDetails findByusername(String username) throws Exception {
		final UserDetails userDetails = (UserDetails) userDetailsService.findByUsername(username);
		System.out.println(userDetails);
		return userDetails;
	}
}
