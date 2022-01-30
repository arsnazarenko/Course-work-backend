package com.itmo.squid.auth.service;

import com.itmo.squid.auth.jwt.CustomUserDetails;
import com.itmo.squid.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAuthService userAuthService;


    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userAuthService.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + s + " not found");
        }
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}
