package com.ecom.proj.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.proj.global.GlobalData;
import com.ecom.proj.model.CustomUserDetail;
import com.ecom.proj.model.User;
import com.ecom.proj.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        
        GlobalData.user = userRepository.findUserByEmail(email).get();

        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return user.map(CustomUserDetail::new).get();
    }

}
