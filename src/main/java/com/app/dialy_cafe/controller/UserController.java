package com.app.dialy_cafe.controller;

import com.app.dialy_cafe.payload.BaseResponse;
import com.app.dialy_cafe.payload.request.UserRequest;
import com.app.dialy_cafe.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    public BaseResponse<?> register(@RequestBody UserRequest req){
        return userService.register(req);
    }

    @GetMapping("/get-user")
    public BaseResponse<?> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get-user/{id}")
    public BaseResponse<?> findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PutMapping("/update-user/{id}")
    public BaseResponse<?> updateUserById(@PathVariable Long id, @RequestBody UserRequest req){
        return userService.updateUserById(id, req);
    }

    @PutMapping("/delete-user/{id}")
    public BaseResponse<?> deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
}
