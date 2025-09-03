package com.app.dialy_cafe.service.serviceImpl;

import com.app.dialy_cafe.exception.NotFoundExceptionHandler;
import com.app.dialy_cafe.repository.UserRepository;
import com.app.dialy_cafe.service.UserDetailService;
import com.app.dialy_cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        var user = userRepository.findByName(name)
                .orElseThrow(() -> new NotFoundExceptionHandler("User not found: " + name));
        return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();
    }
}

