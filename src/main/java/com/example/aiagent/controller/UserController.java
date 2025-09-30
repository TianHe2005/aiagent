package com.example.aiagent.controller;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.User;
import com.example.aiagent.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/login")
    public ResponseResult<?> login(@RequestBody Map<String, String> loginRequest) {
        String userId = loginRequest.get("userId");
        String password = loginRequest.get("password");
        String role = loginRequest.get("role");
        return userService.login(userId, password, role);
    }

    @PostMapping("/user/register")
    public ResponseResult<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/users/{userId}")
    public ResponseResult<?> getUserInfo(@PathVariable String userId, @RequestBody Map<String, String> request) {
        String role = request.get("role");
        return userService.getUserInfo(userId, role);
    }

    @PutMapping("/users/{userId}/avatar")
    public ResponseResult<?> updateAvatar(@PathVariable String userId, @RequestBody Map<String, String> request) {
        String avatarUrl = request.get("avatarUrl");
        return userService.updateAvatar(userId, avatarUrl);
    }

    @PutMapping("/users/{userId}/updatepwd")
    public ResponseResult<?> updatePassword(@PathVariable String userId, @RequestBody Map<String, String> request) {
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        return userService.updatePassword(userId, oldPassword, newPassword);
    }

    @PostMapping("/user/{userId}/update")
    public ResponseResult<?> updateUserInfo(@PathVariable String userId, @RequestBody User user) {
        return userService.updateUserInfo(userId, user);
    }
}