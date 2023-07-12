package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.Requests.UserRequest;
import com.Trakya.OrtayaKarisik.Services.AuthService;
import com.Trakya.OrtayaKarisik.responses.AuthResponse;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, AuthResponse>> login(@RequestBody UserRequest loginRequest) {
        return ResponseEntity.ok(Map.of("token", authService.login(loginRequest)));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRequest registerRequest) {
        return new ResponseEntity<>(Map.of("message", "User succesfuly registered",
                "user", authService.register(registerRequest)), HttpStatus.CREATED);
    }
}