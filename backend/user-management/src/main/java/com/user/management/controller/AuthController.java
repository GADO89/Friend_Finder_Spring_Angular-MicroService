package com.user.management.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.model.dto.auth.AuthDto;
import com.user.management.model.dto.auth.UserAuthDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/auth")
    public ResponseEntity<AuthDto> login(@RequestBody Map<String, Object> params) {
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserAuthDto> signup(@RequestBody Map<String, Object> params) {
        return null;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody String token) {
        return null;
    }

}