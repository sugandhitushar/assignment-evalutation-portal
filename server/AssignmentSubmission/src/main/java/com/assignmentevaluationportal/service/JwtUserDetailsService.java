package com.assignmentevaluationportal.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            // userName= admin password= admin
            return new User("admin", "$2a$10$hW9X0uF.wePffEJU2EVqbePlsChUFZwIzdBAzDphKxx2G3YmCOx.W",
                    new ArrayList<>());
        }else
           throw new UsernameNotFoundException("User " + username + " was not found ");

    }
}
