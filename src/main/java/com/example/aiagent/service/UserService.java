package com.example.aiagent.service;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.User;
import com.example.aiagent.repository.UserRepository;
import com.example.aiagent.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                      JwtService jwtService, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public ResponseResult<?> login(String userId, String password, String role) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userId, password)
            );
            
            Optional<User> userOpt = userRepository.findByUserIdAndRole(userId, role);
            if (userOpt.isEmpty()) {
                return ResponseResult.fail("用户不存在或角色不匹配");
            }
            
            User user = userOpt.get();
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", user.getRole());
            
            String token = jwtService.generateToken(extraClaims, loadUserByUsername(userId));
            
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            result.put("userId", userId);
            
            return ResponseResult.success(result);
        } catch (Exception e) {
            return ResponseResult.fail("用户名或密码错误");
        }
    }

    public ResponseResult<?> register(User user) {
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            return ResponseResult.fail("用户ID已存在");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null); // Don't return password
        
        return ResponseResult.success(savedUser, "注册成功");
    }

    public ResponseResult<?> getUserInfo(String userId, String role) {
        Optional<User> userOpt = userRepository.findByUserIdAndRole(userId, role);
        if (userOpt.isEmpty()) {
            return ResponseResult.fail("用户不存在或角色不匹配");
        }
        
        User user = userOpt.get();
        user.setPassword(null); // Don't return password
        
        return ResponseResult.success(user);
    }

    public ResponseResult<?> updateAvatar(String userId, String avatarUrl) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        if (userOpt.isEmpty()) {
            return ResponseResult.fail("用户不存在");
        }
        
        User user = userOpt.get();
        user.setAvatarUrl(avatarUrl);
        user.setUpdateTime(LocalDateTime.now());
        
        userRepository.save(user);
        
        return ResponseResult.success(null, "头像更新成功");
    }

    public ResponseResult<?> updatePassword(String userId, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        if (userOpt.isEmpty()) {
            return ResponseResult.fail("用户不存在");
        }
        
        User user = userOpt.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseResult.fail("原密码错误");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdateTime(LocalDateTime.now());
        
        userRepository.save(user);
        
        return ResponseResult.success(null, "密码更新成功");
    }

    public ResponseResult<?> updateUserInfo(String userId, User updatedUser) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        if (userOpt.isEmpty()) {
            return ResponseResult.fail("用户不存在");
        }
        
        User user = userOpt.get();
        
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getSex() != null) {
            user.setSex(updatedUser.getSex());
        }
        if (updatedUser.getCollege() != null) {
            user.setCollege(updatedUser.getCollege());
        }
        if (updatedUser.getClassName() != null) {
            user.setClassName(updatedUser.getClassName());
        }
        if (updatedUser.getAdmissionYear() != null) {
            user.setAdmissionYear(updatedUser.getAdmissionYear());
        }
        if (updatedUser.getSchoolYearSystem() != null) {
            user.setSchoolYearSystem(updatedUser.getSchoolYearSystem());
        }
        
        user.setUpdateTime(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null); // Don't return password
        
        return ResponseResult.success(savedUser, "更新成功");
    }
}