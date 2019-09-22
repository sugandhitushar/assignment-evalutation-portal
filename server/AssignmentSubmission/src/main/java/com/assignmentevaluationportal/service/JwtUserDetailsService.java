package com.assignmentevaluationportal.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.UserStatus;
import com.assignmentevaluationportal.repository.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.assignmentevaluationportal.model.User user = userRepository.findByEmail(username);
		
		if(user != null && user.getStatus() == UserStatus.ACTIVE) {
			return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
		} else
           throw new UsernameNotFoundException("User " + username + " was not found ");
    }
}
