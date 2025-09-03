package com.app.dialy_cafe.service.serviceImpl;

import com.app.dialy_cafe.configuration.JwtUtil;
import com.app.dialy_cafe.model.Users;
import com.app.dialy_cafe.payload.BaseResponse;
import com.app.dialy_cafe.payload.request.LoginRequest;
import com.app.dialy_cafe.payload.request.RegisterRequest;
import com.app.dialy_cafe.payload.response.LoginResponse;
import com.app.dialy_cafe.repository.UserRepository;
import com.app.dialy_cafe.service.AuthService;
import com.app.dialy_cafe.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    @Override
    public BaseResponse<?> loginByEmailAndPassword(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getName(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }
        log.info("Req : " + loginRequest.getName());

        final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getName());
        final String token = jwtUtil.generateToken(userDetails);

        return BaseResponse.builder()
                .message("User login successfully")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(LoginResponse.builder()
                        .name(userDetails.getUsername())
                        .token(token)
                        .build()
                ).build();
    }

    @Override
    public BaseResponse<?> register(RegisterRequest req) {
        Users user = new Users();
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
        final UserDetails userDetails = userDetailService.loadUserByUsername(user.getName());
        final String token = jwtUtil.generateToken(userDetails);
        return BaseResponse.builder()
                .message("User register successfully")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(LoginResponse.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .token(token)
                        .build()
                )
                .build();
    }
}
