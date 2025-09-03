package com.app.dialy_cafe.service;


import com.app.dialy_cafe.payload.BaseResponse;
import com.app.dialy_cafe.payload.request.LoginRequest;
import com.app.dialy_cafe.payload.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    BaseResponse<?> loginByEmailAndPassword(LoginRequest loginRequest);

    BaseResponse<?> register(RegisterRequest req);
}
