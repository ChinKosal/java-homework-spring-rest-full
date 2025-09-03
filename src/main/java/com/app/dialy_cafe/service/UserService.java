package com.app.dialy_cafe.service;

import com.app.dialy_cafe.payload.BaseResponse;
import com.app.dialy_cafe.payload.request.UserRequest;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    BaseResponse<?> register(UserRequest req);

    BaseResponse<?> getUsers();

    BaseResponse<?> findUserById(Long id);

    BaseResponse<?> updateUserById(Long id, UserRequest req);

    BaseResponse<?> deleteUserById(Long id);

    UserDetails loadUserByUsername(String userName);
}
