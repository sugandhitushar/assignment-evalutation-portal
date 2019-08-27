package com.assignmentSubmition.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if ("admin".equals(login)) {
            // userName= admin password= admin
            return new User("admin", "$2a$10$tSLo5oDwwLpvKgOGG/cHE.WdupXw55.0UXFAJ9zxQf53XlD2gn4Jm",
                    new ArrayList<>());
        }else
           throw new UsernameNotFoundException("User " + login + " was not found ");

    }
}
