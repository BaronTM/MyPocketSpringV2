package com.myPocket.myPocket.controller.services;

import com.myPocket.myPocket.controller.repository.UserRepository;
import com.myPocket.myPocket.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException("User " + username + " could not be found");
    }
}
