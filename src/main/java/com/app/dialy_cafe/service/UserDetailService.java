package com.app.dialy_cafe.service;

import com.app.dialy_cafe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailService extends UserDetailsService {
    UserDetails loadUserByUsername(String userName);
}
