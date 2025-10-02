package com.greentrack.controller;

import com.greentrack.config.JwtUtil;
import com.greentrack.dto.UserDTO;
import com.greentrack.entity.User;
import com.greentrack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto) {
        UserDTO created = userService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        User user = userService.findEntityByEmail(email).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) throw new RuntimeException("Invalid credentials");
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(java.util.Collections.singletonMap("token", token));
    }
}
