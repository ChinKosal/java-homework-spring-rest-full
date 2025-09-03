package com.app.dialy_cafe.service.serviceImpl;

import com.app.dialy_cafe.model.Users;
import com.app.dialy_cafe.payload.BaseResponse;
import com.app.dialy_cafe.payload.request.UserRequest;
import com.app.dialy_cafe.payload.response.UserResponse;
import com.app.dialy_cafe.repository.UserRepository;
import com.app.dialy_cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public BaseResponse<?> register(UserRequest req) {
        log.info("Registering user: {}", req);
        Users user = Users.builder()
                .name(req.getName())
                .phone(req.getPhone())
                .email(req.getEmail())
                .address(req.getAddress())
                .build();
        userRepository.save(user);
        return BaseResponse.builder()
                .message("Budget created successfully")
                .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                .payload(UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .phone(user.getPhone())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .build())
                .build();
    }

    @Override
    public BaseResponse<?> getUsers() {
        return BaseResponse.builder()
                .message("Get all user successful")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(userRepository.findAll())
                .build();
    }

    @Override
    public BaseResponse<?> findUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return BaseResponse.builder()
                    .message("User not found with id " + id)
                    .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .payload(null)
                    .build();
        }

        Users u = user.get();

        return BaseResponse.builder()
                .message("Get user by id successful")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(UserResponse.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .phone(u.getPhone())
                        .address(u.getAddress())
                ).build();
    }

    @Override
    public BaseResponse<?> updateUserById(Long id, UserRequest req) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return BaseResponse.builder()
                    .message("User not found with id " + id)
                    .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .payload(null)
                    .build();
        }

        Users u = user.get();
        u.setName(req.getName() != null ? req.getName() : u.getName());
        u.setPhone(req.getPhone() != null ? req.getPhone() : u.getPhone());
        u.setEmail(req.getEmail() != null ? req.getEmail() : u.getEmail());
        u.setAddress(req.getAddress() != null ? req.getAddress() : u.getAddress());

        userRepository.saveAndFlush(u);


        return BaseResponse.builder()
                .message("Update user successful")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(UserResponse.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .phone(u.getPhone())
                        .email(u.getEmail())
                        .address(u.getAddress())
                ).build();
    }

    @Override
    public BaseResponse<?> deleteUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return BaseResponse.builder()
                    .message("User not found with id " + id)
                    .statusCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .payload(null)
                    .build();
        }

        Users u = user.get();
        userRepository.delete(u);

        return BaseResponse.builder()
                .message("User deleted successful")
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .payload(UserResponse.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .phone(u.getPhone())
                        .email(u.getEmail())
                        .address(u.getAddress())
                ).build();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return null;
    }
}
