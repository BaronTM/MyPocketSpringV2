package com.myPocket.myPocket.controller.services;

import com.myPocket.myPocket.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PocketUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.myPocket.myPocket.model.entities.User userFromRepository = userRepository.findByUserName(userName);
        return new User(userFromRepository.getUserName(), userFromRepository.getPassword(), new ArrayList<>());
    }
}
