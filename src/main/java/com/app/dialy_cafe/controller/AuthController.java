package com.app.dialy_cafe.controller;

import com.app.dialy_cafe.payload.BaseResponse;
import com.app.dialy_cafe.payload.request.LoginRequest;
import com.app.dialy_cafe.payload.request.RegisterRequest;
import com.app.dialy_cafe.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<?> loginByEmailAndPassword(@RequestBody LoginRequest loginRequest) {
        return authService.loginByEmailAndPassword(loginRequest);
    }

    @PostMapping("/register")
    private BaseResponse<?> register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }
}
