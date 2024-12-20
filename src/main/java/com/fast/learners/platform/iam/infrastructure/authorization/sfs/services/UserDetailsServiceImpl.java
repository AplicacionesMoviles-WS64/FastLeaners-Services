package com.fast.learners.platform.iam.infrastructure.authorization.sfs.services;

import com.fast.learners.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
/*
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


*/
/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetailsService interface.
 */
public class UserDetailsServiceImpl /* implements UserDetailsService */{

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is responsible for loading the user details from the database.
     * @param username The username.
     * @return The UserDetails object.
     */
    /**
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Profile not found with username: " + username));
        return UserDetailsImpl.build(user);
    }
    */
}